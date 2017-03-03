package model.db.nodeTest

import org.joda.time.DateTime

/**
  * A record or document
  *
  * @param name the name of the user
  * @param sex the gender of the user as a string
  * @param age the age of the user
  * @param country the country of origin of the user
  * @param dateCreated a UTC of when the user was created by the system
  */
case class UserData(
  name: String,
  sex: String,
  age: Int,
  country: String,
  dateCreated: DateTime = DateTime.now
)