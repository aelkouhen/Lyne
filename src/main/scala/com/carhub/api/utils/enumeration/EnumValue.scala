package com.carhub.api.utils.enumeration

trait EnumValue{

  this: Enumeration =>

  def valueOf(str: String) = this.values.toList.filter(_.toString == str) match {
    case Nil => null
    case x => x.head
  }
}
