package com.carhub.api.auto.utils.exception

class ElementNotUpdatedException[A]() extends RuntimeException{

  var element : A = _
  var message : String = "No element of type " + element + " was updated"
}
