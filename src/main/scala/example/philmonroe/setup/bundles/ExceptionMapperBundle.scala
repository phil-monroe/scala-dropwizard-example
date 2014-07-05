package example.philmonroe.setup.bundles

import io.dropwizard.Bundle
import io.dropwizard.setup.{Environment, Bootstrap}
import javax.ws.rs.ext.ExceptionMapper
import example.philmonroe.setup.exceptionmappers.RuntimeExceptionMapper
import scala.collection.JavaConversions._

class ExceptionMapperBundle extends Bundle {
  override def initialize(bootstrap: Bootstrap[_]): Unit = {
  }

  override def run(env: Environment): Unit = {
    env.jersey().getResourceConfig.getSingletons
      .filter(s => s.isInstanceOf[ExceptionMapper[_]])
      .map(env.jersey().getResourceConfig.getSingletons.remove)

    env.jersey().register(new RuntimeExceptionMapper)
  }
}
