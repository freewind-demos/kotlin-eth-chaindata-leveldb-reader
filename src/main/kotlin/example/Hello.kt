package example

import org.fusesource.leveldbjni.JniDBFactory.factory
import org.iq80.leveldb.Options
import java.io.File

private val db = factory.open(File("./data/example.db"), Options())!!

fun main(args: Array<String>) {

    db.use {
        val iterator = db.iterator()
        // important!
        iterator.seekToFirst()

        while (iterator.hasNext()) {
            val next = iterator.peekNext()
            val key = String(next.key!!)
            val value = String(next.value!!)
            println("$key = $value")
            iterator.next()
        }
    }

}

