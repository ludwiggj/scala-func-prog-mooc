package exercises.week4.patmat

import exercises.week4.patmat.Huffman._
import exercises.week4.patmat.Huffman.{Fork, Leaf}
import org.scalatest.funsuite.AnyFunSuite

class HuffmanSuite extends AnyFunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(
                   Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5),
                   Leaf('d',4),
                   List('a','b','d'),
                   9
             )
  }

  trait SampleTree {
    val sampleTree = makeCodeTree(
                           makeCodeTree(Leaf('x', 1), Leaf('e', 1)),
                           Leaf('t', 2)
        )
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("weight of another larger tree") {
    new TestTrees {
      assert(weight(t2) === 9)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t1) === List('a','b'))
    }
  }

  test("chars of another larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("makeCodeTree") {
    new SampleTree {
      assert(weight(sampleTree) === 4)
      assert(chars(sampleTree) === List('x', 'e', 't'))
      assert(chars(sampleTree.left) === List('x', 'e'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  def assertTimesListIsAsExpected(list: List[(Char, Int)], expected: List[(Char, Int)]) {
    assert(list.sorted === expected.sorted)

  }

  test("times using a simple string this is an example of a huffman tree") {
    assertTimesListIsAsExpected(times(List('a', 'b', 'a')), List(('a', 2), ('b', 1)))
  }

  test("times using 'this is an example of a huffman tree'") {
    assertTimesListIsAsExpected(times(string2Chars("this is an example of a huffman tree")),
      List(('e', 4), ('a', 4), (' ', 7), ('n', 2), ('t', 2), ('m', 2), ('i', 2), ('h', 2), ('s', 2),
           ('f', 3), ('o', 1), ('u', 1), ('x', 1), ('p', 1), ('r', 1), ('l', 1)))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("makeOrderedLeafList for some frequency table with identical frequencies") {
    assert(makeOrderedLeafList(List(('e', 1), ('t', 1), ('y', 4), ('x', 3))) === List(Leaf('e',1), Leaf('t',1), Leaf('x',3), Leaf('y', 4)))
  }

  test("singleton is false for empty list") {
    assert(singleton(List()) == false)
  }

  test("singleton is false for list containing 2 trees") {
    new SampleTree {
      assert(singleton(List(sampleTree, sampleTree)) == false)
    }
  }

  test("singleton is true for list of a single tree") {
    new SampleTree {
      assert(singleton(List(sampleTree)) == true)
    }
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("combine of leaf list with one element") {
    val leaflist = List(Leaf('e', 1))
    assert(combine(leaflist) === List(Leaf('e',1)))
  }

  test("until with real tree") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert((until(singleton, combine)(leaflist)) === List(Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4), List('e', 't', 'x'), 7)))
  }

  test("until with empty tree") {
    assert((until(singleton, combine)(List())) === List())
  }

  test("createCodeTree") {
    assert(createCodeTree(List('h','e','l','l','o', ' ', 'b','o','y','s')) ===
      Fork(Fork(Fork(Leaf('e',1), Leaf('h',1), List('e', 'h'), 2), Fork(Leaf('b',1), Leaf(' ',1), List('b', ' '), 2), List('e', 'h', 'b', ' '),4),
          Fork(Leaf('l',2), Fork(Fork(Leaf('s',1),Leaf('y',1),List('s', 'y'),2),Leaf('o',2),List('s', 'y', 'o'),4),List('l', 's', 'y', 'o'),6),
          List('e', 'h', 'b', ' ', 'l', 's', 'y', 'o'), 10)
    )
  }

  test("decode example") {
    assert(decode(createCodeTree(List('h','e','l','l','o', ' ', 'b','o','y','s')), List(0,0,0,0,0,1)) === List('e', 'h'))
  }

  test("encode example") {
    val codeTree = createCodeTree(List('h','e','l','l','o', ' ', 'b','o','y','s'))
    assert(encode(codeTree)(List('e', 'h')) === List(0,0,0,0,0,1))
  }

  test("encode exception for unknown letter") {
    val codeTree = createCodeTree(List('h','e','l','l','o', ' ', 'b','o','y','s'))
    intercept[IllegalArgumentException] {
      encode(codeTree)(List('x')) === List(0,0,0,0,0,1)
    }
  }

  test("decodedSecret") {
    assert(decodedSecret === List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))
  }

  test("encodedSecret") {
    assert(encode(frenchCode)(string2Chars("huffmanestcool"))
        === List(0,0,1,1,1,0,1,0,1,1,1,0,0,1,1,0,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,1,1,0,1,0,1,1,0,0,0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,0,1,0,0,0,1,0,1))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      private val phrase: List[Char] = string2Chars("ab")
      assert(decode(t1, encode(t1)(phrase)) === phrase)
    }
  }

  // codeBits(table: CodeTable)(char: Char)
  test("codeBits returns an entry") {
    val codeTable: CodeTable = List(('a', List(0,0,0)), ('b', List(0,0,1)), ('c', List(0,1,0)))
    assert(codeBits(codeTable)('c') === List(0,1,0))
  }

  test("exception if no codeBits match") {
    val codeTable: CodeTable = List(('a', List(0,0,0)), ('b', List(0,0,1)), ('c', List(0,1,0)))
    intercept[IllegalArgumentException] {
      codeBits(codeTable)('d')
    }
  }

  test("convert") {
    val codeTree: CodeTree = createCodeTree(List('h','e','l','l','o', ' ', 'b','o','y','s'))
    println("Code tree [" + codeTree + "]")
  }

  trait TestCodeTables {
    val codeTable1: CodeTable = List(('a', List(0,0,0)), ('b', List(0,0,1)), ('c', List(0,1,0)))
    val codeTable2: CodeTable = List(('d', List(0,1,1)), ('e', List(1,0,0)), ('f', List(1,0,1)))
    val mtCodeTable: CodeTable = List()
  }

  test("mergeCodeTables") {
    new TestCodeTables {
       assert(mergeCodeTables(codeTable1, codeTable2) === List(('a', List(0,0,0)), ('b', List(0,0,1)),
           ('c', List(0,1,0)), ('d', List(0,1,1)), ('e', List(1,0,0)), ('f', List(1,0,1))))
    }
  }

  test("mergeCodeTables first table empty") {
    new TestCodeTables {
       assert(mergeCodeTables(mtCodeTable, codeTable2) === codeTable2)
    }
  }

  test("mergeCodeTables second table empty") {
    new TestCodeTables {
       assert(mergeCodeTables(codeTable1, mtCodeTable) === codeTable1)
    }
  }

  test("quick encode example") {
    val codeTree = createCodeTree(List('h','e','l','l','o', ' ', 'b','o','y','s'))
    assert(quickEncode(codeTree)(List('e', 'h')) === List(0,0,0,0,0,1))
  }

  test("quick encodedSecret") {
    assert(quickEncode(frenchCode)(List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))
        === List(0,0,1,1,1,0,1,0,1,1,1,0,0,1,1,0,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,1,1,0,1,0,1,1,0,0,0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,0,1,0,0,0,1,0,1))
  }

  test("decode and quick encode a very short text should be identity") {
    new TestTrees {
      val phrase = string2Chars("ab")
      assert(decode(t1, quickEncode(t1)(phrase)) === phrase)
    }
  }
}