package net.mamoe.mirai

import net.mamoe.mirai.utils.MiraiInternalAPI

/**
 * [Bot] 中为了让 Java 使用者调用更方便的 API 列表.
 */
@MiraiInternalAPI
@Suppress("FunctionName", "INAPPLICABLE_JVM_NAME", "unused")
actual abstract class BotJavaHappyAPI actual constructor() { // 不要使用 interface, 会无法添加默认实现

    // nothing to do in JS
}