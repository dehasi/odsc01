package funsets

import org.junit.runner.RunWith
import org.scalatest.FunSuite
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
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
    * For ScalaTest tests, there exists a special equality operator "===" that
    * can be used inside "assert". If the assertion fails, the two values will
    * be printed in the error message. Otherwise, when using "==", the test
    * error message will only say "assertion failed", without showing the values.
    *
    * Try it out! Change the values so that the assertion fails, and look at the
    * error message.
    */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
    * When writing tests, one would often like to re-use certain values for multiple
    * tests. For instance, we would like to create an Int-set and have multiple test
    * about it.
    *
    * Instead of copy-pasting the code for creating the set into every test, we can
    * store it in the test class using a val:
    *
    * val s1 = singletonSet(1)
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
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s6 = singletonSet(6)
    val s7 = singletonSet(7)
    val s8 = singletonSet(8)
    val s9 = singletonSet(9)
    val s01 = singletonSet(1)
    val s02 = singletonSet(2)
    val s03 = singletonSet(3)
  }

  trait UnitedSets extends TestSets {
    val s14 = union(union(s1, s2), union(s3, s4))
    val s36 = union(union(s3, s5), union(s4, s6))
    val bigSet = union(union(union(s1, s2), union(s3, s4)), union(union(s5, s6), union(s7, s8)))
    val odd = union(union(s2, s4), union(s6, s8))
    val even = union(union(s1, s3), union(s5, s7))
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

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect elements [1,2,3,4] and [3,4,5,6], result [3,4]") {
    new UnitedSets {

      val i = intersect(s14, s36)
      assert(!contains(i, 1), "Intersect 1")
      assert(!contains(i, 2), "Intersect 2")
      assert(contains(i, 3), "Intersect 3")
      assert(contains(i, 4), "Intersect 4")
      assert(!contains(i, 5), "Intersect 5")
      assert(!contains(i, 6), "Intersect 6")
    }
  }

  test("diff elements[1,2,3,4] and [3,4,5,6], result [1,2]") {
    new UnitedSets {

      val d = diff(s14, s36)
      assert(contains(d, 1), "Intersect 1")
      assert(contains(d, 2), "Intersect 2")
      assert(!contains(d, 3), "Intersect 3")
      assert(!contains(d, 4), "Intersect 4")
      assert(!contains(d, 5), "Intersect 5")
      assert(!contains(d, 6), "Intersect 6")
    }
  }

  test("filter elements[1,2,3,4,5,6,7,8] remain even, result [2,4,6,8]") {
    new UnitedSets {

      val filteredSet = filter(bigSet, x => x % 2 == 0)
      assert(!contains(filteredSet, 1), "Intersect 1")
      assert(contains(filteredSet, 2), "Intersect 2")
      assert(!contains(filteredSet, 3), "Intersect 3")
      assert(contains(filteredSet, 4), "Intersect 4")
      assert(!contains(filteredSet, 5), "Intersect 5")
      assert(contains(filteredSet, 6), "Intersect 6")
      assert(!contains(filteredSet, 7), "Intersect 7")
      assert(contains(filteredSet, 8), "Intersect 8")
    }
  }

  test("forall emptySet is all even, result false") {
    new UnitedSets {
      val checked = forall(emptySet(), x => x % 2 == 0)
      assert(!checked, "should be false")
    }
  }
  test("forall elements[2,4,6,8] is all even, result true") {
    new UnitedSets {
      val checked = forall(odd, x => x % 2 == 0)
      assert(checked, "should be true")
    }
  }

  test("forall elements[2,4,6,8,1] is even, result false") {
    new UnitedSets {
      val checked = forall(union(s1, odd), x => x % 2 == 0)
      assert(!checked, "should be false")
    }
  }

  test("exists elements[2,4,6,8,1] [1], result true") {
    new UnitedSets {
      private val united: Set = union(s1, odd)
      val checked = exists(united, x => x == 1)
      assert(checked, "should be true")
    }
  }

  test("exists elements[2,4,6,8] [1], result false") {
    new UnitedSets {
      val checked = exists(odd, x => x == 1)
      assert(!checked, "should be false")
    }
  }

  test("map elements[1,2,3,4] to -1, result [0,1,2,3]") {
    new UnitedSets {
      val mapped = map(s14, x => x - 1)
      assert(contains(mapped, 0), "Intersect 0")
      assert(contains(mapped, 1), "Intersect 1")
      assert(contains(mapped, 2), "Intersect 2")
      assert(contains(mapped, 3), "Intersect 3")
      assert(!contains(mapped, 4), "Intersect 4")
      assert(!contains(mapped, 5), "Intersect 5")
    }
  }
}
