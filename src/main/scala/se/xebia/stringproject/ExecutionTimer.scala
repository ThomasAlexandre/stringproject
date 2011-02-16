package se.xebia.stringproject

import scala.util.logging.Logged

trait ExecutionTimer extends Execution with Logged {
  abstract override def execute(strings: Set[String]) = {
    val start = new java.util.Date
    val result = super.execute(strings)
    val end = new java.util.Date
    val consumedTime = end.getTime - start.getTime
    log("Consumed time: " + consumedTime+ " ms")
    result
  }
}