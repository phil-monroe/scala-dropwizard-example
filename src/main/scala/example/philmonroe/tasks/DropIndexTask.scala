package example.philmonroe.tasks

import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient
import io.dropwizard.servlets.tasks.Task
import java.io.PrintWriter
import com.google.common.collect.ImmutableMultimap

class DropIndexTask(elasticsearch: ManagedElasticSearchClient) extends Task("es/drop") {
  override def execute(parameters: ImmutableMultimap[String, String], output: PrintWriter): Unit = {

    val res = elasticsearch.dropIndex("twitter")

    output.println(res.getJsonString)
  }
}
