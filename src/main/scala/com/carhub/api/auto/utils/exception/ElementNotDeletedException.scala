package com.carhub.api.auto.utils.exception

class ElementNotDeletedException[A]() extends RuntimeException{

  var element : A = _
  var message : String = "No element of type " + element + " was deleted"
}
