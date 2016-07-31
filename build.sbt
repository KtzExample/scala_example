name := "Scala_Example"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val CatsV = "0.6.1"
  Seq(
    "org.typelevel" %% "cats" % CatsV
  )
}
