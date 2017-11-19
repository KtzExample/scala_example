package org.example.ktz.gof.chap4

import org.example.ktz.gof.chap4.AccountInfoDto.UserId
import org.example.ktz.gof.chap4.adapter.RepositoryAdapter

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class AccountService(
  cacheRepository: RepositoryAdapter,
  dbRepository: RepositoryAdapter
) {
  def getAccountInfoById(userId: UserId): Option[AccountInfoDto] =
    cacheRepository.getAccountInfoById(userId) match {
      case Some(accountInfoDto) => Some(accountInfoDto)
      case None => dbRepository.getAccountInfoById(userId)
    }


  def addBudget(userId: UserId, moneyToAdd: Long): Future[AccountInfoDto] =
    dbRepository.addMoney(userId, moneyToAdd).flatMap(_ => cacheRepository.addMoney(userId, moneyToAdd))

  def addAccount(accountInfoDto: AccountInfoDto): Future[AccountInfoDto] =
    dbRepository.addAccount(accountInfoDto).flatMap(_ => cacheRepository.addAccount(accountInfoDto))
}
