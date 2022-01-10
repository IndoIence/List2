object Task1 {
  import scala.io.Source

  def loadGraph(filename: String): List[String] = {
    val file = Source.fromFile(filename)
    val graph = try file.getLines().toList finally file.close()
    graph
  }

  def mapToList: (String) => List[Int] = {
        // I had a hard time returning it as a tuple so i settled for a list of ints
        // twoInts.split("\\s+").toList.map(_.toInt)).map(list => (list.head, list(1)))
        // works but has another map at the end of it that is outside of the "real" map

    (twoInts: String) => twoInts.split("\\s+").toList.map(_.toInt)
  }

  def MyReduce: List[List[Int]] => Unit = {
    edges =>
      val vertices = edges.flatten.toSet

  }

  def zipper(map1: Map[Int, Int], map2: Map[Int, Int]) = {
    for(key <- map1.keys ++ map2.keys)
      yield (key -> (map1.getOrElse(key, 0), map2.getOrElse(key, 0)))
  }

  def main(args: Array[String]): Unit = {

    val stringList = loadGraph("src/main/scala/test.txt")
    val splitList = stringList.map(mapToList).map(list => (list.head, list(1)))
    
    //---------------------------- this probably can be in a reduce function
    val Vert = splitList.map((k,v) => List(k,v)).flatten.toSet
    val OutDeg = splitList.groupMapReduce(_.head)(_ => 1)(_ + _)
    val InDeg = splitList.groupMapReduce(_._2)(_ => 1)(_ + _)
    val zippedMaps = zipper(InDeg, OutDeg)
    // ---------------------------------------------
    
    
    zippedMaps.foreach((k,v) => println("Vertice " + k + " InDeg "+ v._1 + " OutDeg "+ v._2))


    //val splitlist2 = stringList.map(mapToTuples)
    //val test = splitList.groupBy(_.head)


  }
}
