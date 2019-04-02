package coursework

// REPORT
//  Functional programming might not be easy to understand and implement at the very beginning but with some
// in-depth insight allows to implement clean, neat, tidy and very brief code which has great impact on speed in terms of
// computability. Instead of writing very long lines of code or many methods thanks to built-in libraries performing clean
// neat code is possible.

// Very important issue is immutability of the code, which allows to prevent error-producing by such simple reason as
// not being able to change anything in once created structure. Therefore, if the string is set to be "string", there is
// no way to change it to be something else, what matters a lot especially in highly data sensitive business fields such
// as banking, where the possibility of hacking the code and changing anything should be minimized and avoided by
// using immutable structures rather than structures, which could possibly have the values changed. Immutable structures when
// correctly implemented will help in avoiding the side effects as well as undesirable code changes after x lines of coding
// by simply working upon the copy of the structure.
// Therefore this proves the importance of using such languages like Scala or Haskell.

// Functional programming itself is a highly mathematical way of coding, where by coding the output will be exactly what
// is desired, providing the appropriate way of code implementation. Only because it relies on immutable structures
// it will allow to avoid errors, changing the states or the values of such structures. Therefore, however it is not easy
// to code, it is worthy to build a functionally programmed software preventing from achieving the undesired side effects.

// A fine example of the code below is to use immutable map, which is created based upon filtering the file, getting the
// desired count of elements and outputting the exact count of every word in a file. This will never be changed, no matter
// how hard one might try. So, it will give exactly the output as expected, while by using a map which is mutable
// the result could be easily modified to achieve outcome, but its likelihood to change would not prevent from
// receiving the wrong count. Achieving other values while using immutable map would be possible only by creating
// a new map, which could contain the new values (key and its value) updated simultaneously or added to a new structure without
// modifying the old one. However, the new structure, of course, would never be the same as the old one, used for creation
// of the new map.

// The brevity and density of the code is nothing else but chain of simple functions, where each of them performs as
// expected. It is easily readable by a computer as well as a human. For example, the following lines:

// val countEachKeyWord = scala.io.Source.fromFile("src/coursework/functions.scala").getLines.flatMap(_.split("\\W+"))
//      .filter(distinctScalaKeysSorted.contains(_)).foldLeft(Map.empty[String, Int]){ (count, keyword) => count + (keyword -> (count.getOrElse(keyword, 0) + 1))}

// allow to achieve the exact count in a file of given keyword in one, maybe because of its length, two lines of code,
// where set of functions can be performed at once instead of trying to obtain result by performing each of the function
// in separate lines. Object Oriented approach would rather propose such solution as:

// public static int countWordsOccurrence(String lineFromFile, String keyword)
// {
//  int count = 0;
//  if (lineFromFile == null || lineFromFile.isEmpty())
//    {
//      return count;
//    }
//
//    String[] wordsInFile = lineFromFile.split("\\s+");
//    for (String word : wordsInFile) {
//      if (word.equals(keyword) {
//        count=count+1;
//      }
//
//    return count;
//  }
//
// which basically is a method, containing fields, statements and so on. In fact, simple set of operations using Scala
// and functional programming approach we can perform much quicker and briefer than using the OO approach.

// Essentially it is hard to state whether functional programming is better than OO programming. Any of these approaches
// can be valid in terms of project specifications, goals to be achieved and so on.


// Through this exercises I have learnt how much easier is to use the functional programming to perform a set of
// operations in comparison to the OO programming. Two to one line of code and a set of functions to be performed reduced
// greatly the amount of code to be written with preserving full clarity and ease of reading the code meaning.

// Also, I could experience that the functional programming involves programming executed in parallel, where the
// statements can be executed in any order, while in OO programming a sequence of statements should be performed in
// strictly designed way. Functional programming is absolutely fine way to use when there is a need to obtain or process
// on few things but many operations (functions) while the OO programming is exactly the opposite - many things but only
// few operations. The evaluation of functions itself is a trademark for functional programming and helps preserve clarity
// of the expected results while OO programming is based on concept of objects, where each field, method, statement must
// be evaluated in turn (is it not a time consuming thing?).






object Concordance {
  def main(args: Array[String]): Unit = {

    // scalaKeywords - list of Scala common keywords
    val scalaKeywords = List("abstract", "case", "catch", "class", "def", "do", "else", "extends", "false", "final", "finally", "for", "forSome",
      "if", "implicit", "import", "lazy", "object", "override", "package", "private", "protected", "returned", "sealed", "super", "this",
      "trait", "type", "yield", "match", "new", "null", "print", "printf", "println", "throw", "to", "trait", "true", "try", "until", "val", "var", "while", "with", "with")

    // distinctScalaKeysSorted - list of sorted and distinct Scala keys
    val distinctScalaKeysSorted = scalaKeywords.distinct.sortWith(_ < _)

    val filename = scala.io.StdIn.readLine("Filename to compare: \n")

    // lines - list of lines from obtained from user file
    val lines = scala.io.Source.fromFile(filename).getLines.toList
    // "src/coursework/functions.scala"

    // countEachKeyWord - immutable map of keys consisting of the Scala keywords and general count of them throughout the file
    // lines from file are read to the form of flat map where words are delimited by any word character denoted by \\W+
    // then filtered to check if a line contain a keyword from distinctScalaKeysSorted denoted by placeholder. The function .foldLeft takes two parameters
    // which are curried,
    // initial value in form of empty map and a function that will take the keyword as parameter and a count of occurrences
    // of the keyword across all the file
    // while traversing it. There is initial default value set as 0 in case of no occurrence.
    // Otherwise each occurrence of the word will
    // increase the value by one.
    // \\W+
    val countEachKeyWord = scala.io.Source.fromFile(filename).getLines.flatMap(_.split("\\W+"))
      .filter(distinctScalaKeysSorted.contains(_)).foldLeft(Map.empty[String, Int]){ (count, keyword) => count + (keyword -> (count.getOrElse(keyword, 0) + 1))}


    // header - initial output line
    val header = "%1$-10s %2$-10s %3$-10s".format("Keyword", "count", "lines")
    println(header)


    distinctScalaKeysSorted.foreach(keyword => {
      // valVector - indexed sequence in form of Vector; first position is the line number, second - the full line
      // applicable only to those lines of code where given word appears
      // zip method takes another collection and merge its content with the content of the current collection and
      // as a result a new collection is obtained, where every element is a tuple, first element contains the element of
      // the first collection and the second element of the tuple is the matching element of the second collection.
      // Eventually the newly obtained collection contains elements meeting the filtering condition of including specified word
      // from the list of Scala Keywords in a file. Therefore, each tuple will contain the line number and the line from the file.
      val valVector = (1 to lines.length) zip lines filter (_._2.split("\\W+").contains(keyword))

      //valuesList - List of tuples formed from Indexed Sequence
      val valuesList = valVector.toList

      // mapValues = map containing the list of tuples transformed into map
      val mapValues = valuesList.toMap

      // linesList - list of lines, distinct and sorted, derived from mapValues with use of Iterator.
      val linesList = mapValues.keysIterator.toList.distinct.sortWith(_ < _)

      if (linesList.length != 0)
        {
          val linesStr = linesList.mkString(", ")
          val lineToPrint = "%1$-10s %2$-10s %3$-10s".format(keyword, countEachKeyWord(keyword), linesStr)

          println(lineToPrint)
        }
      }

    )

  }
}
