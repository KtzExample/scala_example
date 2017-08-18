name := "Scala_Example"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val CatsV = "0.9.0"
  val MonixV = "2.3.0"
  val shapelessV = "2.3.2"
  Seq(
    "com.chuusai" %% "shapeless" % shapelessV,
    "org.typelevel" %% "cats" % CatsV,
    "io.monix" %% "monix" % MonixV,
    "io.monix" %% "monix-cats" % MonixV,
    "org.scalactic" %% "scalactic" % "2.2.6",
    "org.scalatest" %% "scalatest" % "2.2.6" % "test"
  )
}
