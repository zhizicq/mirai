/*
 * Copyright 2020 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license that can be found through the following link.
 *
 * https://github.com/mamoe/mirai/blob/master/LICENSE
 */

package net.mamoe.mirai.utils

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File

/**
 * 加载一个设备信息. 若文件不存在或为空则随机并创建一个设备信息保存.
 */
@OptIn(UnstableDefault::class)
fun File.loadAsDeviceInfo(context: Context = ContextImpl()): DeviceInfo {
    if (!this.exists() || this.length() == 0L) {
        return SystemDeviceInfo(context).also {
            this.writeText(JSON.stringify(RandomDefaultDeviceInfo.serializer(), it))
        }
    }
    return JSON.parse(DeviceInfoData.serializer(), this.readText()).also {
        it.context = context
    }
}

private val JSON = Json(JsonConfiguration.Stable)

actual typealias SystemDeviceInfo = RandomDefaultDeviceInfo