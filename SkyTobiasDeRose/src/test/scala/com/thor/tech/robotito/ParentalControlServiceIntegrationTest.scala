package com.thor.tech.robotito

import com.thirdparty.movie.MovieService
import com.thor.movie.exception.{TechnicalFailureException, TitleNotFoundException}
import com.thor.services.ParentalControlLevelModel
import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

import scala.util.{Failure, Success, Try}


class ParentalControlServiceIntegrationTest extends FlatSpec with Matchers with BeforeAndAfter with MockFactory {

  val mockMovieService = stub[MovieService]
  var service: ParentalControlService = _

  before {
    service = new ParentalControlService(mockMovieService)
  }

  // Negative cases

  it should "not allow to watch when over preference level" in {

    val movieID = "movieID"

    (mockMovieService.getParentalControlLevel _).when(movieID).returns(Try("18"))

    service.evaluatePermission(movieID, ParentalControlLevelModel.OVER_12) should be(Success(false))
  }

  it should "not permit watching when failure in Movie Service" in {

    val movieID = "movieID"

    (mockMovieService.getParentalControlLevel _).when(movieID).returns(Try(throw TechnicalFailureException()))

    service.evaluatePermission(movieID, ParentalControlLevelModel.OVER_18) should be(Success(false))
  }

  it should "Unknown parental level returned by MovieService backend" in {

    val movieID = "movieID"

    (mockMovieService.getParentalControlLevel _).when(movieID).returns(Try("pepeGrillo"))

    service.evaluatePermission(movieID, ParentalControlLevelModel.OVER_12) should be(Failure(new TechnicalFailureException))
  }

  it should "Not expected RuntimeException" in {

    val movieID = "movieID"

    (mockMovieService.getParentalControlLevel _).when(movieID).returns(Try(throw new RuntimeException()))

    service.evaluatePermission(movieID, ParentalControlLevelModel.OVER_15) should be(Failure(new TechnicalFailureException))
  }


  it should "TitleNotFound exception case" in {

    val movieID = "movieID"

    (mockMovieService.getParentalControlLevel _).when(movieID).returns(Try(throw TitleNotFoundException()))

    service.evaluatePermission(movieID, ParentalControlLevelModel.OVER_12) should be(Failure(new TitleNotFoundException))
  }

  // Positive cases

  it should "allow to watch in the preference level" in {

    val movieID = "movieID"

    (mockMovieService.getParentalControlLevel _).when(movieID).returns(Try("U"))

    service.evaluatePermission(movieID, ParentalControlLevelModel.OVER_12) should be(Success(true))
  }

  it should "allow to watch if it matches preference level 15" in {

    val movieID = "movieID"

    (mockMovieService.getParentalControlLevel _).when(movieID).returns(Try("15"))

    service.evaluatePermission(movieID, ParentalControlLevelModel.OVER_15) should be(Success(true))
  }

  it should "allow to watch if it matches preference level 12" in {

    val movieID = "movieID"

    (mockMovieService.getParentalControlLevel _).when(movieID).returns(Try("12"))

    service.evaluatePermission(movieID, ParentalControlLevelModel.OVER_12) should be(Success(true))
  }

}
