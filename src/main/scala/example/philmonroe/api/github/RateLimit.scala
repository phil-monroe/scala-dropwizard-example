package example.philmonroe.api.github


case class RateLimit(limit: Long, remaining: Long, reset: Long)
