import scala.collection.immutable.ListMap

object Task3 {




  def main(args: Array[String]): Unit = {
    val books = List[(String, String)](
      ("Romeo and Juliet", "src/main/scala/Books/Romeo and Juliet.txt"),
      ("Metamorphosis.txt", "src/main/scala/Books/Metamorphosis.txt"),
      ("Crime and Punishment","src/main/scala/Books/Crime and Punishment.txt"),
      ("A Connecticut Yankee in King Arthur's Court.txt","src/main/scala/Books/A Connecticut Yankee in King Arthur's Court.txt"),
      ("Adventures of Huckleberry Finn.txt","src/main/scala/Books/Adventures of Huckleberry Finn.txt"),
      ("The Adventures of Tom Sawyer.txt","src/main/scala/Books/The Adventures of Tom Sawyer.txt"))

    val loader = new LoadBooks(books)
    val loadedBooks = loader.load()
    printWords(5, loadedBooks)

    val lastBook = loadedBooks.last
    println(lastBook._3.take(100))


  }




}


def printWords(n: Int, books: List[(String, Map[String, Int], _)]): Unit = {
  books.foreach(
    book => {
      println("Title: " + book._1)
      ListMap(book._2.toSeq.sortWith(_._2 > _._2): _*).take(n).foreach(word => println(word._1 + " " + word._2))
    }
  )
}