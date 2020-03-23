@file:Suppress("NOTHING_TO_INLINE")

package net.mamoe.mirai.utils

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import kotlinx.io.core.toByteArray
import kotlin.js.Date

/**
 * 仅供内部使用的工具类.
 * 不写为扩展是为了避免污染命名空间.
 */
@MiraiInternalAPI
actual object MiraiPlatformUtils {
    init {
        js("""var zlib = require("zlib");""")
    }

    private val zlibUnzip: dynamic = js(
        """
            function unzip(data, start, end) {
                return zlib.unzipSync(Buffer.from(data.slice(start, end)))
            }
        """
    )

    private val zlibZip: dynamic = js(
        """
            function zip(data, start, end) {
                return zlib.gzipSync(Buffer.from(data.slice(start, end)))
            }
        """
    )

    actual fun unzip(data: ByteArray, offset: Int, length: Int): ByteArray =
        zlibUnzip(data, offset, offset + length) as ByteArray

    actual fun zip(data: ByteArray, offset: Int, length: Int): ByteArray =
        zlibZip(data, offset, offset + length) as ByteArray

    actual fun md5(data: ByteArray, offset: Int, length: Int): ByteArray {
        TODO()
    }

    actual inline fun md5(str: String): ByteArray = md5(str.toByteArray())

    actual fun localIpAddress(): String = "192.168.1.123"

    /**
     * Ktor HttpClient. 不同平台使用不同引擎.
     */
    @MiraiInternalAPI
    actual val Http: HttpClient = HttpClient(Js)
}

/**
 * 时间戳
 */
actual val currentTimeMillis: Long
    get() = Date.now().toLong()