package example.philmonroe.healthchecks

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient

class ElasticSearchHealthcheck(elasticSearch: ManagedElasticSearchClient) extends HealthCheck {
  override def check(): Result = {
    val res = elasticSearch.status
    if (res.isSucceeded)
      Result.healthy()
    else
      Result.unhealthy(res.getJsonString)
  }
}
