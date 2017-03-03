package controllers

import javax.inject._
import model.db.nodeTest.UserData
import model.forms.UserDataForm
import play.api.Logger
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services.DataBaseService
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserDataController @Inject()(
  override val messagesApi: MessagesApi,
  dataBaseService: DataBaseService
)(implicit executionContext: ExecutionContext) extends Controller with I18nSupport {

  val logger = Logger("UserDataController")

  /**
    * create a user in the database
    *
    * I am unfamiliar with the MongoDB driver
    * I have implemented everything else
    *
    * @return
    */
  def create(): Action[AnyContent] = Action.async { implicit req =>
    UserDataForm.form.bindFromRequest.value map { userDataForm =>
      logger info s"Received POST data: \t $userDataForm"
      //post user
      dataBaseService.postUser(
        UserData(
          name = userDataForm.name,
          sex = userDataForm.sex,
          age = userDataForm.age,
          country = userDataForm.country
        )
      ) map { _ =>
        Redirect(routes.SecondaryController.secondary(userDataForm.name))
      }
    } getOrElse Future.successful(BadRequest)
  }
}
