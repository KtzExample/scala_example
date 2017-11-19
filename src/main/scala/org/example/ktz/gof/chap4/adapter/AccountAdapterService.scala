package org.example.ktz.gof.chap4.adapter

import org.example.ktz.gof.chap4.AccountInfoDto
import org.example.ktz.gof.chap4.AccountInfoDto.UserId

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AccountAdapterService(
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
