package example.philmonroe.api


case class RepoStatus(repo: String, branches: Seq[BranchStatus])
