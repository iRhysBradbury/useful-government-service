package client

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import scala.concurrent.ExecutionContext.Implicits.global

class CountryDataClientInstanceTest extends PlaySpec with OneAppPerTest {

  "CountryDataClientInstanceTest" should {

    "get countries" in {
      app.injector.instanceOf[CountryDataClientInstance].countries.map ( countries =>
        assert(countries.nonEmpty)
      )
    }
  }
}
