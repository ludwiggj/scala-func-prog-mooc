package lectures.week7.water_pouring

class Pouring(glassCapacities: Vector[Int]) {

  // Glass: Int (glass identifier)
  // State: Vector[Int], one entry per glass, current capacity of glass
  // Moves:

  // Empty(glass) - completely empty a glass
  // Fill(glass) - completely fill a glass
  // Pour(from, to) - pour contents of from glass into to glass, until either from glass is empty, or to glass is full
  //                  (whichever occurs first)

  val initialState = glassCapacities map (x => 0)

  // moves
  trait Move {
    def change(state: State): State
  }

  case class Empty(glass: Int) extends Move {
    def change(state: State) = state updated(glass, 0)
  }

  case class Fill(glass: Int) extends Move {
    def change(state: State) = state updated(glass, glassCapacities(glass))
  }

  case class Pour(from: Int, to: Int) extends Move {
    def change(state: State) = {
      val amount = state(from) min (glassCapacities(to) - state(to))
      state updated(from, state(from) - amount) updated(to, state(to) + amount)
    }
  }

  val glasses = 0 until glassCapacities.length

  val moves: Seq[Move] =
    (for (g <- glasses) yield Empty(g)) ++
      (for (g <- glasses) yield Fill(g)) ++
      (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))

  case class Path(history: List[Move], val endState: State) {
    def extend(move: Move) = new Path(move :: history, move change endState)

    override def toString = (history.reverse mkString " ") + "---> " + endState

    override def equals(o: Any) = o match {
      case that: Path => that.history.equals(this.history) && that.endState.equals(this.endState)
      case _ => false
    }
  }

  val initialPath = new Path(Nil, initialState)

  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    else {
      val more = for {
        currentPath <- paths
        nextPath <- moves map currentPath.extend // calls path.extend on each move to generate a new path
        if !(explored contains nextPath.endState)
      } yield nextPath
      paths #:: from(more, explored ++ (more map (_.endState)))
    }

  val pathSets = from(Set(initialPath), Set(initialState))

  def solutions(targetCapacity: Int): Stream[Path] =
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains targetCapacity
    } yield path
}