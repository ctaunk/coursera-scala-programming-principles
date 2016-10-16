package forcomp

import scala.collection.immutable.Map.WithDefault

object test {
  val counts = List(('a', 1), ('b', 1), ('a', 2)) //> counts  : List[(Char, Int)] = List((a,1), (b,1), (a,2))
  val grouped = counts.groupBy(_._1)              //> grouped  : scala.collection.immutable.Map[Char,List[(Char, Int)]] = Map(b ->
                                                  //|  List((b,1)), a -> List((a,1), (a,2)))
  val combine = (x1: (Char, Int), x2: (Char, Int)) => {
    (x1._1, x1._2 + x2._2)
  }                                               //> combine  : ((Char, Int), (Char, Int)) => (Char, Int) = <function2>
  for {
     item <- grouped
  } yield (item._1, item._2.reduceLeft(combine))  //> res0: scala.collection.immutable.Map[Char,(Char, Int)] = Map(b -> (b,1), a -
                                                  //| > (a,3))
}