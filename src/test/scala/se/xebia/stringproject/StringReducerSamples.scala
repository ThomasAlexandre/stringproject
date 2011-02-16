package se.xebia.stringproject

import org.scalatest.Spec
import org.scalatest.matchers.MustMatchers
import scala.util.logging.ConsoleLogger

class StringReducerSamples extends Spec with MustMatchers {
  describe("Shortening the string") { 
    
    it("should return CITERUSLOVESTALENT on VOCDIITEIOCRUDOIANTOCSLOIOCVESTAIOCVOLIOCENTSU") {
    	val strings = Set("VOCDIITEIOCRUDOIANTOCSLOIOCVESTAIOCVOLIOCENTSU")
    	val words = Set("TDD", "DDD", "DI", "DO", "OO", "UI", "ANT", "CV", "IOC", "LOC", "SU", "VO")
    	val stringReducer = new StringReducer(words) with ExecutionTimer with ConsoleLogger
    	val reducedStrings = stringReducer.execute(strings) 
    	val minimalString = reducedStrings.reduceLeft { (total, elem) => if (total.length < elem.length) total else elem }
    	minimalString must be ("CITERUSLOVESTALENT")
    }
    it("should return empty string on empty input no matter what words") {
    	val stringReducer = new StringReducer(Set("TDD", "DDD")) // no need to measure time on this one
    	stringReducer.execute(Set("")) must be (Set(""))
    }
    it("should return empty string on string TDTDTO with words TDT,TDO") {
    	val strings = Set("TDTDTO")
    	val words = Set("TDT","TDO")
    	val stringReducer = new StringReducer(words) with ExecutionTimer
    	val reducedStrings = stringReducer.execute(strings) 
    	val minimalString = reducedStrings.reduceLeft { (total, elem) => if (total.length < elem.length) total else elem }
    	minimalString must be ("")
    }
  }
}