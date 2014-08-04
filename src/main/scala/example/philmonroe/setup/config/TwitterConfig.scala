package example.philmonroe.setup.config

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty

class TwitterConfig {
  @NotEmpty
  @JsonProperty
  val apiKey: String = System.getenv("TWITTER_API_KEY")

  @NotEmpty
  @JsonProperty
  val apiSecret: String = System.getenv("TWITTER_API_SECRET")

  @NotEmpty
  @JsonProperty
  val oauthToken: String = System.getenv("TWITTER_OAUTH_TOKEN")

  @NotEmpty
  @JsonProperty
  val oauthSecret: String = System.getenv("TWITTER_OAUTH_SECRET")

  @JsonProperty
  val topics = Option(System.getenv("TWITTER_TOPICS")).map(_.split(",").toSeq).getOrElse(Seq.empty[String])
}
