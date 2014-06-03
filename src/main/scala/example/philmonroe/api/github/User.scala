package example.philmonroe.api.github


case class User(
                 id: Long,
                 gravatar_id: String,
                 site_admin: Boolean,
                 login: String,
                 `type`: String,
                 url: String,
                 gists_url: String,
                 organizations_url: String,
                 repos_url: String,
                 received_events_url: String,
                 following_url: String,
                 subscriptions_url: String,
                 starred_url: String,
                 html_url: String,
                 events_url: String,
                 avatar_url: String,
                 followers_url: String)
