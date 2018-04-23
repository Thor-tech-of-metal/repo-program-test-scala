package com.thor.tech.robotito

import com.thirdparty.movie.MovieService
import com.thor.movie.exception.{TechnicalFailureException, TitleNotFoundException}
import com.thor.services.ParentalControlLevelModel
import com.thor.services.ParentalControlLevelModel.ParentalControlLevel

import scala.util.Try

case class ParentalControlService(movieService: MovieService) {


  ///https://www.youtube.com/watch?v=Itn3WIhQ6NQ

  def evaluatePermission(movieUID: String, preference: ParentalControlLevel): Try[Boolean] = {

    movieService.getParentalControlLevel(movieUID)
      .map(ParentalControlLevelModel.withName)
      .map(preference.id >= _.id)
      .recover {
        case exception: TechnicalFailureException => false
        case exception: TitleNotFoundException => throw exception
        case exception: RuntimeException => throw TechnicalFailureException() }

  }
}
