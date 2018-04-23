package models

import io.swagger.annotations.ApiModelProperty
import play.api.libs.json.{Json}

import scala.annotation.meta.field


case class Contact(
                    @(ApiModelProperty @field)(position=1) phone: String,
                    @(ApiModelProperty @field)(position=2) formattedPhone: String
                  )

object Contact { implicit val contactWriter = Json.format[Contact] }


case class Address(
                    @(ApiModelProperty @field)(position=1) address: String,
                    @(ApiModelProperty @field)(position=2) postalCode: String,
                    @(ApiModelProperty @field)(position=3) countryCode: String,
                    @(ApiModelProperty @field)(position=4) city: String,
                    @(ApiModelProperty @field)(position=5) state: String,
                    @(ApiModelProperty @field)(position=6) country: String
                  )

object Address { implicit lazy val addressesWriter = Json.format[Address] }

case class Location(
                     @(ApiModelProperty @field)(position=1) lat: String,
                     @(ApiModelProperty @field)(position=2) lng: String
                   )

object Location { implicit lazy val locationWriter = Json.format[Location]}

case class Listing(
                    @(ApiModelProperty @field)(position=1, value="id") id: Option[String],
                    @(ApiModelProperty @field)(position=2) contact: Contact,
                    @(ApiModelProperty @field)(position=3) address: List[Address],
                    @(ApiModelProperty @field)(position=4) location: Location
                  )

object Listing{
  implicit lazy val listingWriter = Json.format[Listing]
}