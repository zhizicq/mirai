package net.mamoe.mirai.utils

import kotlin.reflect.KProperty1

@Suppress("UnsafeCastFromDynamic")
internal actual fun KProperty1<*, *>.getValueAgainstPermission(receiver: Any): Any? {
    return (this as KProperty1<dynamic, dynamic>).get(receiver)
}