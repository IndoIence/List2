import scala.collection.immutable.ListMap


object Task3 {




  def main(args: Array[String]): Unit = {
    val books = List[(String, String)](
      ("Romeo and Juliet", "src/main/scala/Books/Romeo and Juliet.txt"),
      ("Metamorphosis.txt", "src/main/scala/Books/Metamorphosis.txt"),
      ("Crime and Punishment","src/main/scala/Books/Crime and Punishment.txt"),
      ("A Connecticut Yankee in King Arthur's Court.txt","src/main/scala/Books/A Connecticut Yankee in King Arthur's Court.txt"),
      ("Adventures of Huckleberry Finn.txt","src/main/scala/Books/Adventures of Huckleberry Finn.txt"),
      ("The Adventures of Tom Sawyer.txt","src/main/scala/Books/The Adventures of Tom Sawyer.txt"),
      ("Lorem Ipsum ","src/main/scala/Books/testFile.txt")
    )

    val loader = new LoadBooks(books)
    val loadedBooks = loader.load()

    //books.foreach(book => generateShingles(book))

    // this for some reason gives a List(Vector(HashSet))) when i expect List(HashSet))
    val minShingle = 3
    val maxShingle = 14
    val shinglesInBooks = loadedBooks.map(book => (minShingle to maxShingle).map(generateShingles(book._3,_)))
    val booksWithShingles = books.zip(shinglesInBooks)

    booksWithShingles.combinations(2).foreach( twoBooks => {
      val firstBook = twoBooks(0)
      val secBook = twoBooks(1)
      for (shingleIndex <- 0 to (maxShingle - minShingle)){
        println("Jaccard Similarity of book " + firstBook._1._1 + " and " + secBook._1._1 + s" for ${shingleIndex + minShingle} shingles")
        println(jaccSim(firstBook._2(shingleIndex), secBook._2(shingleIndex)))

      }

    })


    //println(jaccSim(shinglesInBooks(0)(0), shinglesInBooks(1)(0)))




  }




}

def jaccSim(set1: Set[List[String]], set2: Set[List[String]]) = {
  set1.intersect(set2).size.toDouble / set1.union(set2).size
}


def generateShingles(book: List[String] ,ShingleSize: Int): Set[List[String]] = {
  val shingles = book.sliding(ShingleSize).toSet
  shingles
}

def printWords(n: Int, books: List[(String, Map[String, Int], _)]): Unit = {
  books.foreach(
    book => {
      println("Title: " + book._1)
      ListMap(book._2.toSeq.sortWith(_._2 > _._2): _*).take(n).foreach(word => println(word._1 + " " + word._2))
    }
  )
}