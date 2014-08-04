package example.philmonroe.metrics

import com.fasterxml.jackson.databind.ObjectMapper
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient
import com.codahale.metrics.Gauge
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

case class ShardsStatus(total: Integer, successful: Integer, failed: Integer)

@JsonIgnoreProperties(ignoreUnknown = true)
case class DocsStatus(num_docs: Integer)

@JsonIgnoreProperties(ignoreUnknown = true)
case class IndexStatus(docs: DocsStatus)

@JsonIgnoreProperties(ignoreUnknown = true)
case class ESStatus(_shards: ShardsStatus, indices: Map[String, IndexStatus])

class ElasticSearchNumDocsGauge(mapper: ObjectMapper, es: ManagedElasticSearchClient, index: String) extends Gauge[Integer] {
  override def getValue: Integer = {
    val status = es.status.getJsonString
    val res = mapper.readValue(status, classOf[ESStatus])
    println(status)
    println(res)
    res.indices(index).docs.num_docs
  }
}
