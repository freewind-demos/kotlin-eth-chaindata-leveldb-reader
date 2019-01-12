package example

import org.apache.commons.codec.binary.Hex
import org.fusesource.leveldbjni.JniDBFactory.factory
import org.iq80.leveldb.Options
import java.io.File

private val db = factory.open(File("./Ethereum/geth/chaindata"), Options())!!

fun main(args: Array<String>) {

    db.use {
        val iterator = db.iterator()
        // important!
        iterator.seekToFirst()

        while (iterator.hasNext()) {
            val next = iterator.peekNext()
            val key = String(Hex.encodeHex(next.key!!))
            val value = String(Hex.encodeHex(next.value!!))
            println("$key = $value")
            iterator.next()
        }
    }

}

