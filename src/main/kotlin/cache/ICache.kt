package cache

interface ICache<K, V> {
    fun put(key: K, value: V)

    fun remove(key: K): V?

    fun get(key: K): V?

    fun clear()

    fun size(): Int
}