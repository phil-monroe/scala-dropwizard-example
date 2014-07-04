package example.philmonroe.setup.config

import javax.validation.constraints.NotNull
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty

/**
 * Created by phil on 7/3/14.
 */
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
