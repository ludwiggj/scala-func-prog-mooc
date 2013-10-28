object bookQuery {
  case class Book(title: String, authors: List[String])

  val books: List[Book] = List(
    Book(title = "Structure and Interpretation of Computer Programs", authors = List("Abelson", "Sussman")),
    Book(title = "Introduction to Functional Programmming", authors = List("Bird", "Wadler")),
    Book(title = "Effective Java", authors = List("Block")),
    Book(title = "Java Puzzlers", authors = List("Block", "Gafter")),
    Book(title = "Programmming in Scala", authors = List("Odersky", "Spoon", "Wenners")),
    Book(title = "A Chip Off the Old", authors = List("Block"))
  )




  def authorOfMoreThanOneBook_Works = {
    (for {
      b1 <- books
      b2 <- books
      if b1 != b2
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1).toSet
  }
  def authorOfMoreThanOneBook_DupsIfAuthsMoreThan2 = {
    for {
      b1 <- books
      b2 <- books
      if b1.title < b2.title
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1
  }

  def authorOfMoreThanOneBook_Distinct = {
    (for {
      b1 <- books
      b2 <- books
      if b1 != b2
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1).distinct
  }

  def authorOfMoreThanOneBook_Best = {
    val bookSet = books.toSet
    for {
      b1 <- bookSet
      b2 <- bookSet
      if b1 != b2
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1
  }
  // books authored by B
  for (b <- books; a <- b.authors if a startsWith "B")
  yield b.title

  // translating back into higher order functions
  val z = (books).map(book =>
    (book, book.authors.filter(a => (a startsWith "B")))) filter (
    entry => entry._2.length > 0) map (entry => entry._1)

  // books with Program in title
  for (b <- books if (b.title indexOf "Program") >= 0)
  yield b.title

  authorOfMoreThanOneBook_Works
  authorOfMoreThanOneBook_DupsIfAuthsMoreThan2
  authorOfMoreThanOneBook_Distinct
  authorOfMoreThanOneBook_Best
}