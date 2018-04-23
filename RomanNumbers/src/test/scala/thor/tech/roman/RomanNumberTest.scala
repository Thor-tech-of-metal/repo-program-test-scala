package thor.tech.roman

import com.thor.tech.roman.RomanNumber
import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}


class RomanNumberTest extends FlatSpec with Matchers with BeforeAndAfter with MockFactory {

  val expactedRomanNumber = Map( 1 ->"I", 2-> "II",  3->"III",4->"IV",5->"V", 6->"VI", 7->"VII", 8->"VIII", 9->"IX", 10->"X")

  val expactedNumbreFromRoman = Map( "I"->1,"II"->1,"III"->1,"IV"->1,"V"->1, "VI"->1, "VII"->1, "VIII"->1, "IX"->1, "X"->1)

  for( number <- 1 to 10){

    it should s"calculate ${number}" in {

      RomanNumber.convertNumberToRoman(number) should be (expactedRomanNumber(number))
    }
  }

  it should "calculate 55" in {

    val expactedPath = "LV"
    RomanNumber.convertNumberToRoman(55) should be (expactedPath)
  }



  it should "calculate II" in {

    val expactedPath = "CCCXLIV"
    RomanNumber.convertNumberToRomanMethod2(344) should be (expactedPath)
  }


  for( number <- 1 to 10){

    it should s"calculate ${number} using method 2" in {

      RomanNumber.convertNumberToRomanMethod2(number) should be (expactedRomanNumber(number))
    }
  }
}
