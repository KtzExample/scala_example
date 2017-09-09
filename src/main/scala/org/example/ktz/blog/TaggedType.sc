val userId: Long = 12
val deviceUuid: Long = 1234
val carSerialId: Long = 12345

def getHashCode(
 userId: Long,
 deviceUuid: Long,
 carSerialId: Long
): String = {
  val userIdPlus: Long = userId + 1
  val deviceUuidPlus: Long = deviceUuid + 2
  val carSerialIdPlus: Long = carSerialId + 3
  s"$userIdPlus-$deviceUuidPlus-$carSerialIdPlus"
}

getHashCode(userId, deviceUuid, carSerialId) // Right Answer = res0: String = 13-1236-12348

getHashCode(deviceUuid, userId, carSerialId) // Wrong Answer = res1: String = 1235-14-12348

// Lets do it by case class
case class UserIdCaseClass(userId: Long)
case class DeviceUuidCaseClass(deviceUuid: Long)
case class CarSerialIdCaseClass(carSerialId: Long)

def getHashCodeCaseClass(
  userIdCaseClass: UserIdCaseClass,
  deviceUuidCaseClass: DeviceUuidCaseClass,
  carSerialIdCaseClass: CarSerialIdCaseClass
): String = {
  val userIdPlus: Long = userIdCaseClass.userId + 1
  val deviceUuidPlus: Long = deviceUuidCaseClass.deviceUuid + 2
  val carSerialIdPlus: Long = carSerialIdCaseClass.carSerialId + 3
  s"$userIdPlus-$deviceUuidPlus-$carSerialIdPlus"
}

getHashCodeCaseClass(UserIdCaseClass(userId), DeviceUuidCaseClass(deviceUuid), CarSerialIdCaseClass(carSerialId)) // Right Answer = res2: String = 13-1236-12348
//getHashCodeCaseClass(DeviceUuidCaseClass(deviceUuid), UserIdCaseClass(userId), CarSerialIdCaseClass(carSerialId)) // Not Even Compiled!

import shapeless.tag
import shapeless.tag.@@

trait UserIdTag
trait DeviceUuidTag
trait CarSerialIdTag

type UserId = Long @@ UserIdTag
type DeviceUuid = Long @@ DeviceUuidTag
type CarSerialId = Long @@ CarSerialIdTag

def getHashCodeTagged(
 userId: UserId,
 deviceUuid: DeviceUuid,
 carSerialId: CarSerialId
): String = {
  val userIdPlus: Long = userId + 1
  val deviceUuidPlus: Long = deviceUuid + 2
  val carSerialIdPlus: Long = carSerialId + 3
  s"$userIdPlus-$deviceUuidPlus-$carSerialIdPlus"
}

val taggedUserId: UserId = tag[UserIdTag][Long](userId)
val taggedDeviceUuid: DeviceUuid = tag[DeviceUuidTag][Long](deviceUuid)
val taggedCarSerialId: CarSerialId = tag[CarSerialIdTag][Long](carSerialId)

getHashCodeTagged(taggedUserId, taggedDeviceUuid, taggedCarSerialId) // Right Answer = res2: String = 13-1236-12348
//getHashCodeTagged(taggedDeviceUuid, taggedCarSerialId, taggedUserId) // Not Even Compiled


// Can't Override
def getId(userId: UserId): Long = userId + 1
//def getId(deviceUuid: DeviceUuid): Long = deviceUuid + 2      // Not Compiled because tagged type erased after compile
//def getId(carSerialId: CarSerialId): Long = carSerialId + 3   // Not Compiled because tagged type erased after compile

// Let's do by either
def getUserIdOrDeviceIdOrCarSerialId(
 id: Either[UserId, Either[DeviceUuid, CarSerialId]]
): Long = id match {
  case Left(userId) => userId + 1
  case Right(Left(deviceUuid)) => deviceUuid + 2
  case Right(Right(carSerialId)) => carSerialId + 3
}

getUserIdOrDeviceIdOrCarSerialId(Left(taggedUserId))
getUserIdOrDeviceIdOrCarSerialId(Right(Left(taggedDeviceUuid)))
getUserIdOrDeviceIdOrCarSerialId(Right(Right(taggedCarSerialId)))

// You can do by Coproduct by shapeless
import shapeless.{Coproduct, CNil, :+:, Inl, Inr}
type Id = UserId :+: DeviceUuid :+: CarSerialId :+: CNil

def getId(id: Id): Long = id match {
  case Inl(userId) => userId + 1
  case Inr(Inl(deviceUuid)) => deviceUuid + 2
  case Inr(Inr(Inl(carSerialId))) => carSerialId + 3
  case Inr(Inr(Inr(cNil))) => 0
}

getId(Coproduct[Id](taggedUserId))
getId(Coproduct[Id](taggedDeviceUuid))
getId(Coproduct[Id](taggedCarSerialId))