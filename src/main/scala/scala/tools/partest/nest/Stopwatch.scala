package scala.tools.partest.nest

/**
  * Measured elapsed time between between calls to `start` and `stop`.
  * May be `pause`-ed and re-`started` before `stop` is eventually called.
  */
final class Stopwatch {
  private var base: Option[Long] = None
  private var elapsed = 0L
  def pause(): Unit = {
    assert(base.isDefined)
    val now = System.nanoTime
    elapsed += (now - base.get)
    base = None
  }
  def start(): Unit = {
    base = Some(System.nanoTime())
  }

  def stop(): Long = {
    pause()
    (1.0 * elapsed / 1000 / 1000).toLong
  }
}
