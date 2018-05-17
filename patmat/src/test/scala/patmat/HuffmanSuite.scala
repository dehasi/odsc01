package patmat

import org.junit.runner.RunWith
import org.scalatest.FunSuite
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
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    val list = combine(leaflist)
    assert(list === List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)))
  }


  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("times [a a a b b c]") {
    val chars = List('a', 'a', 'a', 'b', 'b', 'c')
    val list = times(chars)
    assert(list.size === 3)
    assert(list.exists(p => p._1 == 'a' && p._2 == 3))
    assert(list.exists(p => p._1 == 'b' && p._2 == 2))
    assert(list.exists(p => p._1 == 'c' && p._2 == 1))
  }

  test("until(singleton, combine)(trees): possible to call") {
    new TestTrees {
      val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
      val list = until(singleton, combine)(leaflist)
      assert(list === List(Fork(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4), List('e', 't', 'x'), 7)))
    }
  }
  test("decode(codeTree, 111) == B") {
    new TestTrees {
      private val codeTree: CodeTree = createCodeTree(string2Chars("AAAAAAAABBBCDEFGH"))

      private val chars: List[Char] = decode(codeTree, List(1, 1, 1))
      assert(chars === List('B'))
    }
  }

  test("codeBits(codeTable, b) == 111") {
    new TestTrees {
      private val codeTable: CodeTable = List(('a', List(1, 0, 1)), ('b', List(1, 1, 1)), ('c', List(0, 0, 1)))
      private val charToBits: Char => List[Bit] = codeBits(codeTable)

      assert(charToBits('b') === List(1, 1, 1))
    }
  }

  test("convert(AB) = [(a,1)(b,0)]") {
    new TestTrees {
      private val codeTree: CodeTree = createCodeTree(string2Chars("AB"))
      private val codeTable: CodeTable = convert(codeTree)
      assert(codeTable === List(('B', List(0)), ('A', List(1))))
    }
  }

}
