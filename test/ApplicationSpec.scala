import controllers.UserDataController
import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {
    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/error")).map(status(_)) mustBe Some(NOT_FOUND)
    }
  }

  "IndexController" should {
    "render the index page" in {
      val actual = route(app, FakeRequest(GET, "/")).get
      status(actual) mustBe OK
      contentType(actual) mustBe Some("text/html")
      contentAsString(actual) must include ("form")
    }
  }

  "SecondaryController" should {
    "render the secondary page" in {
      val actual = route(app, FakeRequest(GET, "/secondary?name=Rhys")).get
      status(actual) mustBe OK
      contentType(actual) mustBe Some("text/html")
      contentAsString(actual) must include ("Rhys")
    }
  }

  "UserDataController" should {
    "post a user" in {
      val controller = app.injector.instanceOf[UserDataController]
      val actual = controller.create().apply(
        FakeRequest(
          method = "POST",
          path = controllers.routes.IndexController.index().url
        ).withFormUrlEncodedBody(
          "name" -> "Rhys",
          "age" -> "99",
          "sex" -> "male",
          "country" -> "United Kingdom"
        )
      )
      status(actual) mustBe 303
    }
  }
}
