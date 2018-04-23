package com.thor.services

object ParentalControlLevelModel extends Enumeration {

  type ParentalControlLevel = Value

  val U = Value(0, "U")
  val PG = Value(1, "PG")
  val OVER_12 = Value(2,"12")
  val OVER_15 = Value(3, "15")
  val OVER_18 = Value(4, "18")
}

