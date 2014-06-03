package example.philmonroe.api.github


case class Status(state: String, url: String, description: String, context: String, target_url: String, creator: User, id: Long, created_at: String, updated_at: String)
