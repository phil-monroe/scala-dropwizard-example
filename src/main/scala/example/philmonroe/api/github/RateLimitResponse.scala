package example.philmonroe.api.github


case class RateLimitResponse(resources: Map[String, RateLimit], rate: RateLimit)
