package lectures.week7.lazy_evaluation

object lazee {

  trait TestData {
    println("\nBegin...")
    val x = {
      print("x "); 1
    }
    lazy val y = {
      print("y "); 2
    }

    def z = {
      print("z "); 3
    }
  }

  def main(args: Array[String]) {
    def expr_z = new TestData {
      println("\n[z...]")
      z
    }

    def expr_zy = new TestData {
      println("\n[z + y...]")
      z + y
    }

    def expr_zyx = new TestData {
      println("\n[z + y + x...]")
      z + y + x
    }

    def expr_zyxz = new TestData {
      println("\n[z + y + x + z...]")
      z + y + x + z
    }

    def expr_zyxzy = new TestData {
      println("\n[z + y + x + z + y...]")
      z + y + x + z + y
    }

    def expr_zyxzyx = new TestData {
      println("\n[z + y + x + z + y + x...]")
      z + y + x + z + y + x
    }

    expr_z
    expr_zy
    expr_zyx
    expr_zyxz
    expr_zyxzy
    expr_zyxzyx
  }
}
