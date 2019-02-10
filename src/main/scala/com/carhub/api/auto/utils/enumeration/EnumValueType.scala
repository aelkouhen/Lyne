
package com.carhub.api.auto.utils.enumeration

import java.io.Serializable
import java.sql.{PreparedStatement, ResultSet, Types}

import org.hibernate.engine.spi.SessionImplementor
import org.hibernate.usertype.UserType

/**
 * Helper class to translate enum for hibernate
 */

abstract class EnumValueType(val et: Enumeration with EnumValue) extends UserType {

  override def sqlTypes() =  Array(Types.VARCHAR)

  override def returnedClass = classOf[et.Value]

  override def equals(x: Object, y: Object): Boolean =  x == y

  override def hashCode(x: Object) = x.hashCode()

  override def nullSafeGet(resultSet: ResultSet, names: Array[String], session: SessionImplementor, owner: Any): AnyRef = {
    val value = resultSet.getString(names(0))
    if (resultSet.wasNull()) return null
    else {
      return et.valueOf(value)
    }
  }

  override def nullSafeSet(statement: PreparedStatement, value: Any, index: Int, session: SessionImplementor): Unit = {
    if (value == null) {
      statement.setNull(index, Types.VARCHAR)
    } else {
      val en = value.toString
      statement.setString(index, en)
    }
  }

  override def deepCopy(value: Object): Object = value

  override def isMutable() = false

  override def disassemble(value: Object) = value.asInstanceOf[Serializable]

  override def assemble(cached: Serializable, owner: Object): Object = cached.asInstanceOf[Object]

  override def replace(original: Object, target: Object, owner: Object) = original

}

