def getSome(a: Int): Option[Int] = Some(a)

def getNone(a: Int): Option[Int] = None

def add(a: Int, b: Int): Int = a + b

val aOpt = getSome(1)

val bOpt = getSome(2)

aOpt.flatMap(a => bOpt.map(b => add(a, b)))

import cats.instances.option._
import cats.Applicative

Applicative[Option].map2(aOpt, bOpt)(add)

import cats.syntax.all._

(aOpt |@| bOpt).map(add)

val ints = List(1,2,3,4,5)
ints.map(getSome)

import cats.Traverse
import cats.instances.list._
import cats.instances.option._

Traverse[List].traverse(ints)(getSome)

def getSomeOrNone(a: Int): Option[Int] =
  if(a % 2 == 0) Some(a)
  else None

Traverse[List].traverse(ints)(getSomeOrNone)
