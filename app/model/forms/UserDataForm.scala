package model.forms

import play.api.libs.json.Json
import play.api.data._
import play.api.data.Forms._

case class UserDataForm(
  name: String,
  age: Int,
  sex: String,
  country: String
)

object UserDataForm {

  implicit lazy val fmt = Json.format[UserDataForm]

  object Fields {
    val name = "name"
    val age = "age"
    val sex = "sex"
    val country = "country"
  }

  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "age" -> number(
        min = 1,
        max = 120
      ),
      "sex" -> nonEmptyText,
      "country" -> nonEmptyText
    )(UserDataForm.apply)(UserDataForm.unapply)
  )
}