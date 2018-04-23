package util


import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class TestUtils extends FlatSpec with Matchers with BeforeAndAfter {


  it should s" Evaluate UUID" in {

    val inputUIID = "f7f952bd-b954-4599-a9b2-ab0428405e76"
    Utils.validateUUID(inputUIID) should be (Right(inputUIID))
  }

  it should s" Evaluate UUID with error" in {

    val inputUIID = "\\m/"
    Utils.validateUUID(inputUIID) should be (Left(Utils.errorValidatingUUID))
  }



}
