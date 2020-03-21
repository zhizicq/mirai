package net.mamoe.mirai.utils.io

import kotlinx.io.core.ByteReadPacket
import kotlinx.io.core.Closeable
import net.mamoe.mirai.utils.MiraiInternalAPI

/**
 * 多平台适配的 TCP Socket.
 */
@MiraiInternalAPI
actual class PlatformSocket actual constructor() : Closeable {
    actual suspend fun connect(serverHost: String, serverPort: Int) {
    }

    /**
     * @throws SendPacketInternalException
     */
    actual suspend fun send(packet: ByteArray, offset: Int, length: Int) {
    }

    /**
     * @throws SendPacketInternalException
     */
    actual suspend fun send(packet: ByteReadPacket) {
    }

    /**
     * @throws ReadPacketInternalException
     */
    actual suspend fun read(): ByteReadPacket {
        TODO("Not yet implemented")
    }

    actual val isOpen: Boolean
        get() = TODO("Not yet implemented")

    actual override fun close() {
    }

}