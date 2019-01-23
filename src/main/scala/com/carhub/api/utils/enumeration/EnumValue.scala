package com.carhub.api.utils.enumeration

trait EnumValue{

  this: Enumeration =>

  private var nameDescriptionMap = scala.collection.mutable.Map[String, String]()

  def Value(name: String, desc: String) = {
    nameDescriptionMap += (name -> desc)
    new Val(name)
  }

  def getDescriptionOrName(ev: this.Value) = {
    try {
      nameDescriptionMap(""+ev)
    } catch {
      case e: NoSuchElementException => ev.toString
    }
  }

  def getNameDescriptionList =  this.values.toList.map(v => (v.toString, getDescriptionOrName(v) ) ).toList

  /* get the enum given a string */
  def valueOf(str: String) = this.values.toList.filter(_.toString == str) match {
    case Nil => null
    case x => x.head
  }
}
