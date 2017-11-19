package org.example.ktz.gof.chap4.bridge

import org.example.ktz.gof.chap4.AccountInfoDto
import org.example.ktz.gof.chap4.AccountInfoDto.UserId

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class AccountBridgeService {
  import RepositoryImp._

  val cacheImpl: RepositoryImp = RepositoryImp(CacheRepo)
  val dbImpl: RepositoryImp = RepositoryImp(DbRepo)

  def getAccountInfoById(userId: UserId): Option[AccountInfoDto] =
    cacheImpl.getAccountInfoById(userId) match {
      case Some(accountInfoDto) => Some(accountInfoDto)
      case None => dbImpl.getAccountInfoById(userId)
    }


  def addBudget(userId: UserId, moneyToAdd: Long): Future[AccountInfoDto] =
    dbImpl.addMoney(userId, moneyToAdd).flatMap(_ => cacheImpl.addMoney(userId, moneyToAdd))

  def addAccount(accountInfoDto: AccountInfoDto): Future[AccountInfoDto] =
    dbImpl.addAccount(accountInfoDto).flatMap(_ => cacheImpl.addAccount(accountInfoDto))
}
