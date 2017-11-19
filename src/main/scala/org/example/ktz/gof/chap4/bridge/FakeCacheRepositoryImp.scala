package org.example.ktz.gof.chap4.bridge

import org.example.ktz.gof.chap4.AccountInfoDto.UserId
import org.example.ktz.gof.chap4.{AccountInfoDto, CacheAccountInfo}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class FakeCacheRepositoryImp extends RepositoryImp {


  import scala.collection.mutable
  private val accountInfo: mutable.Map[UserId, AccountInfoDto] = mutable.Map.empty

  override def getAccountInfoById(userId: UserId): Option[AccountInfoDto] = accountInfo.get(userId)

  override def addMoney(userId: UserId, budgetToAdd: Long): Future[AccountInfoDto] = Future {
    val accountInfoDto = accountInfo(userId)
    accountInfo.update(userId, accountInfoDto.copy(budget = accountInfoDto.budget + budgetToAdd))

    accountInfoDto
  }

  override def addAccount(accountInfoDto: AccountInfoDto): Future[AccountInfoDto] = Future {
    accountInfo += accountInfoDto.userId -> accountInfoDto

    accountInfo(accountInfoDto.userId)
  }
}
