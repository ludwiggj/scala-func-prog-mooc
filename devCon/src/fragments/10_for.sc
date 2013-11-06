// 10 - for...

object fors {
  for (i <- 1 to 10) print(i + " ")

  for (i <- 1 to 10; if i % 2 == 0) print(i + " ")

  val x = for (i <- 1 to 10) yield i

  for (i <- 1 to 20; j <- 1 to 20) yield (i, j)

  // Pair of integer factors of 20
  for {
    i <- 1 to 20
    j <- 1 to 20
    if (i * j == 20)
  } yield (i, j)

  for {
    i <- 1 to 20
    j <- 1 to 20
    if (i <= j)
    if (i * j == 20)
  } yield (i, j)
}