package example.philmonroe.setup.bundles

import io.dropwizard.Bundle
import io.dropwizard.setup.{Environment, Bootstrap}
import com.wordnik.swagger.config.ScannerFactory
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner
import com.wordnik.swagger.reader.ClassReaders
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader
import io.dropwizard.assets.AssetsBundle
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON

/**
 * Created by phil on 6/4/14.
 */
class SwaggerBundle extends Bundle {
  override def initialize(bootstrap: Bootstrap[_]): Unit = {
    // Swagger
    ScannerFactory.setScanner(new DefaultJaxrsScanner())
    ClassReaders.setReader(new DefaultJaxrsApiReader())

    // Swagger UI
    bootstrap.addBundle(new AssetsBundle("/swagger-ui", "/docs/"))
  }

  override def run(environment: Environment): Unit = {
    environment.jersey().register(new ApiListingResourceJSON)
  }
}
