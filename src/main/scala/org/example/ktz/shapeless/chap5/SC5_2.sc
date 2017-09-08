import shapeless.labelled.{KeyTag, FieldType}
import shapeless.syntax.singleton._

trait Cherries

val someNumber = 123

val numCherries = "numCherries" ->> someNumber

import shapeless.labelled.field

field[Cherries](123)

import shapeless.Witness

def getFieldName[K, V](value: FieldType[K, V])
                      (implicit witness: Witness.Aux[K]): K = witness.value

getFieldName(numCherries)

def getFieldValue[K, V](value: FieldType[K, V]): V = value

getFieldValue(numCherries)

import shapeless.{HList, ::, HNil}

val garfield = ("Cat" ->> "Garfield") :: ("orange" ->> true) :: HNil

getFieldName(garfield.head)