package com.carhub.api.auto.utils.exception

class ServiceUnavailableException(val serviceName : String) extends RuntimeException{
  override def getMessage: String = "Service " + serviceName + " is down"
}
