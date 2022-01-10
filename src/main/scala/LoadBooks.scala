import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer
import scala.io.Source

class LoadBooks(books: List[(String,String)]){

  def load(): List[(String, Map[String, Int], List[String])] = {
    val stopwords = Source.fromFile("src/main/scala/Books/stop_words_english.txt", "utf-8").getLines().toList
    val booksList = ListBuffer[(String, Map[String, Int], List[String])]()

    books.foreach(book => {
      val words = loadText(book._2, stopwords)
      //val counts = words.foldLeft(Map.empty[String, Int]){ (count, word) => count + (word -> (count.getOrElse(word, 0) + 1))      }
      val unsortedCount = words.groupMapReduce(identity)(_ => 1)(_+_)
      val counts = ListMap(unsortedCount.toSeq.sortWith(_._2 > _._2):_*)
      val result = (book._1, counts, words.toList)
      booksList += result
    })
    booksList.toList
  }
  def loadText(filename: String, stopwords: List[String])={
    val text = Source.fromFile(filename).getLines().mkString(" ")
    val textNoStop = cleanText(text, stopwords)
    textNoStop
  }

  def cleanText(t: String, stopwords:List[String]) = {
    t.toLowerCase()
      .replaceAll("""[\p{Punct}&&[^.]]""","")
      .split("\\W+")
      .filter(!stopwords.contains(_))
      .toList
  }


}

