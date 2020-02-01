package elmeniawy.eslam.imagepicker.utils

import java.security.SecureRandom
import java.util.*

/**
 * RandomString
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 10:05 PM.
 */
class RandomString {
    /**
     * Generate a random string.
     */
    fun nextString(): String {
        for (idx in buf.indices)
            buf[idx] = symbols[random.nextInt(symbols.size)]
        return String(buf)
    }

    companion object {
        private val upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        private val lower = upper.toLowerCase(Locale.ROOT)

        private val digits = "0123456789"

        private val alphanum = upper + lower + digits
    }

    private var random: Random

    private var symbols: CharArray

    private var buf: CharArray

    private constructor(length: Int, random: Random, symbols: String) {
        if (length < 1) throw IllegalArgumentException()
        if (symbols.length < 2) throw IllegalArgumentException()
        this.random = Objects.requireNonNull(random)
        this.symbols = symbols.toCharArray()
        this.buf = CharArray(length)
    }

    /**
     * Create an alphanumeric string generator.
     */
    private constructor(length: Int, random: Random) : this(length, random, alphanum)

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    private constructor(length: Int) : this(length, SecureRandom())

    /**
     * Create session identifiers.
     */
    constructor() : this(21)
}