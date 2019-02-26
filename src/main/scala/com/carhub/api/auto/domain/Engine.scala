package com.carhub.api.auto.domain

import com.carhub.api.auto.utils.enumeration.{EnumValue, EnumValueType}
import com.carhub.api.auto.utils.jsonapi.annotations.{JsonApi, JsonApiId}
import com.fasterxml.jackson.core.{JsonGenerator, JsonParser}
import com.fasterxml.jackson.databind.annotation.{JsonDeserialize, JsonSerialize}
import com.fasterxml.jackson.databind.{DeserializationContext, JsonDeserializer, JsonSerializer, SerializerProvider}
import javax.persistence._
import org.hibernate.annotations.Type

import scala.beans.BeanProperty

object Fuel extends Enumeration with EnumValue{
  type Kind = Value
  val UNSPECIFIED, DIESEL, HYBRID, ELECTRICAL, LPG, CNG, HYDROGEN, GAS, ETHANOL = Value

  class FuelJsonSerializer extends JsonSerializer[Kind] {
    override def serialize(value: Kind, gen : JsonGenerator, provider: SerializerProvider)= {
      gen.writeStartObject
      gen.writeStringField("kind", value.toString);
      gen.writeEndObject();
    }
  }

  class FuelJsonDeserializer extends JsonDeserializer[Kind] {
    override def deserialize(jsonParser : JsonParser, context: DeserializationContext): Kind = Fuel.valueOf(jsonParser.getText)
  }
}
class FuelType extends EnumValueType(Fuel){}

object Injection extends Enumeration with EnumValue{
  //TDI= Turbocharged Direct Injection
  //SDI = Standard Diesel Injection
  //SPI= Single Point Injection
  //MPI= Multi-Point Injection
  //CPFI= Central Port Fuel Injection
  //SPFI= Sequential Port Fuel Injection
  //CRDI= Common Rail Direct injection
  //HDI= High Pressure Direct Injection
  type System = Value
  val HYBRID, SDI, CRDI, CARBURETTOR, SPI, HDI, DIRECT_INJECTION, MPI, SPFI = Value

  class InjectionJsonSerializer extends JsonSerializer[System] {
    override def serialize(value: System, gen : JsonGenerator, provider: SerializerProvider)= {
      gen.writeStartObject
      gen.writeStringField("system", value.toString);
      gen.writeEndObject();
    }
  }

  class InjectionJsonDeserializer extends JsonDeserializer[System] {
    override def deserialize(jsonParser : JsonParser, context: DeserializationContext): System = Injection.valueOf(jsonParser.getText)
  }
}
class InjectionSystemType extends EnumValueType(Injection) {}

object Cylinder extends Enumeration with EnumValue{
  type Position = Value
  val V_ENGINE, W_ENGINE, BOXER, WANKEL, INLINE = Value

  class CylinderJsonSerializer extends JsonSerializer[Position] {
    override def serialize(value: Position, gen : JsonGenerator, provider: SerializerProvider)= {
      gen.writeStartObject
      gen.writeStringField("position", value.toString);
      gen.writeEndObject();
    }
  }

  class CylinderJsonDeserializer extends JsonDeserializer[Position] {
    override def deserialize(jsonParser : JsonParser, context: DeserializationContext): Position = Cylinder.valueOf(jsonParser.getText)
  }
}
class CylinderPositionType extends EnumValueType(Cylinder){}

object Turbine extends Enumeration with EnumValue{
  /*
  TWIN_POWER_TURBO= Twin-Scroll Turbo
  VGT= Variable Geometry Turbocharger
  VTS= Variable Twin-Scroll Turbocharger
  */
  type System = Value
  val COMPRESSOR, TURBO, TWIN_TURBO , TWIN_SCROLL_TURBO, VGT, VTS, ELECTRIC_TURBO = Value

  class TurbineJsonSerializer extends JsonSerializer[System] {
    override def serialize(value: System, gen : JsonGenerator, provider: SerializerProvider)= {
      gen.writeStartObject
      gen.writeStringField("system", value.toString);
      gen.writeEndObject();
    }
  }

  class TurbineJsonDeserializer extends JsonDeserializer[System] {
    override def deserialize(jsonParser : JsonParser, context: DeserializationContext): System = Turbine.valueOf(jsonParser.getText)
  }
}
class TurbineSystemType extends EnumValueType(Turbine){}

@Entity
@Table(name = "engine")
@JsonApi(apiType = "engine")
class Engine extends Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @JsonApiId
  var id: Long = _

  //The name of the engine.
  @BeanProperty
  var name: String = _

  //The volume swept by all of the pistons inside the cylinders of an internal combustion engine in a single movement.
  //Typical unit code(s): CMQ for cubic centimeter
  @BeanProperty
  var engineDisplacement: Double = _

  //The power of the vehicle's engine. Typical unit code(s): KWT for kilowatt.
  @BeanProperty
  var enginePower: Double  = _

  //The type of fuel injection system powering the engine.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.InjectionSystemType")
  @JsonSerialize(using = classOf[Injection.InjectionJsonSerializer])
  @JsonDeserialize(using = classOf[Injection.InjectionJsonDeserializer])
  var injection: Injection.System = _

  //The type of turbine system in the engine.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.TurbineSystemType")
  @JsonSerialize(using = classOf[Turbine.TurbineJsonSerializer])
  @JsonDeserialize(using = classOf[Turbine.TurbineJsonDeserializer])
  var turbine: Turbine.System = _

  //The type of fuel suitable for the engine.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.FuelType")
  @JsonSerialize(using = classOf[Fuel.FuelJsonSerializer])
  @JsonDeserialize(using = classOf[Fuel.FuelJsonDeserializer])
  var fuel: Fuel.Kind = _

  //The torque (turning force) of the vehicle's engine.
  //Typical unit code(s): NU for newton metre (N m)
  @BeanProperty
  var torque: Int = _

  //The number of cylinders.
  @BeanProperty
  var numberOfCylinders: Int = _

  //The position of the cylinders.
  @BeanProperty
  @Type(`type` = "com.carhub.api.auto.domain.CylinderPositionType")
  @JsonSerialize(using = classOf[Cylinder.CylinderJsonSerializer])
  @JsonDeserialize(using = classOf[Cylinder.CylinderJsonDeserializer])
  var cylinders: Cylinder.Position = _

  //The bore is the diameter the cylinder in which a piston travels in (mm).
  @BeanProperty
  var cylinderBore: Double = _

  //The Piston Stroke.
  @BeanProperty
  var pistonStroke: Double = _

  //The Compression ratio of the piston.
  @BeanProperty
  var compressionRatio: Double = _

  //The Number of valves per cylinder.
  @BeanProperty
  var valvesPerCylinder: Int = _

  //The Engine Oil capacity in (l).
  @BeanProperty
  var oilCapacity: Double = _

  //The Engine Coolant capacity in (l).
  @BeanProperty
  var coolantCapacity: Double = _
}
