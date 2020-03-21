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

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.io.core.IoBuffer
import kotlinx.io.errors.IOException
import kotlinx.io.streams.asInput
import java.io.File

/*
 * 将各类型图片容器转为 [ExternalImage]
 */


@Suppress("ACTUAL_WITHOUT_EXPECT") // https://youtrack.jetbrains.com/issue/KT-37662
actual typealias JvmPlatformImage = Bitmap

/**
 * 读取 [Bitmap] 的属性, 然后构造 [ExternalImage]
 */
@Suppress(
    "UNUSED_PARAMETER",
    "ACTUAL_WITHOUT_EXPECT",
    "ACTUAL_FUNCTION_WITH_DEFAULT_ARGUMENTS"
) // https://youtrack.jetbrains.com/issue/KT-37662
@Throws(IOException::class)
actual fun Bitmap.toExternalImage(formatName: String = "gif"): ExternalImage {
    TODO()
}

// suspend inline fun BufferedImage.suspendToExternalImage(): ExternalImage = withContext(IO) { toExternalImage() }

/**
 * 读取文件头识别图片属性, 然后构造 [ExternalImage]
 */
@Suppress("ACTUAL_WITHOUT_EXPECT") // https://youtrack.jetbrains.com/issue/KT-37662
@OptIn(MiraiInternalAPI::class)
@Throws(IOException::class)
actual fun File.toExternalImage(): ExternalImage {
    val input = BitmapFactory.decodeFile(this.absolutePath)
    checkNotNull(input) { "Unable to read file(path=${this.path}), BitmapFactory.decodeFile returned null" }

    return ExternalImage(
        width = input.width,
        height = input.height,
        md5 = this.inputStream().use { MiraiPlatformUtils.md5(it) },
        imageFormat = this.nameWithoutExtension,
        input = this.inputStream().asInput(IoBuffer.Pool),
        inputSize = this.length(),
        filename = this.name
    )
}