package lectures.week6.queries_with_for

case class Book(title: String, authors: List[String])

object bookQuery2 {
  val books: List[Book] = List(
    Book(title = "Structure and Interpretation of Computer Programs", authors = List("Abelson", "Sussman")),
    Book(title = "Introduction to Functional Programmming", authors = List("Bird", "Wadler")),
    Book(title = "Effective Java", authors = List("Block")),
    Book(title = "Java Puzzlers", authors = List("Block", "Gafter")),
    Book(title = "Programmming in Scala", authors = List("Odersky", "Spoon", "Wenners"))
  )

  def main(args: Array[String]) {
    // books authored by Bird
    println(for (b <- books; a <- b.authors if a startsWith "Bird") yield b.title)
  }
}