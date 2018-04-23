package util

import java.util.UUID

import api.ListingPropertyData
import models.{Address, Contact, Listing, Location}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class ListingPropertyDataTest extends FlatSpec with Matchers with BeforeAndAfter {


  it should s" evaluate getListingPropertyById method using predefined listed property " in {

    val inputUIID = "5e22a83a-6f4f-11e6-8b77-86f30ca893d3"
    val expectedListedProperty = Listing(
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

     new ListingPropertyData().getListingPropertyById(inputUIID)  should be (Some(expectedListedProperty))
  }


  //getListingPropertyById 1) add a record 2) find it 3) compare the result with the input data.
  it should s" evaluate getListingPropertyById method using added data" in {

    val listingPropertyData = new ListingPropertyData()
    val predefinedInput = Listing(
      id = Some(UUID.randomUUID().toString),
      contact = Contact( phone= "15126841100",  formattedPhone = "+1 512-684-1100" ),
      address = List(
        Address(
          address =  "1011 W 5th St",
          postalCode =  "1011",
          countryCode =  "US",
          city =  "Austin",
          state =  "TX",
          country =  "United States" )),
      location =  Location("40.4255485534668", "-3.7075681686401367")
    )

    val inputToBeFound = listingPropertyData.addListing(predefinedInput)
    val getByIdResult = listingPropertyData.getListingPropertyById(inputToBeFound.id.get)

    // the found should be equals to the predefined one.
    getByIdResult should be (Some(predefinedInput))
  }

  //Add listing Test.
  it should s" evaluate addListing method " in {

    val input = Listing(
      id = Some(UUID.randomUUID().toString),
      contact = Contact( phone= "15126841100",  formattedPhone = "+1 512-684-1100" ),
      address = List(
        Address(
          address =  "1011 W 5th St",
          postalCode =  "1011",
          countryCode =  "US",
          city =  "Austin",
          state =  "TX",
          country =  "United States" )),
      location =  Location("40.4255485534668", "-3.7075681686401367")
    )

    val result = new ListingPropertyData().addListing(input)

    result should be (input)
  }

  //Update listing Test 1) create a listing 2) update 3) evaluate it.
  it should s" evaluate updateListing method " in {

    val listingPropertyData = new ListingPropertyData()
    val predefinedInput = Listing(
      id = Some(UUID.randomUUID().toString),
      contact = Contact( phone= "15126841100",  formattedPhone = "+1 512-684-1100" ),
      address = List(
        Address(
          address =  "1011 W 5th St",
          postalCode =  "1011",
          countryCode =  "US",
          city =  "Austin",
          state =  "TX",
          country =  "United States" )),
      location =  Location("40.4255485534668", "-3.7075681686401367")
    )

    val result = listingPropertyData.addListing(predefinedInput)

    val inputToBeUpdated = predefinedInput.copy(location = Location("40", "-3"))
    val updatedResult = listingPropertyData.updateListing(inputToBeUpdated)

    // the updated result record should the equals to the predefined data.
    updatedResult should be (inputToBeUpdated)
  }


  //remove listing 1) create a pre-defined record 2) remove it 3) try to find it.
  it should s" evaluate deleteListing method " in {

    val listingPropertyData = new ListingPropertyData()
    val predefinedInput = Listing(
      id = Some(UUID.randomUUID().toString),
      contact = Contact( phone= "15126841100",  formattedPhone = "+1 512-684-1100" ),
      address = List(
        Address(
          address =  "1011 W 5th St",
          postalCode =  "1011",
          countryCode =  "US",
          city =  "Austin",
          state =  "TX",
          country =  "United States" )),
      location =  Location("40.4255485534668", "-3.7075681686401367")
    )

    val inputToBeRemoved = listingPropertyData.addListing(predefinedInput)

    listingPropertyData.deleteListing(inputToBeRemoved)

    val getByIdResult = listingPropertyData.getListingPropertyById(inputToBeRemoved.id.get)

    // the inputToBeRemoved should not be found.
    getByIdResult should be (None)
  }

}
