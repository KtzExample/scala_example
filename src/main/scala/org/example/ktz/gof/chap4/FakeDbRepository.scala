package org.example.ktz.gof.chap4

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class FakeDbAccountInfo(userId: Long, userName: String, budget: Long)

class FakeDbRepository {
  import scala.collection.mutable
  private val accountInfo: mutable.Map[Long, FakeDbAccountInfo] = mutable.Map.empty

  def getAccountInfoById(userId: Long): Option[FakeDbAccountInfo] = accountInfo.get(userId)

  def addMoney(userId: Long, moneyToAdd: Long): Future[FakeDbAccountInfo] = Future {
    val accountToAddBudget = accountInfo(userId)
    accountInfo.update(userId, accountToAddBudget.copy(budget = accountToAddBudget.budget + moneyToAdd))

    accountInfo(userId)
  }

  def addAccountInfo(fakeDbAccountInfo: FakeDbAccountInfo): Future[FakeDbAccountInfo] = Future {
    accountInfo += fakeDbAccountInfo.userId -> fakeDbAccountInfo

    accountInfo(fakeDbAccountInfo.userId)
  }
}
