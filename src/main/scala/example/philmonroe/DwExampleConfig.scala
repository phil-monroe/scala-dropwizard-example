package example.philmonroe

import io.dropwizard.Configuration
import javax.validation.constraints.NotNull
import io.dropwizard.client.JerseyClientConfiguration
import com.fasterxml.jackson.annotation.JsonProperty
import example.philmonroe.setup.config.TwitterConfig
import javax.validation.Valid
import io.dropwizard.server.{SimpleServerFactory, DefaultServerFactory, ServerFactory}


class DwExampleConfig extends Configuration {
  @Valid
  @NotNull
  private val server: ServerFactory = new SimpleServerFactory

  @Valid
  val twitter = new TwitterConfig



}

