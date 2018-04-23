package controllers

import java.util.UUID
import javax.inject.Inject

import api._
import io.swagger.annotations._
import models.{Listing  }
import play.api.libs.json.Json
import play.api.mvc._
import util.Utils

@Api(value = "/listings", description = "Operations about listings properties.")
class ListingPropertyApiController @Inject()  (listingPropertyData: ListingPropertyData)  extends BaseApiController {

  @ApiOperation(
    nickname = "getlistingsById",
    value = "Find listing by ID",
    notes = "Returns a listing",
    response = classOf[models.Listing],
    httpMethod = "GET",
    authorizations = Array(new Authorization(value = "oauth2",
      scopes = Array(
        new AuthorizationScope(scope = "write:listing", description = "modify listing in your account"),
        new AuthorizationScope(scope = "read:listing", description = "read your listing")
      )))
  )
  @ApiResponses(
    Array( new ApiResponse(code = 400, message = "Invalid ID supplied"),  new ApiResponse(code = 404, message = "listings property not found"))
  )
  def getListingPropertyById(@ApiParam(value = "ID of the listing to fetch") id: String) = Action {

    implicit request =>


      Utils.validateUUID(id) match {

        case Left(error) =>  JsonResponse(new value.ApiResponse(400, s"${Utils.errorValidatingUUID}"))

        case Right(id) => {

          listingPropertyData.getListingPropertyById(id) match {

            case Some(listing) => JsonResponse(listing)
            case _ => JsonResponse(new value.ApiResponse(404, "listings not found"), 404)
          }
        }
      }
  }

  @ApiOperation(nickname = "addListing",
    value = "Add a new Listing",
    response = classOf[Void],
    httpMethod = "POST",
    authorizations = Array(new Authorization(value = "oauth2",
      scopes = Array(
        new AuthorizationScope(scope = "test:anything", description = "anything"),
        new AuthorizationScope(scope = "test:nothing", description = "nothing")
      ))))
  @ApiResponses(Array(new ApiResponse(code = 405, message = "Invalid input")))
  @ApiImplicitParams(Array(new ApiImplicitParam(value = "Listing object that needs to be added to the store", required = true, dataType = "models.Listing", paramType = "body")))
  def addListing() = Action {

    implicit request =>
      request.body.asJson match {

        case Some(jsValue) => {

          Json.fromJson[Listing](jsValue) map { listingModel =>

            val result = listingPropertyData.addListing(listingModel.copy(id = Some(UUID.randomUUID().toString)))
            Ok(Json.toJson(result))

          } getOrElse {
            JsonResponse(new value.ApiResponse(405, "Invalid input"))
          }
        }
        case None => JsonResponse(new value.ApiResponse(405, "Invalid input"))
      }
  }


  @ApiOperation(nickname = "updateListing", value = "Update an existing Listing", response = classOf[Void], httpMethod = "PUT")
  @ApiResponses(Array(
    new ApiResponse(code = 405, message = "Invalid input"),
    new ApiResponse(code = 400, message = "Invalid ID"),
    new ApiResponse(code = 404, message = "Listing not found"))
  )
  @ApiImplicitParams(Array(new ApiImplicitParam(value = "Listing object that needs to be updated in the store", required = true, dataType = "models.Listing", paramType = "body")))
  def updateListing() = Action {

    implicit request =>
      request.body.asJson match {

        case Some(jsValue) => {

          Json.fromJson[Listing](jsValue) map { listingModel =>

            Utils.validateUUID(listingModel.id.getOrElse("No provided")) match {

              case Left(error) =>  JsonResponse(new value.ApiResponse(400, s"${Utils.errorValidatingUUID}"))

              case Right(id) => {

                listingPropertyData.getListingPropertyById(id) match {

                  case Some(listing) => {
                    val result = listingPropertyData.updateListing(listingModel)
                    Ok(Json.toJson(result))
                  }
                  case _ => JsonResponse(new value.ApiResponse(404, "listings not found"), 404)
                }
              }
            }

          } getOrElse {
            JsonResponse(new value.ApiResponse(405, "Invalid input"))
          }
        }

        case None => JsonResponse(new value.ApiResponse(405, "Invalid input"))
      }
  }

  @ApiOperation(nickname = "deleteListing", value = "Delete a listing by ID", notes = "For valid response try integer IDs with value < 1000. " +
      "Anything above 1000 or nonintegers will generate API errors", httpMethod = "DELETE")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message = "Listing not found")))
  def deleteListing( @ApiParam(value = "ID of the listing that needs to be deleted", required = true) id: String) = Action {
    implicit request =>

      Utils.validateUUID(id) match {

        case Left(error) =>  JsonResponse(new value.ApiResponse(400, s"${Utils.errorValidatingUUID}"))

        case Right(_) => {

          listingPropertyData.getListingPropertyById(id) match {

            case Some(listing) => {
              listingPropertyData.deleteListing(listing)
              Ok
            }
            case _ => JsonResponse(new value.ApiResponse(404, "listings not found"), 404)
          }
        }
      }
  }

}

object ListingPropertyApiController {}
