package com.thor.movie.exception


case class TitleNotFoundException() extends RuntimeException

case class TechnicalFailureException() extends RuntimeException