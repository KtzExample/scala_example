package org.example.ktz.gof.chap4.bridge

import org.example.ktz.gof.chap4.AccountInfoDto
import org.example.ktz.gof.chap4.AccountInfoDto.UserId

import scala.concurrent.Future

trait RepositoryImp {
  def getAccountInfoById(userId: UserId): Option[AccountInfoDto]

  def addMoney(userId: UserId, budgetToAdd: Long): Future[AccountInfoDto]

  def addAccount(accountInfoDto: AccountInfoDto): Future[AccountInfoDto]
}

object RepositoryImp {
  def apply(repoType: RepoType): RepositoryImp = repoType match {
    case CacheRepo => new FakeCacheRepositoryImp
    case DbRepo => new FakeDbRepositoryImp
  }

  type RepoType = Int
  val CacheRepo: Int = 1
  val DbRepo: Int = 2
}