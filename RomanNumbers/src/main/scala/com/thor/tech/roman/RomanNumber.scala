package com.thor.tech.roman

import scala.annotation.tailrec
import scala.collection.immutable.TreeMap

object RomanNumber {

  val romanCharacters= List(
    ("M", 1000),
    ("CM", 900),
    ("D", 500),
    ("CD", 400),
    ("C", 100),
    ("XC", 90),
    ("L", 50),
    ("XL", 40),
    ("X", 10),
    ("IX", 9),
    ("V", 5),
    ("IV", 4),
    ("I", 1)
  )


  val romanCharactersTreeMap= TreeMap[Int,String](
    1->"I",
    4->"IV",
    5->"V",
    9->"IX",
    10->"X",
    40->"XL",
    50->"L",
    90->"XC",
    100->"C",
    400->"CD",
    500->"D",
    900->"CM",
    1000->"M"
  )


  def convertNumberToRoman(numberToBeConverted:Int):String= toRomanNumerals(numberToBeConverted,romanCharacters)


  private def toRomanNumerals(number: Int, digits: List[(String, Int)]): String = {
    digits match {
      case Nil => ""
      case head :: tail => head._1 * (number / head._2) + toRomanNumerals(number % head._2, tail)

    }
  }

  def convertNumberToRomanMethod2( number:Int ) :String ={

    @tailrec
    def loop (accumulator:String, number:Int ) :String ={

      val greatestDigitList = romanCharactersTreeMap.keys.filter {element => element<= number }
      val greatestDigit = if ( greatestDigitList.isEmpty ) number else greatestDigitList.max
      val greatestRomanDigit = romanCharactersTreeMap.get(greatestDigit).getOrElse("")
      val romanDigitAccumulator= accumulator + greatestRomanDigit
      val newDigitAccumutator = number - greatestDigit

      if (newDigitAccumutator == 0) {
        romanDigitAccumulator
      } else {
        loop(romanDigitAccumulator, newDigitAccumutator)
      }
    }

    loop("",number)
  }

}


