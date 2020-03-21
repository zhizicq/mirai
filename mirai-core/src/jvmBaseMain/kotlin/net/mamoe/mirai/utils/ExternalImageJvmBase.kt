/*
 * Copyright 2020 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license that can be found through the following link.
 *
 * https://github.com/mamoe/mirai/blob/master/LICENSE
 */

package net.mamoe.mirai.utils

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.io.ByteReadChannel
import kotlinx.coroutines.withContext
import kotlinx.io.core.Input
import kotlinx.io.core.copyTo
import kotlinx.io.streams.asOutput
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.URL

/**
 * Jvm 平台的图像类.
 *
 * Jvm: `BufferedImage`
 * Android: `Bitmap`
 */
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class JvmPlatformImage

/**
 * 读取 [JvmPlatformImage] 的属性, 然后构造 [ExternalImage]
 */
@Suppress("NO_ACTUAL_FOR_EXPECT")
@Throws(IOException::class)
expect fun JvmPlatformImage.toExternalImage(formatName: String = "gif"): ExternalImage


/**
 * 读取文件头识别图片属性, 然后构造 [ExternalImage]
 */
@Suppress("NO_ACTUAL_FOR_EXPECT")
@Throws(IOException::class)
expect fun File.toExternalImage(): ExternalImage


/**
 * 在 [IO] 中进行 [File.toExternalImage]
 */
suspend inline fun File.suspendToExternalImage(): ExternalImage = withContext(IO) { toExternalImage() }


/**
 * 下载文件到临时目录然后调用 [File.toExternalImage]
 */
@Throws(IOException::class)
fun URL.toExternalImage(): ExternalImage {
    val file = createTempFile().apply { deleteOnExit() }
    file.outputStream().use { output ->
        openStream().use { input ->
            input.copyTo(output)
        }
        output.flush()
    }
    return file.toExternalImage()
}

/**
 * 在 [IO] 中进行 [URL.toExternalImage]
 */
suspend inline fun URL.suspendToExternalImage(): ExternalImage = withContext(IO) { toExternalImage() }

/**
 * 保存为临时文件然后调用 [File.toExternalImage]
 */
@Throws(IOException::class)
fun InputStream.toExternalImage(): ExternalImage {
    val file = createTempFile().apply { deleteOnExit() }
    file.outputStream().use {
        this.copyTo(it)
        it.flush()
    }
    this.close()
    return file.toExternalImage()
}

/**
 * 在 [IO] 中进行 [InputStream.toExternalImage]
 */
suspend inline fun InputStream.suspendToExternalImage(): ExternalImage = withContext(IO) { toExternalImage() }

/**
 * 保存为临时文件然后调用 [File.toExternalImage].
 *
 * 需要函数调用者 close [this]
 */
@Throws(IOException::class)
fun Input.toExternalImage(): ExternalImage {
    val file = createTempFile().apply { deleteOnExit() }
    file.outputStream().asOutput().use {
        this.copyTo(it)
        it.flush()
    }
    return file.toExternalImage()
}

/**
 * 在 [IO] 中进行 [Input.toExternalImage]
 */
suspend inline fun Input.suspendToExternalImage(): ExternalImage = withContext(IO) { toExternalImage() }

/**
 * 保存为临时文件然后调用 [File.toExternalImage].
 */
suspend fun ByteReadChannel.toExternalImage(): ExternalImage {
    val file = createTempFile().apply { deleteOnExit() }
    file.outputStream().use {
        withContext(IO) { copyTo(it) }
        it.flush()
    }

    return file.suspendToExternalImage()
}