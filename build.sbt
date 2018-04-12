name := "Scala_Example"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies ++= {
  val CatsV = "1.0.1"
  val CatsEffect = "0.10"
  val MonixV = "3.0.0-22bf9c6"
  val shapelessV = "2.3.2"
  val twitterV = "18.3.0"
  Seq(
    "com.chuusai" %% "shapeless" % shapelessV,
    "org.typelevel" %% "cats-core" % CatsV,
    "org.typelevel" %% "cats-free" % CatsV,
    "org.typelevel" %% "cats-effect" % CatsEffect,
    "io.monix" %% "monix" % MonixV,
    "org.scalactic" %% "scalactic" % "3.0.1",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "com.twitter" %% "util-core" % twitterV,
    "io.catbird" %% "catbird-util" % twitterV,
    "com.github.julien-truffaut" %%  "monocle-core"  % "1.5.0-cats",
    "com.github.julien-truffaut" %%  "monocle-macro" % "1.5.0-cats",
    "com.github.julien-truffaut" %%  "monocle-law"   % "1.5.0-cats" % "test",
    "org.specs2" %% "specs2-core" % "4.0.2" % "test"
  )
}

scalacOptions in Test ++= Seq("-Yrangepos")

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")


