package org.example.ktz.hackerrank.day30

object DictionariesAndMaps extends App{

  val nContact: Int = io.StdIn.readInt()

  def makeContacts(nContact: Int, acc: Map[String, String] = Map.empty): Map[String, String] =
    if(nContact == 0) acc
    else {
      val input: Array[String] =  io.StdIn.readLine().split(" ")
      makeContacts(nContact - 1, acc + (input(0) -> input(1)))
    }

  def getContact(constacts: Map[String, String]): Unit = {
    val name = io.StdIn.readLine()

    if(name.nonEmpty) {

      val result = contacts.get(name) match {
        case Some(phoneNumber) => s"$name=$phoneNumber"
        case None => "Not found"
      }

      println(result)

      getContact(constacts)
    }
  }


  val contacts: Map[String, String] = makeContacts(nContact)

  getContact(contacts)

}
