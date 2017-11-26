package org.example.ktz.gof.chap4.composite
import org.example.ktz.gof.chap4.AccountInfoDto
import org.example.ktz.gof.chap4.AccountInfoDto.UserId

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class FakeAccountComposite(childRepos: Repository*) extends Repository {
  require(childRepos.nonEmpty)

  override def getAccountInfoById(userId: UserId): Option[AccountInfoDto] = {
    println("composite - getAccountInfoById")
    childRepos.foldLeft[Option[AccountInfoDto]](None) {
      case (None, repo) => repo.getAccountInfoById(userId)
      case (Some(accountInfoDto), repo) => Some(accountInfoDto)
    }
  }

  override def addMoney(userId: UserId, budgetToAdd: Long): Future[AccountInfoDto] = {
    println("composite - addMoney")
    childRepos.tail.foldLeft(childRepos.head.addMoney(userId, budgetToAdd)) { (r, repo) =>
      r.flatMap(_ => repo.addMoney(userId, budgetToAdd))
    }
  }

  override def addAccount(accountInfoDto: AccountInfoDto) = {
    println("composite - addAccount")
    childRepos.tail.foldLeft(childRepos.head.addAccount(accountInfoDto)) { (r, repo) =>
      r.flatMap(a => repo.addAccount(a))
    }
  }
}
