package example.philmonroe.tasks

import io.dropwizard.servlets.tasks.Task
import com.google.common.collect.ImmutableMultimap
import java.io.PrintWriter
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient

class CreateIndexTask(elasticsearch: ManagedElasticSearchClient) extends Task("es/create") {
  override def execute(parameters: ImmutableMultimap[String, String], output: PrintWriter): Unit = {

    val res = elasticsearch.createIndex("twitter")
    output.println(res.getJsonString)
  }
}



