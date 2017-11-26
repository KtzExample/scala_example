package org.example.ktz.gof.chap4.composite

import org.example.ktz.gof.chap4.AccountInfoDto
import org.example.ktz.gof.chap4.AccountInfoDto.UserId

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class FakeDbRepository extends Repository {
  import scala.collection.mutable
  private val accountInfos: mutable.Map[Long, AccountInfoDto] = mutable.Map.empty

  override def getAccountInfoById(userId: UserId): Option[AccountInfoDto] = {
    println("db - getAccountInfoById")
    accountInfos.get(userId)
  }

  override def addMoney(userId: UserId, budgetToAdd: Long): Future[AccountInfoDto] = Future{
    println("db - addMoney")
    val accountInfo = getAccountInfoById(userId).get
    val newAccountInfo = accountInfo.copy(budget = accountInfo.budget + budgetToAdd)
    accountInfos.update(userId, newAccountInfo)

    newAccountInfo
  }

  override def addAccount(accountInfoDto: AccountInfoDto): Future[AccountInfoDto] = Future {
    println("db - addAccount")
    accountInfos += accountInfoDto.userId -> accountInfoDto

    accountInfoDto
  }
}
