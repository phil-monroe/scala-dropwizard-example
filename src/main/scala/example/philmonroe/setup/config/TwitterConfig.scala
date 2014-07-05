package example.philmonroe.setup.config

import javax.validation.constraints.NotNull
import com.fasterxml.jackson.annotation.JsonProperty

class TwitterConfig {
  @NotNull
  @JsonProperty
  val apiKey: String = null

  @NotNull
  @JsonProperty
  val apiSecret: String = null

  @NotNull
  @JsonProperty
  val oauthToken: String = null

  @NotNull
  @JsonProperty
  val oauthSecret: String = null

  @JsonProperty
  val topics = Seq.empty[String]
}
