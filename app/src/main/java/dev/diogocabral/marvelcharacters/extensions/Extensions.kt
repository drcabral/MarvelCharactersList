package dev.diogocabral.marvelcharacters.extensions

import java.security.MessageDigest

fun String.toMD5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.toHex()
}

private fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}