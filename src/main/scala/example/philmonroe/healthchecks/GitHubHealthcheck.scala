package example.philmonroe.healthchecks

import example.philmonroe.clients.GithubAPI
import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result


class GitHubHealthCheck(gitHub: GithubAPI) extends HealthCheck {
  override def check(): Result = {
    val rateLimit = gitHub.getRateLimit().rate
    val message = s"remaining: ${rateLimit.remaining}"

    if (rateLimit.remaining > 100)
      Result.healthy(message)
    else
      Result.unhealthy(message)
  }
}
