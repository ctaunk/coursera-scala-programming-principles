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
      if (r <= 0) {
        1
      } else if (c == 0 || c == r) {
        1
      } else {
        pascal(c - 1, r - 1) + pascal(c, r - 1)
      }
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      
      def isBalanced(acc: Int, remaining: List[Char]): Boolean = {
        if (remaining.isEmpty) {
          return acc == 0
        } else if (remaining.head == '(') {
          return isBalanced(acc + 1, remaining.tail)
        } else if (remaining.head == ')') {
          //check if closing brace is encountered when the parenthesis are balanced
          if (acc == 0) {
            return false
          } else {
            return isBalanced(acc - 1, remaining.tail)
          }
        } else {
          return isBalanced(acc, remaining.tail)
        }
      }

      return isBalanced(0, chars)

    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      //money = 0; 1 solution no coins
      //money < 0; no solution
      //money > 0; but no coins then no solution
      if (money == 0) {
        return 1
      } else if (money < 0) {
        return 0
      } else if (coins.isEmpty && money > 0) {
        return 0
      } else {
        return countChange(money, coins.tail) + countChange(money - coins.head, coins)
      }
    }
  }
