package example.philmonroe.setup.bundles

import io.dropwizard.ConfiguredBundle
import io.dropwizard.setup.{Environment, Bootstrap}
import com.wordnik.swagger.config.{ConfigFactory, ScannerFactory}
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner
import com.wordnik.swagger.reader.ClassReaders
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader
import io.dropwizard.assets.AssetsBundle
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON
import example.philmonroe.DwExampleConfig

class SwaggerBundle extends ConfiguredBundle[DwExampleConfig] {
  override def initialize(bootstrap: Bootstrap[_]): Unit = {
    // Swagger
    ScannerFactory.setScanner(new DefaultJaxrsScanner())
    ClassReaders.setReader(new DefaultJaxrsApiReader())

    // Swagger UI
    bootstrap.addBundle(new AssetsBundle("/swagger-ui", "/docs/"))
  }

  override def run(config: DwExampleConfig, environment: Environment): Unit = {
    val swaggerConfig = ConfigFactory.config
    swaggerConfig.setApiVersion("2.0.0")
    swaggerConfig.setBasePath(s"${config.hostname}")

    environment.jersey().register(new ApiListingResourceJSON)
  }
}
