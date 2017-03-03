package controllers

import javax.inject._
import client.CountryDataClientInstance
import model.db.nodeTest.Country
import model.forms.UserDataForm
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services.DataBaseService
import scala.concurrent.ExecutionContext

@Singleton
class IndexController @Inject()(
  override val messagesApi: MessagesApi,
  dataBaseService: DataBaseService,
  countryDataClientInstance: CountryDataClientInstance
)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def index(): Action[AnyContent] = Action.async {
    countryDataClientInstance.countries.map(countries =>
      Ok(
        views.html.index(
          title = messagesApi("index.title"),
          form = UserDataForm.form,
          countries = countries.map { c =>
            val country = Country.fromAPI(c)
            country.key -> country.displayName
          }
        )
      )
    )
  }
}
