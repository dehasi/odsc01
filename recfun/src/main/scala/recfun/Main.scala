package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (r == c) 1
    else if (r < 0 || c < 0) 0
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def isOpenBrace(char: Char) = char == '('

    def isClosedBrace(char: Char) = char == ')'

    def isBrace(char: Char) = isOpenBrace(char) || isClosedBrace(char)

    def balance(chars: List[Char], stack: Int): Boolean = {
      if (chars.isEmpty) stack.equals(0)
      else if (isOpenBrace(chars.head)) balance(chars.tail, stack + 1)
      else if (stack == 0) false
      else balance(chars.tail, stack - 1)
    }

    val list = chars.filter(c => isBrace(c))
    balance(list, 0)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.isEmpty || money <= 0) 0
    else if (money == coins.head) 1 + countChange(money - coins.head, coins) + countChange(money, coins.tail)
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
