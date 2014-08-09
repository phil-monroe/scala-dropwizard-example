package example.philmonroe.setup.bundles

import io.dropwizard.ConfiguredBundle
import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.DwExampleConfig
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient
import example.philmonroe.tasks.{CreateIndexTask, DropIndexTask}
import example.philmonroe.healthchecks.ElasticSearchHealthcheck
import example.philmonroe.metrics.ElasticSearchNumDocsGauge


class ElasticSearchBundle extends ConfiguredBundle[DwExampleConfig] {
  var elasticsearch: Option[ManagedElasticSearchClient] = None

  override def initialize(bootstrap: Bootstrap[_]): Unit = {

  }

  override def run(config: DwExampleConfig, env: Environment): Unit = {
    val elasticsearch = new ManagedElasticSearchClient(config.elasticSearchUrl)
    this.elasticsearch = Some(elasticsearch)

    env.lifecycle().manage(elasticsearch)

    elasticsearch.createIndex("twitter")

    env.admin().addTask(new CreateIndexTask(elasticsearch))
    env.admin().addTask(new DropIndexTask(elasticsearch))

    env.healthChecks().register("elasticsearch", new ElasticSearchHealthcheck(elasticsearch))

    env.metrics().register("elasticsearch.twitter.num_docs", new ElasticSearchNumDocsGauge(env.getObjectMapper, elasticsearch, "twitter"))

  }
}
