/*
 * Copyright 2020 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license that can be found through the following link.
 *
 * https://github.com/mamoe/mirai/blob/master/LICENSE
 */

@file:Suppress("EXPERIMENTAL_API_USAGE", "unused")

package net.mamoe.mirai.utils

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import kotlinx.io.core.buildPacket
import kotlinx.io.errors.IOException
import net.mamoe.mirai.utils.io.getRandomString
import java.awt.image.BufferedImage
import java.io.File
import java.io.OutputStream
import java.security.MessageDigest
import javax.imageio.ImageIO

/*
 * 将各类型图片容器转为 [ExternalImage]
 */
@Suppress("ACTUAL_WITHOUT_EXPECT") // https://youtrack.jetbrains.com/issue/KT-37662
actual typealias JvmPlatformImage = BufferedImage

/**
 * 读取 [BufferedImage] 的属性, 然后构造 [ExternalImage]
 */
@Suppress(
    "ACTUAL_WITHOUT_EXPECT",
    "ACTUAL_FUNCTION_WITH_DEFAULT_ARGUMENTS"
) // https://youtrack.jetbrains.com/issue/KT-37662
@Throws(IOException::class)
actual fun BufferedImage.toExternalImage(formatName: String = "gif"): ExternalImage {
    val digest = MessageDigest.getInstance("md5")
    digest.reset()

    val buffer = buildPacket {
        ImageIO.write(this@toExternalImage, formatName, object : OutputStream() {
            override fun write(b: Int) {
                b.toByte().let {
                    this@buildPacket.writeByte(it)
                    digest.update(it)
                }
            }
        })
    }

    return ExternalImage(width, height, digest.digest(), formatName, buffer, getRandomString(16) + "." + formatName)
}

suspend inline fun BufferedImage.suspendToExternalImage(): ExternalImage = withContext(IO) { toExternalImage() }

/**
 * 读取文件头识别图片属性, 然后构造 [ExternalImage]
 */
@Suppress("ACTUAL_WITHOUT_EXPECT") // https://youtrack.jetbrains.com/issue/KT-37662
@OptIn(MiraiInternalAPI::class)
@Throws(IOException::class)
actual fun File.toExternalImage(): ExternalImage {
    val input = ImageIO.createImageInputStream(this)
    checkNotNull(input) { "Unable to read file(path=${this.path}), no ImageInputStream found" }
    val image = ImageIO.getImageReaders(input).asSequence().firstOrNull()
        ?: error("Unable to read file(path=${this.path}), no ImageReader found (file type not supported)")
    image.input = input

    return ExternalImage(
        width = image.getWidth(0),
        height = image.getHeight(0),
        md5 = MiraiPlatformUtils.md5(this.inputStream()), // dont change
        imageFormat = image.formatName,
        input = this.inputStream(),
        filename = this.name
    )
}
