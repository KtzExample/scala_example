package com.ktz.ASWC.Cap1

/**
  * Created by ktz on 2016. 10. 30..
  */
trait Json

final case class JsObject(get : Map[String, Json]) extends Json
final case class JsString(get : String) extends Json
final case class JsNumber(get : Double) extends Json

trait JsonWriter[A]{
  def write(value : A) : Json
}

final case class Person(name : String, email : String)

object DefaultJsonWriters {
  implicit val stringJsonWriter = new JsonWriter[String] {
    override def write(value: String): Json = JsString(value)
  }

  implicit val personJsonWriter = new JsonWriter[Person] {
    override def write(value: Person): Json =
      JsObject(Map("name" -> JsString(value.name),
        "email" -> JsString(value.email)))
  }
}

object Json {
  def toJson[A](value: A)(implicit writer: JsonWriter[A]) : Json =
    writer.write(value)
}

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit writer: JsonWriter[A]) : Json = {
      writer.write(value)
    }
  }
}


object JsonMain extends App{
  import DefaultJsonWriters._
  import JsonSyntax._
  val json : Json = Json.toJson(Person("Dave", "dave@example.com"))
  Person("Dave", "dave@example.com").toJson
}