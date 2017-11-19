package org.example.ktz.gof.chap4.adapter

import org.example.ktz.gof.chap4._
import org.example.ktz.gof.chap4.AccountInfoDto.UserId

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait RepositoryAdapter {
  def getAccountInfoById(userId: UserId): Option[AccountInfoDto]

  def addMoney(userId: UserId, budgetToAdd: Long): Future[AccountInfoDto]

  def addAccount(accountInfoDto: AccountInfoDto): Future[AccountInfoDto]
}

object RepositoryAdapter {
  implicit class FakeCacheRepositoryAdapter(fakeCacheRepository: FakeCacheRepository) extends RepositoryAdapter {
    override def getAccountInfoById(userId: UserId): Option[AccountInfoDto] =
      fakeCacheRepository.getAccountInfo(userId.toInt).map(cacheAccountInfo2AccountInfo)

    override def addMoney(userId: UserId, budgetToAdd: Long): Future[AccountInfoDto] = {
      val cacheAccountInfo = fakeCacheRepository.getAccountInfo(userId.toInt).get
      fakeCacheRepository
        .setUserInfo(cacheAccountInfo.copy(budget = cacheAccountInfo.budget + budgetToAdd))
        .map(cacheAccountInfo2AccountInfo)
    }

    override def addAccount(accountInfoDto: AccountInfoDto): Future[AccountInfoDto] =
      fakeCacheRepository.addAccountInfo(accountInfoDto).map(cacheAccountInfo2AccountInfo)

    implicit def cacheAccountInfo2AccountInfo(cacheAccountInfo: CacheAccountInfo): AccountInfoDto =
      AccountInfoDto(
        userId = cacheAccountInfo.userId,
        userName = cacheAccountInfo.userName,
        budget = cacheAccountInfo.budget
      )

    implicit def accountInfo2CacheAccountInfo(accountInfoDto: AccountInfoDto): CacheAccountInfo =
      CacheAccountInfo(
        userId = accountInfoDto.userId.toInt,
        userName = accountInfoDto.userName,
        budget = accountInfoDto.budget
      )
  }


  implicit class FakeDbRepositoryAdapter(fakeDbRepository: FakeDbRepository) extends RepositoryAdapter {
    override def getAccountInfoById(userId: UserId): Option[AccountInfoDto] =
      fakeDbRepository.getAccountInfoById(userId).map(dbAccountInfo2AccountInfo)

    override def addMoney(userId: UserId, budgetToAdd: Long): Future[AccountInfoDto] =
      fakeDbRepository.addMoney(userId, budgetToAdd).map(dbAccountInfo2AccountInfo)

    override def addAccount(accountInfoDto: AccountInfoDto): Future[AccountInfoDto] =
      fakeDbRepository.addAccountInfo(accountInfoDto).map(dbAccountInfo2AccountInfo)


    implicit def dbAccountInfo2AccountInfo(dbAccountInfo: FakeDbAccountInfo): AccountInfoDto =
    AccountInfoDto(
      userId = dbAccountInfo.userId,
      userName = dbAccountInfo.userName,
      budget = dbAccountInfo.budget
    )

    implicit def AccountInfo2dbAccountInfo(accountInfoDto: AccountInfoDto): FakeDbAccountInfo =
    FakeDbAccountInfo(
      userId = accountInfoDto.userId,
      userName = accountInfoDto.userName,
      budget = accountInfoDto.budget
    )
  }
}
