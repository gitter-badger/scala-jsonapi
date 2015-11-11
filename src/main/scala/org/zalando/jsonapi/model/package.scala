package org.zalando.jsonapi

import collection.immutable.{ Seq ⇒ ImmutableSeq }

/**
 * The model package, containing partially covered Jsonapi specification.
 */
package object model {

  /**
   * A root, top-level object.
   */
  case class RootObject(data: Data)

  /**
   * The root object's "primary data".
   * @param `type` the type of the root object
   * @param id the ID of the root object
   * @param attributes the optional list of attributes of the root object
   */
  case class Data(`type`: String, id: String, attributes: Option[Attributes])

  type Attributes = ImmutableSeq[Attribute]

  /**
   * The representation of an attribute of the root object.
   * @param name the name of the attribute
   * @param value the value of the attribute
   */
  case class Attribute(name: String, value: Attribute.Value)

  /**
   * Companion object of the [[Attribute]] type.
   */
  object Attribute {

    /**
     * Sum type for attribute values.
     */
    sealed trait Value

    /**
     * An attribute value that is string-typed.
     * @param value the string value
     */
    case class StringValue(value: String) extends Value

    /**
     * An attribute value that is number-typed.
     * @param value the number value
     */
    case class NumberValue(value: BigDecimal) extends Value

    /**
     * An attribute value that is boolean-typed.
     * @param value the boolean value
     */
    case class BooleanValue(value: Boolean) extends Value

    /**
     * An attribute value that is list(key, value)-typed.
     * @param value the list of key-value pairs
     */
    case class JsObjectValue(value: Attributes) extends Value

    /**
     * An attribute value that is array-typed.
     * @param value the array value
     */
    case class JsArrayValue(value: Seq[Value]) extends Value

    /**
     * An attribute value that is null.
     */
    case object NullValue extends Value
  }
}
