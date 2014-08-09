package example.philmonroe

import io.dropwizard.Configuration
import javax.validation.constraints.NotNull
import example.philmonroe.setup.config.TwitterConfig
import javax.validation.Valid
import io.dropwizard.server.{SimpleServerFactory, ServerFactory}
import com.fasterxml.jackson.annotation.JsonProperty


class DwExampleConfig extends Configuration {
  @Valid
  @NotNull
  private val server: ServerFactory = new SimpleServerFactory


  @Valid
  val twitter = new TwitterConfig


  @NotNull
  @JsonProperty
  val elasticSearchUrl = System.getenv("BONSAI_URL")


  @NotNull
  @JsonProperty
  val hostname = System.getenv("HOSTNAME")
}

