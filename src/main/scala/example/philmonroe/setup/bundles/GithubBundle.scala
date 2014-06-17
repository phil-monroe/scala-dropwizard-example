package example.philmonroe.setup.bundles

import io.dropwizard.ConfiguredBundle
import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.clients.GithubAPI
import example.philmonroe.GitHubStatusConfig
import io.dropwizard.client.JerseyClientBuilder

/**
 * Created by phil on 6/4/14.
 */
class GithubBundle extends ConfiguredBundle[GitHubStatusConfig] {
  var githubClient: GithubAPI = _

  override def initialize(bootstrap: Bootstrap[_]): Unit = {

  }

  override def run(config: GitHubStatusConfig, env: Environment): Unit = {
    // HTTP Client
    val httpClient = new JerseyClientBuilder(env).using(config.httpClientConfig).build("http-client")


    // GitHub
    githubClient = new GithubAPI(httpClient, config.gitHubToken)
  }
}
