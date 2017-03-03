package controllers

import javax.inject._
import client.CountryDataClientInstance
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services.DataBaseService
import scala.concurrent.ExecutionContext

@Singleton
class SecondaryController @Inject()(
  override val messagesApi: MessagesApi,
  dataBaseService: DataBaseService,
  countryDataClientInstance: CountryDataClientInstance
)(implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def secondary(name: String): Action[AnyContent] = Action.async {
    countryDataClientInstance.countries.map(countries =>
      Ok(
        views.html.secondary(
          title = messagesApi("index.title"),
          userName = name
        )
      )
    )
  }
}
