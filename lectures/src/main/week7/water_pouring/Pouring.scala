package main.week7.water_pouring

class Pouring(capacity: Vector[Int]) {

  // states
  type State = Vector[Int]
  val initialState = capacity map (x => 0)

  // moves
  trait Move {
    def change(state: State): State
  }

  case class Empty(glass: Int) extends Move {
    def change(state: State) = state updated (glass, 0)
  }
  case class Fill(glass: Int) extends Move {
    def change(state: State) = state updated (glass, capacity(glass))
  }
  case class Pour(from: Int, to: Int) extends Move {
    def change(state: State) =
      //state
    {
      val amount = state(from) min (capacity(to) - state(to))
      state updated (from, state(from) - amount) updated (to, state(to) + amount)
    }
  }

  val glasses = 0 until capacity.length

  val moves: Seq[Move] =
    (for (g <- glasses) yield Empty(g)) ++
    (for (g <- glasses) yield Fill(g)) ++
    (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))

  class Path(history: List[Move], val endState: State) {

    //def endState: State = trackState(history)
    //private def trackState(xs: List[Move]): State = xs match {
    //  case Nil => initialState
    //  case move :: xs1 => move change trackState(xs1)
    //}

    // Terser version:
    //def endState: State = (history foldRight initialState) (_ change _)

    def extend(move: Move) = new Path(move :: history, move change endState)
    override def toString = (history.reverse mkString " ") + "---> " + endState
  }

  val initialPath = new Path(Nil, initialState)

  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    else {
      val more = for {
        path <- paths
        next <- moves map path.extend // calls path.extend on each move to generate a new path
        if !(explored contains next.endState)
      } yield next
      paths #:: from(more, explored ++ (more map (_.endState)))
    }

  val pathSets = from(Set(initialPath), Set(initialState))

  def solutions(target: Int): Stream[Path] =
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains target
    } yield path
}