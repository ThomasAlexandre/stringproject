package se.xebia.stringproject

/** 
 * This class shortens strings recursively by removing a number of 
 * provided words through the use of regular expressions, until no
 * more matches are found.
 * 
 * @author Thomas Alexandre
 *
 */
class StringReducer(words: Set[String]) extends Execution {

  def execute(strings: Set[String]): Set[String]  = {

    val shortenedStrings = strings.map { string =>
      {
        (0 to string.length) map { index =>
          val regex = ("([A-Z]{" + index + "})" + words.mkString("(", "|", ")") + "([A-Z]*)").r
          string match {
            case regex(prefix, w, suffix) => prefix + suffix
            case _ => string
          }
        }
      }.filterNot(_ == string) // removes invariants
    }.toSet.flatten

    if (shortenedStrings isEmpty) strings else execute(shortenedStrings.toSet)
  }
}