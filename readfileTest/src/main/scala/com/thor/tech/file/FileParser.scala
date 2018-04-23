package com.thor.tech.file

import com.thor.tech.file.datamodel.DataModel

import scala.io.Source


case class FileParser (file:String)  {

  def parseFile(): List[DataModel] = {

    val bufferedSource = Source.fromFile(file)
    val fileLines = bufferedSource.getLines().toList

    def loop (fileLines:List[String],dataModelAccumulator:List[DataModel] ): List[DataModel] = {

      fileLines match {
        case head::tail =>

          if (isTheCurrentLineAHeader(head)) loop(tail,dataModelAccumulator )
          else loop(tail,dataModelAccumulator:::List( populateDataModel(head)) )

        case Nil => dataModelAccumulator
      }
    }

    val result =loop(fileLines,List.empty)
    bufferedSource.close
    result
  }

  def populateDataModel(line:String):DataModel ={

    val array = line.split(",")
    DataModel(array(0),array(1))
  }

  def isTheCurrentLineAHeader(line:String) : Boolean = line.contains("#")

}
