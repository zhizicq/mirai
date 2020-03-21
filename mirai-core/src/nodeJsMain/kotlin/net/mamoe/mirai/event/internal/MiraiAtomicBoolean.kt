package net.mamoe.mirai.event.internal

internal actual class MiraiAtomicBoolean actual constructor(initial: Boolean) {
    actual fun compareAndSet(expect: Boolean, update: Boolean): Boolean {
        return if (value == expect) {
            value = update
            true
        } else {
            false
        }
    }

    actual var value: Boolean = initial
}