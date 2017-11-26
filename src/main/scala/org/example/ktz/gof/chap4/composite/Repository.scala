package org.example.ktz.gof.chap4.composite

import org.example.ktz.gof.chap4.AccountInfoDto
import org.example.ktz.gof.chap4.AccountInfoDto.UserId

import scala.concurrent.Future

trait Repository {

  def getAccountInfoById(userId: UserId): Option[AccountInfoDto]

  def addMoney(userId: UserId, budgetToAdd: Long): Future[AccountInfoDto]

  def addAccount(accountInfoDto: AccountInfoDto): Future[AccountInfoDto]
}