package example.philmonroe.api.twitter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by phil on 7/3/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
case class User(screen_name: String, name: String, location: String)
