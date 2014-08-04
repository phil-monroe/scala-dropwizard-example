package example.philmonroe.test

import org.scalatest.{Matchers, FunSpec}
import example.philmonroe.DwExampleService
import net.sourceforge.argparse4j.inf.Namespace
import com.google.common.collect.ImmutableMap
import org.eclipse.jetty.server.{ServerConnector, Server}
import io.dropwizard.setup.{Environment, Bootstrap}
import io.dropwizard.lifecycle.ServerLifecycleListener
import io.dropwizard.cli.ServerCommand
import org.slf4j.LoggerFactory
import com.sun.jersey.api.client.Client
import example.philmonroe.DwExampleService
import example.philmonroe.DwExampleConfig

object TestRuntime extends ServerLifecycleListener {
  val LOG = LoggerFactory.getLogger(this.getClass)

  val configPath = this.getClass.getResource("/config.yml").getPath
  var jettyServer: Option[Server] = None
  var config: Option[DwExampleConfig] = None
  var env: Option[Environment] = None

  def getLocalPort: Int = {
    jettyServer.get.getConnectors.head.asInstanceOf[ServerConnector].getLocalPort
  }

  def serverStarted(server: Server) {
    this.jettyServer = Option(server)
    LOG.info(s"started on port $getLocalPort")
  }

  def startIfRequired {
    if (jettyServer.isDefined)
      return

    val application = new DwExampleService
    val bootstrap = new TestServerBootstrap(application)
    application.initialize(bootstrap)

    val command = new ServerCommand[DwExampleConfig](application)
    val namespace = new Namespace(ImmutableMap.of[String, AnyRef]("file", configPath))
    command.run(bootstrap, namespace)

    while (jettyServer.isEmpty) {
      LOG.warn("waiting")
      Thread.sleep(100)
    }
  }

  class TestServerBootstrap(app: DwExampleService) extends Bootstrap(app) {
    override def run(configuration: DwExampleConfig, environment: Environment) {
      config = Option(configuration)
      env = Option(environment)
      environment.lifecycle.addServerLifecycleListener(TestRuntime)

      super.run(configuration, environment)
    }
  }


}

trait StartRule {
  TestRuntime.startIfRequired
}

abstract class IntegrationSpec extends FunSpec with Matchers with StartRule {
  def newClient = Client.create().resource(s"http://localhost:${TestRuntime.getLocalPort}")

}
