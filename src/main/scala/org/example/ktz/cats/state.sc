import scala.util.Random

def genRandomChar: Char = {
  (Random.nextInt(26) + 65).toChar
}

List(genRandomChar, genRandomChar, genRandomChar).mkString

def genIntXorShift(seed: Int): Int = {
  var x = seed
  x ^= (x << 21)
  x ^= (x >> 35)
  x ^= (x << 4)
  x
}

def genChar(seed: Int): (Int, Char) = {
  val newSeed = genIntXorShift(seed)
  val number = Math.abs(newSeed % 25) + 65
  (newSeed, number.toChar)
}

val initialSeed = 42

val random = {
  val (nextSeed1, first) = genChar(initialSeed)
  val (nextSeed2, second) = genChar(nextSeed1)
  val (_, third) = genChar(nextSeed2)
  List(first, second, third)
}.mkString

import cats.data

val nextCharS = data.State[Int, Char]{ seed =>
  genChar(seed)
}

val random2 = for {
  first <- nextCharS
  second <- nextCharS
  third <- nextCharS
} yield List(first, second, third)

