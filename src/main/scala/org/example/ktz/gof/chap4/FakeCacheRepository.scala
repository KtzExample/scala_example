package org.example.ktz.gof.chap4

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class CacheAccountInfo(userId: Int, budget: Long, userName: String)

class FakeCacheRepository {
  import scala.collection.mutable
  private val accountInfo: mutable.Map[Int, CacheAccountInfo] = mutable.Map.empty

  def getAccountInfo(userId: Int): Option[CacheAccountInfo] = accountInfo.get(userId)

  def setUserInfo(cacheAccountInfo: CacheAccountInfo): Future[CacheAccountInfo] = Future {
    accountInfo.update(cacheAccountInfo.userId, cacheAccountInfo)
    accountInfo(cacheAccountInfo.userId)
  }

  def addAccountInfo(cacheAccountInfo: CacheAccountInfo): Future[CacheAccountInfo] = Future {
    accountInfo += cacheAccountInfo.userId -> cacheAccountInfo

    accountInfo(cacheAccountInfo.userId)
  }
}
