package example.philmonroe.metrics

import com.fasterxml.jackson.databind.ObjectMapper
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient
import com.codahale.metrics.Gauge

class ElasticSearchMeter(mapper: ObjectMapper, es: ManagedElasticSearchClient) extends Gauge[java.util.Map[String, String]] {
  override def getValue: java.util.Map[String, String] = {
    val status = es.status.getJsonString
    println(status)
    mapper.readValue(status, classOf[java.util.Map[String, String]])
  }
}
