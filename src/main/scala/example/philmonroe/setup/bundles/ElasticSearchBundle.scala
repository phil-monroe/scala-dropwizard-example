package example.philmonroe.setup.bundles

import io.dropwizard.ConfiguredBundle
import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.DwExampleConfig
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient

class ElasticSearchBundle extends ConfiguredBundle[DwExampleConfig] {
  var elasticsearch: ManagedElasticSearchClient = null

  override def initialize(bootstrap: Bootstrap[_]): Unit = {

  }

  override def run(config: DwExampleConfig, env: Environment): Unit = {
    elasticsearch = new ManagedElasticSearchClient(env.getObjectMapper)
    env.lifecycle().manage(elasticsearch)
  }
}
