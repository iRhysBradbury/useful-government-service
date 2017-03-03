package model.db.nodeTest

import client.CountryDataClient

case class Country(
  key: String,
  displayName: String
)

object Country {
  def fromAPI(from: CountryDataClient.Country) = {
    Country(
      key = from.name,
      displayName = from.name
    )
  }
}