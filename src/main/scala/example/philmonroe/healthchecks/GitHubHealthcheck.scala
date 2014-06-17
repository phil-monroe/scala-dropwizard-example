package example.philmonroe.healthchecks

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result
import example.philmonroe.setup.bundles.GithubBundle


class GitHubHealthCheck(gitHubBundle: GithubBundle) extends HealthCheck {
  override def check(): Result = {
    val gitHub = gitHubBundle.githubClient

    val rateLimit = gitHub.getRateLimit().rate
    val message = s"remaining: ${rateLimit.remaining}"

    if (rateLimit.remaining > 100)
      Result.healthy(message)
    else
      Result.unhealthy(message)
  }
}
