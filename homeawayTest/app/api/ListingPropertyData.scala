package api

import models._
import javax.inject._

import scala.collection.mutable.ListBuffer

@Singleton
case class ListingPropertyData() {

  val listings: ListBuffer[Listing] = new ListBuffer[Listing]()

  {
    listings += Listing(
                    id = Some("5e22a83a-6f4f-11e6-8b77-86f30ca893d3"),
                    contact = Contact( phone= "15126841100",  formattedPhone = "+1 512-684-1100" ),
                    address = List(
                                    Address(
                                    address =  "1011 W 5th St",
                                    postalCode =  "1011",
                                    countryCode =  "US",
                                    city =  "Austin",
                                    state =  "TX",
                                    country =  "United States" )),
                    location =  Location("40.4255485534668", "-3.7075681686401367"))
  }

  def getListingPropertyById(id: String): Option[Listing]={

    listings.filter( listing => listing.id.map { uuid => uuid == id } getOrElse(false) ) match {

      case listings if(listings.size) > 0 => Some(listings.head)
      case _ => None
    }
  }


  def addListing(listingWithId : Listing): Listing = {

    listings += listingWithId
    listingWithId
  }

  def updateListing(listing: Listing): Listing = {

    listings --= listings.filter(existing => existing.id == listing.id)
    listings += listing
    listing
  }

  def deleteListing(listing: Listing) = {

    listings --= listings.filter(existing => existing.id == listing.id)
  }
}

object ListingPropertyData