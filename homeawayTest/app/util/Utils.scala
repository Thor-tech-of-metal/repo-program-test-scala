package util

import java.util.UUID

import scala.util.Try

case class Utils()

object Utils{

  val  errorValidatingUUID= "there has been an error validating the UUID."

  def validateUUID (uuid:String ) : Either[String,String] = {

    val result = Try(UUID.fromString(uuid))
    result map  { element =>Right(element.toString) } getOrElse(Left(errorValidatingUUID))
  }
}
