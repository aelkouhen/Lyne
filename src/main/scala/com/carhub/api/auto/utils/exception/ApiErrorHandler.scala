package com.carhub.api.auto.utils.exception

import org.springframework.beans.{ConversionNotSupportedException, TypeMismatchException}
import org.springframework.core.annotation.Order
import org.springframework.core.Ordered
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.{HttpMessageNotReadableException, HttpMessageNotWritableException}
import org.springframework.validation.BindException
import org.springframework.web.bind.{MethodArgumentNotValidException, MissingPathVariableException, MissingServletRequestParameterException, ServletRequestBindingException}
import org.springframework.web.{HttpMediaTypeNotAcceptableException, HttpMediaTypeNotSupportedException, HttpRequestMethodNotSupportedException}
import org.springframework.web.bind.annotation.{ExceptionHandler, RestControllerAdvice}
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.async.AsyncRequestTimeoutException
import org.springframework.web.multipart.support.MissingServletRequestPartException
import org.springframework.web.servlet.NoHandlerFoundException

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
class ApiErrorHandler extends ResponseEntityExceptionHandler{

  override protected def handleExceptionInternal(ex: Exception, body : Object, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleAsyncRequestTimeoutException(ex: AsyncRequestTimeoutException, headers: HttpHeaders, status: HttpStatus, webRequest: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleBindException(ex: BindException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleConversionNotSupported(ex: ConversionNotSupportedException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleHttpMediaTypeNotAcceptable(ex: HttpMediaTypeNotAcceptableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleHttpMediaTypeNotSupported(ex: HttpMediaTypeNotSupportedException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleHttpMessageNotWritable(ex: HttpMessageNotWritableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleHttpRequestMethodNotSupported(ex: HttpRequestMethodNotSupportedException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleMissingPathVariable(ex: MissingPathVariableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleMissingServletRequestParameter(ex: MissingServletRequestParameterException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleMissingServletRequestPart(ex: MissingServletRequestPartException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleNoHandlerFoundException(ex: NoHandlerFoundException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleServletRequestBindingException(ex: ServletRequestBindingException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  override def handleTypeMismatch(ex: TypeMismatchException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(status, if (status.is4xxClientError() || status.is5xxServerError()) "error" else "info", ex))
  }

  @ExceptionHandler(Array(classOf[RuntimeException]))
  def handleAll(ex: RuntimeException, headers: HttpHeaders, request: WebRequest): ResponseEntity[AnyRef] = {
    buildResponseEntity(headers, new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"error", ex))
  }

  private def buildResponseEntity(httpHeaders: HttpHeaders, error : ApiError) = new ResponseEntity[AnyRef](error, httpHeaders, error.httpStatus)
}