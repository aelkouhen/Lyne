package com.carhub.api.auto.utils.jsonapi

import java.util
import java.util.Optional

import scala.collection.immutable.Traversable
import scala.collection.{GenTraversableOnce, JavaConverters, mutable}

object JsonApiHelper {
  def isOption(clazz: Class[_]): Boolean = {
    val optionTypes = List(
      classOf[util.Optional[_]],
      classOf[Option[_]]
    )
    optionTypes.exists(_.isAssignableFrom(clazz))
  }

  def isList(clazz: Class[_]): Boolean = {
    val collectionList = List(
      classOf[util.Collection[_]],
      classOf[Traversable[_]],
      classOf[mutable.Traversable[_]]
    )
    collectionList.exists(_.isAssignableFrom(clazz))
  }

  def toIterable(data: Any): GenTraversableOnce[_] = {
    if (Option(data).isEmpty) {
      Nil
    } else if (classOf[GenTraversableOnce[_]].isAssignableFrom(data.getClass())) {
      data.asInstanceOf[GenTraversableOnce[_]]
    } else {
      JavaConverters.collectionAsScalaIterable(data.asInstanceOf[util.Collection[_]])
    }
  }

  def extractOptionalValue(value: AnyRef) : AnyRef= value match {
    case scalaOption: Option[AnyRef] => scalaOption.orNull
    case javaOption: Optional[AnyRef] => if (javaOption.isPresent) javaOption.get() else null
    case _ => value
  }
}
