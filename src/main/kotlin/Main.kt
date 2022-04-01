import cache.TimedCache
import cache.TimedKey

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val timedCache = TimedCache<TimedKey<String>, Int>(10L)

    val key1 = TimedKey("Salz", 10)
    val key2 = TimedKey("Brot", 10)
    val key3 = TimedKey("Milch", 30)
    val key4 = TimedKey("Käse", 10)
    val key5 = TimedKey("Wurst", 40)

    timedCache.put(key1, 4)
    timedCache.put(key2, 12)
    timedCache.put(key3, 16)
    timedCache.put(key4, 20)
    timedCache.put(key5, 50)

    println(timedCache.get(key1))
    println(timedCache.get(key2))
    println(timedCache.get(key3))
    println(timedCache.get(key4))
    println(timedCache.get(key5))

    Thread.sleep(20*1000)

    println(timedCache.get(key1))
    println(timedCache.get(key2))
    println(timedCache.get(key3))
    println(timedCache.get(key4))
    println(timedCache.get(key5))
}