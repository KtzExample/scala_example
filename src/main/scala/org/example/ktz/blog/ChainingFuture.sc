import java.time.Instant

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

case class UserInfo(userId: Int, nickname: String, emailAddress: String, account: Option[Long])

def getNicknameById(userId: Int): Future[String] = Future {
  Thread.sleep(2000)
  "ktz"
}

def getEmailAddressByNickname(nickname: String): Future[String] = Future {
  Thread.sleep(2000)
  "helloworld@example.com"
}

def getAccountInfoByNickname(nickname: String): Future[Option[Long]] = Future {
  Thread.sleep(3000)
  None
}

def printInterval(userInfo: UserInfo, start: Instant, end: Instant): String =
  s"$userInfo take ${(end.toEpochMilli - start.toEpochMilli) / 1000} seconds"

val userId: Int = 31337

/**
 * Just use for comprehension
*/

val start1: Instant = Instant.now

val fUserInfo1 = for {
  nickname <- getNicknameById(userId)
  emailAddr <- getEmailAddressByNickname(nickname)
  accountInfo <- getAccountInfoByNickname(nickname)
} yield UserInfo(userId, nickname, emailAddr, accountInfo)

val userInfo1 = Await.result(fUserInfo1, 10 second)

val end1: Instant = Instant.now

println(printInterval(userInfo1, start1, end1))  // UserInfo(31337,ktz,helloworld@example.com,None) take 7 seconds

/**
 * Calling first and use next
*/

def getEmailAndAccountTuple(fNickname: Future[String]): Future[(String, Option[Long])] =
  fNickname.flatMap(nickname => {
    val fEmailAddr = getEmailAddressByNickname(nickname)
    val fAccountInfo = getAccountInfoByNickname(nickname)

    fEmailAddr.flatMap(email => fAccountInfo.map(account => (email, account)))
  })

val start2: Instant = Instant.now

val fNickname: Future[String] = getNicknameById(userId)

val fUserInfo2 = for {
  nickname <- fNickname
  (email, accountInfo) <- getEmailAndAccountTuple(fNickname)
} yield UserInfo(userId, nickname, email, accountInfo)
val userInfo2 = Await.result(fUserInfo2, 10 second)

val end2: Instant = Instant.now

println(printInterval(userInfo2, start2, end2)) // UserInfo(31337,ktz,helloworld@example.com,None) take 5 seconds


/**
 * Use cats applicative
*/

import cats.syntax.all._
import cats.instances.future._

val start3: Instant = Instant.now

val fUserInfo3 = for {
  nickname <- getNicknameById(userId)
  (email, account) <- (getEmailAddressByNickname(nickname) |@| getAccountInfoByNickname(nickname)).tupled
} yield UserInfo(userId, nickname, email, account)

val userInfo3: UserInfo = Await.result(fUserInfo3, 10 second)

val end3: Instant = Instant.now

println(printInterval(userInfo3, start3, end3)) // UserInfo(31337,ktz,helloworld@example.com,None) take 5 seconds