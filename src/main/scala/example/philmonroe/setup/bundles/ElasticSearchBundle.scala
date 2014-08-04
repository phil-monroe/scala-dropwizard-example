package example.philmonroe.setup.bundles

import io.dropwizard.ConfiguredBundle
import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.DwExampleConfig
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient
import example.philmonroe.tasks.{CreateIndexTask, DropIndexTask}

class ElasticSearchBundle extends ConfiguredBundle[DwExampleConfig] {
  var elasticsearch: ManagedElasticSearchClient = null

  override def initialize(bootstrap: Bootstrap[_]): Unit = {

  }

  override def run(config: DwExampleConfig, env: Environment): Unit = {
    elasticsearch = new ManagedElasticSearchClient(config.elasticSearchUrl)
    env.lifecycle().manage(elasticsearch)

    elasticsearch.createIndex("twitter")

    env.admin().addTask(new CreateIndexTask(elasticsearch))
    env.admin().addTask(new DropIndexTask(elasticsearch))
  }
}
