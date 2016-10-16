package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a', 'b', 'd'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
  }

  test("combine of some leaf list") {
//    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
//    assert(combine(leaflist) === List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)))
    val leaflist = List(Leaf('e', 4), Leaf('t', 5), Leaf('x', 6))
    assert(combine(leaflist) === List(Leaf('x', 6), Fork(Leaf('e', 4), Leaf('t', 5), List('e', 't'), 9)))
  }

//  test("test makeCodeTree") {
//    new TestTrees {
//      println(createCodeTree(List('h', 'e', 'l', 'l', 'o')))
//      println(createCodeTree(List('a', 'b')))
//    }
//  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
//      println(encode(t1)("aab".toList))
//      println(decode(t1, List(0, 0, 1)))
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
//      val tst = createCodeTree(List('h', 'e', 'l', 'l', 'o'))
//      println(tst);
//      println(encode(tst)("hello".toList));
//      assert(decode(tst, encode(tst)("hello".toList)) === "hello".toList)
    }
  }

  test("decode French secret") {
    new TestTrees {
      println(decodedSecret)
    }
  }

  test("quick encode text") {
    new TestTrees {
      val tst = createCodeTree(List('h', 'e', 'l', 'l', 'o'))
      println(encode(tst)("hello".toList));
      println(quickEncode(tst)("hello".toList));
      assert(encode(tst)("hello".toList) === quickEncode(tst)("hello".toList))
    }
  }
}
