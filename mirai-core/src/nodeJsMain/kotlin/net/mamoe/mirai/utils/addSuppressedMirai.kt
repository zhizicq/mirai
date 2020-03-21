package net.mamoe.mirai.utils

@PublishedApi
internal actual fun Throwable.addSuppressedMirai(e: Throwable) {
    // nothing to do in JS
}