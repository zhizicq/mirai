package net.mamoe.mirai.utils.io

import kotlinx.io.core.ByteReadPacket
import kotlinx.io.core.Closeable
import net.mamoe.mirai.utils.MiraiInternalAPI

/**
 * 多平台适配的 DatagramChannel.
 */
@MiraiInternalAPI
actual class PlatformDatagramChannel actual constructor(serverHost: String, serverPort: Short) :
    Closeable {
    /**
     * @throws SendPacketInternalException
     */
    actual suspend inline fun send(packet: ByteReadPacket): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * @throws ReadPacketInternalException
     */
    actual suspend inline fun read(): ByteReadPacket {
        TODO("Not yet implemented")
    }

    actual val isOpen: Boolean
        get() = TODO("Not yet implemented")

    override fun close() {
        TODO("Not yet implemented")
    }

}