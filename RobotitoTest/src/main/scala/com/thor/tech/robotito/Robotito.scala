package com.thor.tech.robotito

import scala.annotation.tailrec

object Robotito {

  def calculatePath (pathCoordinates: String) : (Int,Int) = {

    val pathSeq:Seq[String] = pathCoordinates.split(",")
    
    @tailrec
    def calculateAccumulativePath(pathSeq:List[String], accumulator:(Int,Int)):(Int,Int) ={

      pathSeq match {
        case head::tail if head =="N" => calculateAccumulativePath(tail,( accumulator._1, accumulator._2 +1 ) )
        case head::tail if head =="S" => calculateAccumulativePath(tail,( accumulator._1, accumulator._2 -1) )
        case head::tail if head =="E" => calculateAccumulativePath(tail,( accumulator._1+1, accumulator._2 ) )
        case head::tail if head =="W" => calculateAccumulativePath(tail,( accumulator._1-1, accumulator._2 ) )
        case Nil => accumulator
      }
    }

    calculateAccumulativePath(pathSeq.toList,(0,0))
  }
}
