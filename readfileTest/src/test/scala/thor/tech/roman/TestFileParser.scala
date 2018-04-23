package thor.tech.roman

import com.thor.tech.file.FileParser
import com.thor.tech.file.datamodel.DataModel
import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}


class TestFileParser extends FlatSpec with Matchers with BeforeAndAfter with MockFactory {

  private val fileName:String = "/home/skynet/mis_cosas/repositories/scala/repo-interview-test/readfileTest/src/main/resources/test.txt"

  it should s" populate data model correctly" in {

    val expectedMode = DataModel("tobias","eduardo")
    val inputLine = "tobias,eduardo"
    val fileParser= new FileParser(fileName)

    fileParser.populateDataModel(inputLine) should be (expectedMode)
  }

  it should s" not populate data model. Becasue this is a head" in {

    val inputLine = "# this is the head"
    val fileParser= new FileParser(fileName)

    fileParser.isTheCurrentLineAHeader(inputLine) should be (true)
  }


  it should s" populate all data model correctly" in {

    val expectedMode = List( DataModel("Tobias","Eduardo"),DataModel("Raymond","Pellicano") )
    val fileParser= new FileParser(fileName)

    fileParser.parseFile() should be (expectedMode)
  }

}
