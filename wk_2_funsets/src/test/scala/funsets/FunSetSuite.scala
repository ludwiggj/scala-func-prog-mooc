package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  test("contains set of all negative numbers") {
    def largerThanZero(x: Int) = x > 0
    expectResult(false) { (contains(largerThanZero, -1)) }
    expectResult(false) { contains(largerThanZero, 0) }
    expectResult(true) { contains(largerThanZero, 1) }
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)   
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s12 = (x: Int) => (x == 1) || (x == 2)
    val s13 = (x: Int) => (x == 1) || (x == 3)
    val s_1_3_5 = (x: Int) => x == 1 || x == 3 || x == 5
    val s_1_5_10 = (x: Int) => x == 1 || x == 5 || x == 10
    val mtSet = (x: Int) => false;
    val identitySet = (x: Int) => true
    val isEven = (p: Int) => p % 2 == 0
    val isOdd = (p: Int) => p % 2 == 1
    val s1To5 = (x: Int) => x > 0 && x <=5;
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  
  test("union of set and empty set is the set itself") {
    new TestSets {
      val s = union(s1, mtSet)
      assert(contains(s, 1), "Union mt 1")
      assert(!contains(s, 2), "Union mt 2")
      assert(!contains(s, 3), "Union mt 3")
    }
  }
  
  test("union of empty set and set is the set itself") {
    new TestSets {
      val s = union(mtSet, s1)
      assert(contains(s, 1), "mt Union 1")
      assert(!contains(s, 2), "mt Union 2")
      assert(!contains(s, 3), "mt Union 3")
    }
  }
  
  test("intersection contains set of all elements that are in both sets") {
    new TestSets {
      val s12_Intersect_s13 = intersect(s12, s13) // Common element is 1
      assert(contains(s12_Intersect_s13, 1), "Intersect 1")
      assert(!contains(s12_Intersect_s13, 2), "Intersect 2")
      assert(!contains(s12_Intersect_s13, 3), "Intersect 3")
    }
  }
  
  test("intersection of set and empty set is the empty set") {
    new TestSets {
      val s = intersect(s1, mtSet)
      assert(!contains(s, 1), "Intersect mt 1")
      assert(!contains(s, 2), "Intersect mt 2")
      assert(!contains(s, 3), "Intersect mt 3")
    }
  }
  
  test("intersection of empty set and set is the empty set") {
    new TestSets {
      val s = intersect(mtSet, s1)
      assert(!contains(s, 1), "mt Intersect 1")
      assert(!contains(s, 2), "mt Intersect 2")
      assert(!contains(s, 3), "mt Intersect 3")
    }
  }
  
  test("diff of s, t contains set of all elements that are in s but not in t") {
    new TestSets {
      val s12_Diff_s13 = diff(s12, s13) // Remaining element is 2
      assert(!contains(s12_Diff_s13, 1), "Diff 1")
      assert(contains(s12_Diff_s13, 2), "Diff 2")
      assert(!contains(s12_Diff_s13, 3), "Diff 3")
    }
  }
  
  test("diff of set and the empty set is the set itself") {
    new TestSets {
      val s = diff(s12, mtSet)
      assert(contains(s, 1), "Diff mt 1")
      assert(contains(s, 2), "Diff mt 2")
      assert(!contains(s, 3), "Diff mt 3")
    }
  }
  
  test("diff of empty set and the set is the empty set") {
    new TestSets {
      val s = diff(mtSet, s12)
      assert(!contains(s, 1), "mt Diff 1")
      assert(!contains(s, 2), "mt Diff 2")
      assert(!contains(s, 3), "mt Diff 3")
    }
  }
  
  test("filter set of numbers 1 to 5 to return even numbers") {
    new TestSets {      
      val evens1To5 = filter(s1To5, isEven)
      assert(!contains(evens1To5, 1), "1 is not even")
      assert(contains(evens1To5, 2), "2 is even")
      assert(!contains(evens1To5, 3), "3 is not even")
      assert(contains(evens1To5, 4), "4 is even")
      assert(!contains(evens1To5, 5), "5 is not even")
      assert(!contains(evens1To5, 0), "0 is outside range")
      assert(!contains(evens1To5, 6), "6 is outside range")
    }
  }
  
   test("filter empty set returns empty set") {
    new TestSets {      
      val mtFiltered = filter(mtSet, isEven)
      assert(!contains(mtFiltered, 1), "mt filter 1")
      assert(!contains(mtFiltered, 2), "mt filter 2")
      assert(!contains(mtFiltered, 3), "mt filter 3")
      assert(!contains(mtFiltered, 4), "mt filter 4")
      assert(!contains(mtFiltered, 5), "mt filter 5")
      assert(!contains(mtFiltered, 0), "mt filter 0")
      assert(!contains(mtFiltered, 6), "mt filter 6")
    }
   }
    
   test("square of all numbers in range -1000 to 1000 is 1 million or less") {
     new TestSets {
       val squareIsAMillionOrLess = (p: Int) => p * p <= 1000000       
       assert(forall(identitySet, squareIsAMillionOrLess))
     }
   }
   
   test("forall fails on first test") {
     new TestSets {       
       assert(!forall(identitySet, (x: Int) => x >= -999))
     }
   }
   
   test("forall fails on last test") {
     new TestSets {       
       assert(!forall(identitySet, (x: Int) => x <= 999))
     }
   }
   
   test("all numbers are odd") {
     new TestSets {       
       assert(forall(s_1_3_5, isOdd))
     }
   }
   
   test("all numbers are not odd") {
     new TestSets {
       
       assert(!forall(s_1_5_10, isOdd))
     }
   }
   
   test("for all is true for empty set") {
     new TestSets {
       assert(forall(mtSet, isOdd))
     }
   }
   
   test("at least one odd number") {
     new TestSets { 
       assert(exists(s_1_3_5, isOdd))
     }
   }
   
   test("no even numbers") {
     new TestSets { 
       assert(!exists(s_1_3_5, isEven))
     }
   }
   
   test("another at least one odd number") {
     new TestSets { 
       assert(exists(s_1_5_10, isOdd))
     }
   }
   
   test("at least one even number") {
     new TestSets { 
       assert(exists(s_1_5_10, isEven))
     }
   }
   
   test("map each number between 1 and 5 to its double") {
     new TestSets {
       val newSet = map(s1To5, (x: Int) => 2 * x)
       assert(contains(newSet,2))
       assert(contains(newSet,4))
       assert(contains(newSet,6))
       assert(contains(newSet,8))
       assert(contains(newSet,10))      
     }
   }
   
   test("map each number between 1 and 5 to its cube") {
     new TestSets {
       val newSet = map(s1To5, (x: Int) => x * x * x)
       assert(contains(newSet,1))
       assert(contains(newSet,8))
       assert(contains(newSet,27))
       assert(contains(newSet,64))
       assert(contains(newSet,125))     
     }
   }
   
   test("map on empty set produces another empty set") {
     new TestSets {
       val newSet = map(mtSet, (x: Int) => 2 * x)
       assert(!contains(newSet,2))
       assert(!contains(newSet,4))
       assert(!contains(newSet,6))
       assert(!contains(newSet,8))
       assert(!contains(newSet,10))      
     }
   }
}