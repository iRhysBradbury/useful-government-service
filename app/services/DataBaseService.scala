package services

import javax.inject._
import com.mongodb.client.MongoCollection
import com.mongodb.{MongoClient, MongoClientURI}
import com.typesafe.config.ConfigFactory
import model.db.nodeTest.UserData
import org.bson.Document
import scala.concurrent.{ExecutionContext, Future}

trait MongoClientRepo {
  def host(): String
  def port(): Int
  def username(): String
  def password(): String

  final val client: MongoClient = {
    new MongoClient(
      new MongoClientURI(
        s"mongodb://$username:$password@$host:$port"
      )
    )
  }
}

@Singleton
class DataBaseService extends MongoClientRepo {

  val mongoConf = ConfigFactory.load.getConfig("mongo")
  val host = mongoConf.getString("host")
  val port = mongoConf.getInt("port")
  val database = mongoConf.getString("database")
  val username = mongoConf.getString("username")
  val password = mongoConf.getString("password")
  val userCollection = mongoConf.getString("userCollection")
  val countryCollection = mongoConf.getString("countryCollection")

  def userdata(): MongoCollection[Document] = client.getDatabase(database).getCollection(userCollection)
  def countrydata(): MongoCollection[Document] = client.getDatabase(database).getCollection(countryCollection)

  def postUser(user: UserData)(implicit ec: ExecutionContext): Future[Unit] = {
    //* I am new to the mongodb driver
    Future.successful(())
  }
}
