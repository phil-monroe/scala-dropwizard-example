package example.philmonroe.setup

import org.slf4j.LoggerFactory


trait Logging {
  val LOG = LoggerFactory.getLogger(this.getClass)
}
