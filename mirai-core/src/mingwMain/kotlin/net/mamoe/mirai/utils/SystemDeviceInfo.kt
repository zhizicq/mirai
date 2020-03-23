package net.mamoe.mirai.utils

import kotlinx.io.core.toByteArray
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.mamoe.mirai.utils.MiraiPlatformUtils.md5
import net.mamoe.mirai.utils.io.getRandomByteArray
import net.mamoe.mirai.utils.io.getRandomString

@Serializable
@OptIn(ExperimentalUnsignedTypes::class, MiraiInternalAPI::class)
actual open class SystemDeviceInfo actual constructor() : DeviceInfo() {
    actual constructor(context: Context) : this() {
        this.context = context
    }

    @Transient
    final override lateinit var context: Context

    override val display: ByteArray = "MIRAI.200122.001".toByteArray()
    override val product: ByteArray = "mirai".toByteArray()
    override val device: ByteArray = "mirai".toByteArray()
    override val board: ByteArray = "mirai".toByteArray()
    override val brand: ByteArray = "mamoe".toByteArray()
    override val model: ByteArray = "mirai".toByteArray()
    override val bootloader: ByteArray = "unknown".toByteArray()
    override val fingerprint: ByteArray =
        "mamoe/mirai/mirai:10/MIRAI.200122.001/${getRandomString(7, '0'..'9')}:user/release-keys".toByteArray()
    override val bootId: ByteArray = ExternalImage.generateUUID(md5(getRandomByteArray(16))).toByteArray()
    override val procVersion: ByteArray =
        "Linux version 3.0.31-${getRandomString(8)} (android-build@xxx.xxx.xxx.xxx.com)".toByteArray()
    override val baseBand: ByteArray = byteArrayOf()
    override val version: Version = Version
    override val simInfo: ByteArray = "T-Mobile".toByteArray()
    override val osType: ByteArray = "android".toByteArray()
    override val macAddress: ByteArray = "02:00:00:00:00:00".toByteArray()
    override val wifiBSSID: ByteArray? = "02:00:00:00:00:00".toByteArray()
    override val wifiSSID: ByteArray? = "<unknown ssid>".toByteArray()
    override val imsiMd5: ByteArray = md5(getRandomByteArray(16))
    override val imei: String = getRandomString(15, '0'..'9')
    override val ipAddress: ByteArray
        get() = MiraiPlatformUtils.localIpAddress().split(".").map { it.toUByte().toByte() }.takeIf { it.size == 4 }
            ?.toByteArray() ?: byteArrayOf()
    override val androidId: ByteArray get() = display
    override val apn: ByteArray = "wifi".toByteArray()

    @Serializable
    actual object Version : DeviceInfo.Version {
        override val incremental: ByteArray = "5891938".toByteArray()
        override val release: ByteArray = "10".toByteArray()
        override val codename: ByteArray = "REL".toByteArray()
        override val sdk: Int = 29
    }
}