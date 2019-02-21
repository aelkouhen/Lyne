package com.carhub.api.auto.utils.exception

import org.hibernate.TypeMismatchException
import org.springframework.beans.ConversionNotSupportedException
import org.springframework.core.annotation.Order
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.http.converter.{HttpMessageNotReadableException, HttpMessageNotWritableException}
import org.springframework.validation.BindException
import org.springframework.web.bind.{MethodArgumentNotValidException, MissingPathVariableException, MissingServletRequestParameterException, ServletRequestBindingException}
import org.springframework.web.{HttpMediaTypeNotAcceptableException, HttpMediaTypeNotSupportedException, HttpRequestMethodNotSupportedException}
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler}
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.async.AsyncRequestTimeoutException
import org.springframework.web.multipart.support.MissingServletRequestPartException
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException


@Order(0)
@ControllerAdvice
class ApiErrorHandler {

  @ExceptionHandler(Array(classOf[Exception],
    classOf[ElementNotFoundException[_]],
    classOf[NoSuchRequestHandlingMethodException],
    classOf[HttpRequestMethodNotSupportedException],
    classOf[HttpMediaTypeNotSupportedException],
    classOf[HttpMediaTypeNotAcceptableException],
    classOf[NoSuchRequestHandlingMethodException],
    classOf[MissingPathVariableException],
    classOf[MissingServletRequestParameterException],
    classOf[ServletRequestBindingException],
    classOf[ConversionNotSupportedException],
    classOf[TypeMismatchException],
    classOf[HttpMessageNotReadableException],
    classOf[HttpMessageNotWritableException],
    classOf[MethodArgumentNotValidException],
    classOf[MissingServletRequestPartException],
    classOf[BindException],
    classOf[NoHandlerFoundException],
    classOf[AsyncRequestTimeoutException]))
  def handleAll(ex: Exception, request: WebRequest): ResponseEntity[ApiError] = ex match{
      case ex : ElementNotFoundException[_] => buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND,"error" , ex))
      case ex : NoSuchRequestHandlingMethodException => buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND,"error" , ex))
      case ex : HttpRequestMethodNotSupportedException => buildResponseEntity(new ApiError(HttpStatus.METHOD_NOT_ALLOWED,"error" , ex))
      case ex : HttpMediaTypeNotSupportedException => buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,"error" , ex))
      case ex : HttpMediaTypeNotAcceptableException => buildResponseEntity(new ApiError(HttpStatus.NOT_ACCEPTABLE,"error" , ex))
      case ex : MissingPathVariableException => buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"error" , ex))
      case ex : MissingServletRequestParameterException => buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"error" , ex))
      case ex : ServletRequestBindingException => buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"error" , ex))
      case ex : ConversionNotSupportedException => buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"error" , ex))
      case ex : TypeMismatchException => buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"error" , ex))
      case ex : HttpMessageNotReadableException => buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"error" , ex))
      case ex : HttpMessageNotWritableException => buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"error" , ex))
      case ex : MethodArgumentNotValidException => buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"error" , ex))
      case ex : MissingServletRequestPartException => buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"error" , ex))
      case ex : BindException => buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"error" , ex))
      case ex : NoHandlerFoundException => buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND,"error" , ex))
      case ex : AsyncRequestTimeoutException => buildResponseEntity(new ApiError(HttpStatus.SERVICE_UNAVAILABLE,"error" , ex))
      case _ : Throwable => buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"error" , ex))
  }

  private def buildResponseEntity(error : ApiError) = ResponseEntity.status(error.httpStatus).body(error)
}