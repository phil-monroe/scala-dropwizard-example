package example.philmonroe.api

import example.philmonroe.api.github.Status


case class BranchStatus(branch: String, status: String, description: String, context: String, target_url: String)

object BranchStatus {
  def fromResponse(branch: String, res: Status) = BranchStatus(branch, res.state, res.description, res.context, res.target_url)
}