package example.philmonroe.api.twitter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
case class Tweet(created_at: String,
                 id: Long,
                 text: String,
                 truncated: Boolean,
                 user: User,
                 time: Long = 0)
