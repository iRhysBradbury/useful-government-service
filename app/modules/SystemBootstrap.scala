package modules

import javax.inject.{Inject, Singleton}
import client.CountryDataClientInstance
import play.api.Logger
import services.DataBaseService
import scala.concurrent.ExecutionContext

@Singleton
class SystemBootstrap @Inject()(
  db: DataBaseService,
  countryDataClient: CountryDataClientInstance
)(implicit ec: ExecutionContext) {
  val logger = Logger("SystemBootstrap")

  logger info "setup up mongo db"
  /* create userdata collection and countries collection */

  logger info "upsert countries"
  countryDataClient.countries.map(_.map(country => country /* insert countries with mongo repo */))

}
