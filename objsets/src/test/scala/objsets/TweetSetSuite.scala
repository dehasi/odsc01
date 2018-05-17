package objsets

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TweetSetSuite extends FunSuite {

  trait TestSets {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)

    val nonEmpty = new NonEmpty(null, new Empty, new Empty)
    val t1 = new Tweet("1", "1", 1)
    val t2 = new Tweet("2", "2", 2)
    val t3 = new Tweet("3", "3", 3)
    val emptyList = Nil
    val list1 = new Cons(t1, Nil)
    val list1_t2 = new Cons(t2, Nil)
    val list2 = new Cons(t2, new Cons(t1, Nil))
    val list32 = new Cons(t3, new Cons(t2, Nil))
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def asList(tweets: TweetList): List[Tweet] = {
    var res = List[Tweet]()
    tweets.foreach(x => res = x :: res)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  def size(list: TweetList): Int = asList(list).size

  test("filter: on empty set") {
    new TestSets {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d") {
    new TestSets {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1)") {
    new TestSets {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2)") {
    new TestSets {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5") {
    new TestSets {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
      assert(size(trends) === size(set5))
    }
  }

  test("insert: to empty() [1]") {
    new TestSets {
      val list = nonEmpty.insert(t1, emptyList)
      assert(size(list) === 1)
      assert(list.head.user === t1.user)
      assert(list.tail.isEmpty)
    }
  }

  test("insert: to list(1) [2[]") {
    new TestSets {
      val list = nonEmpty.insert(t2, list1)
      assert(size(list) === 2)
      assert(list.head.user === t2.user)
      assert(list.tail.head.user === t1.user)
      assert(list.tail.tail.isEmpty)
    }
  }

  test("insert: to list(3,2) [1]") {
    new TestSets {
      val list = nonEmpty.insert(t1, list32)
      assert(size(list) === 3)
      assert(list.head.user === t3.user)
      assert(list.tail.head.user === t2.user)
      assert(list.tail.tail.head.user === t1.user)
      assert(list.tail.tail.tail.isEmpty)
    }
  }

  test("merge:  empty() empty()") {
    new TestSets {
      val list = nonEmpty.merge(emptyList, emptyList)
      assert(size(list) === 0)
    }
  }

  test("merge:  empty() list(1)") {
    new TestSets {
      val list = nonEmpty.merge(emptyList, list1)
      assert(size(list) === 1)
    }
  }

  test("merge:  list(1) empty()") {
    new TestSets {
      val list = nonEmpty.merge(list1, emptyList)
      assert(size(list) === 1)
    }
  }

  test("merge:  list(1) list(2)") {
    new TestSets {
      val list = nonEmpty.merge(list1, list1_t2)
      assert(size(list) === 2)
      assert(list.head.user === t2.user)
      assert(list.tail.head.user === t1.user)
      assert(list.tail.tail.isEmpty)
    }
  }

  test("merge:  list(2) list(1)") {
    new TestSets {
      val list = nonEmpty.merge(list1_t2, list1)
      assert(size(list) === 2)
      assert(list.head.user === t2.user)
      assert(list.tail.head.user === t1.user)
      assert(list.tail.tail.isEmpty)
    }
  }
}
