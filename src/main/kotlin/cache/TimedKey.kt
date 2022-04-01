package cache

import java.util.Timer
import java.util.TimerTask

class TimedKey<A>(
    val key: A,
    seconds: Long
) {
    var isExpired = false

    init {
        val timer = Timer()
        timer.schedule(ExpireTask(this), seconds*1000)
    }

    internal fun expire() {
        isExpired = true
    }
}

class ExpireTask<K>(
    val key: TimedKey<K>
): TimerTask() {
    override fun run() {
        key.expire()
    }
}
