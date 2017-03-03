package client

import javax.inject.{Inject, Singleton}
import CountryDataClient.Country
import CountryDataClient.Country._
import com.typesafe.config.ConfigFactory
import play.api.libs.json.{Format, Json}
import play.api.libs.ws.WSClient
import scala.concurrent.{ExecutionContext, Future}

/**
  * Configured Injectable
  *
  * @param wsClient a Play WSClient which will used as the HTTP Client
  */
@Singleton
class CountryDataClientInstance @Inject()(override val wsClient: WSClient) extends CountryDataClient {
  override def url = ConfigFactory.load.getString("country.url")
}

/**
  * CountryDataClient interface
  */
trait CountryDataClient {

  /**
    * @return a Play WSClient which will used as the HTTP Client
    */
  def wsClient(): WSClient

  /**
    * @return the url of the json data
    */
  def url(): String

  /**
    * @return all the countries in the api
    */
  def countries(implicit ec: ExecutionContext): Future[Seq[Country]] = {
    wsClient()
      .url(url())
      .get()
      .map { x =>
        x.json.as[Seq[Country]]
      }
  }
}

object CountryDataClient {

  /* JSON API DTO */
  case class Translation(
    language: Option[String],
    nativeText: Option[String]
  )
  object Translation {
    implicit lazy val fmt: Format[Translation] = Json.format[Translation]
  }

  /* JSON API DTO */
  case class Country(
    name: String,
    topLevelDomain: Seq[String],
    alpha2Code: String,
    alpha3Code: String,
    callingCodes: Seq[String],
    capital: String,
    altSpellings: Seq[String],
    region: String,
    subregion: String,
    population: Int,
    latlng: Seq[Double],
    demonym: String,
    gini: Option[Double],
    timezones: Seq[String],
    borders: Seq[String],
    nativeName: String,
    currencies: Seq[String],
    languages: Seq[String]
  )
  object Country {
    implicit lazy val fmt: Format[Country] = Json.format[Country]
  }
}
