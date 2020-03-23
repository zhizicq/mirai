package net.mamoe.mirai.utils

/**
 * Weak Reference.
 * On JVM, it is implemented as a typealias referring to `WeakReference` from JDK.
 *
 * Details:
 * On JVM, instances of objects are stored in the JVM Heap and are accessed via references.
 * GC(garbage collection) can automatically collect and release the memory used by objects that are not directly referred by any other.
 * [WeakRef] is not a direct reference, therefore it doesn't hinder GC.
 *
 * @see weakRef provides a WeakRef
 * @see unsafeWeakRef provides a UnsafeWeakRef
 */
actual class WeakRef<T> actual constructor(referent: T) {
    var value: dynamic = referent

    actual fun get(): T? {
        return value as? T
    }

    actual fun clear() {
        value = null
    }
}