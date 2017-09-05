
import shapeless.{::, Generic, HList, HNil}
import shapeless.ops.hlist.Last

val last1 = Last[String :: Int :: HNil]
val last2 = Last[Int :: String :: HNil]

last1("foo" :: 123 :: HNil)
last2(321 :: "bar" :: HNil)

import org.example.ktz.shapeless.chap4._
import org.example.ktz.shapeless.chap4.Second._

val second1 = Second[String :: Boolean :: Int :: HNil]

val second2 = Second[String :: Int :: Boolean :: HNil]

//Second[String :: HNil]

second1("foo" :: true :: 123 :: HNil)
second2("foo" :: 321 :: false :: HNil)

import RectUtil.lastField

lastField(Rect(Vec(1, 2), Vec(3, 4)))