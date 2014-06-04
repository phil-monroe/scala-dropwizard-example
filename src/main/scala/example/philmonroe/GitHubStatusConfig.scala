package example.philmonroe

import io.dropwizard.Configuration
import javax.validation.constraints.NotNull
import io.dropwizard.client.JerseyClientConfiguration
import com.fasterxml.jackson.annotation.JsonProperty


class GitHubStatusConfig extends Configuration {
  val DefaultGitHubToken = "c526fbbe8e986379dc98eca3342b8ca4a2e6d954"
  val GitHubTokenEnvVar = "GITHUB_APP_TOKEN"

  @NotNull
  @JsonProperty
  val gitHubToken = Option(System.getenv(GitHubTokenEnvVar)).getOrElse(DefaultGitHubToken)

  val httpClientConfig = new JerseyClientConfiguration
}

