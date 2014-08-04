package example.philmonroe.tasks

import io.dropwizard.servlets.tasks.Task
import com.google.common.collect.ImmutableMultimap
import java.io.PrintWriter
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient
import io.searchbox.core.Delete

class DropIndexTask(elasticsearch: ManagedElasticSearchClient) extends Task("es/flush") {
  override def execute(parameters: ImmutableMultimap[String, String], output: PrintWriter): Unit = {

    elasticsearch.client.execute((new Delete.Builder("twitter")).build)
    output.println("ok")
  }
}
