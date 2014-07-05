package example.philmonroe.api.twitter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
case class User(screen_name: String, name: String, location: String)
