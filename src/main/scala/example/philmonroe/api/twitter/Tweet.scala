package example.philmonroe.api.twitter

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by phil on 7/3/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
case class Tweet(created_at: String,
                 id: Long,
                 text: String,
                 truncated: Boolean,
                 user: User,
                  time: Long = 0
                  )
