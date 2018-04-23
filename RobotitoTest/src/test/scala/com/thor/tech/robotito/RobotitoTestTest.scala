package com.thor.tech.robotito

import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}



class RobotitoTestTest extends FlatSpec with Matchers with BeforeAndAfter with MockFactory {

  it should "calculate a single initial move" in {

    val expactedPath = (0,1)
    Robotito.calculatePath("N") should be (expactedPath)
  }


  it should "calculate a path  move" in {

    val expactedPath: (Int,Int) = (2,2)
    Robotito.calculatePath("N,N,E,E") should be (expactedPath)
  }

}
