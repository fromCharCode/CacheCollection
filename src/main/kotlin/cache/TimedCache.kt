package cache

import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch

class TimedCache<K: TimedKey<String>, V>(
    private val intervalMillis: Long
): ICache<K, V> {
    private val cacheMap = ConcurrentHashMap<K, V>()

    init {
        checkExpiredKeyJob(intervalMillis)
    }

    private fun checkExpiredKeyJob(timeInterval: Long): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            while (NonCancellable.isActive) {
                cacheMap.keys.removeIf { it.isExpired }
            }
        }
    }

    override fun put(key: K, value: V) {
        cacheMap[key] = value
    }

    override fun remove(key: K): V? =
        cacheMap.remove(key)

    override fun get(key: K): V? =
        cacheMap[key]

    override fun clear() =
        cacheMap.clear()

    override fun size(): Int =
        cacheMap.size
}