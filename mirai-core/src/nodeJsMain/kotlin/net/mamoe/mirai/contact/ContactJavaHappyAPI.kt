package net.mamoe.mirai.contact

import net.mamoe.mirai.JavaHappyAPI
import net.mamoe.mirai.utils.MiraiInternalAPI

/**
 * [Contact] 中为了让 `Java` 更容易调用的 API
 */
@MiraiInternalAPI
@JavaHappyAPI
actual abstract class ContactJavaHappyAPI {
    // nothing to do in JS
}

/**
 * [Member] 中为了让 `Java` 更容易调用的 API
 */
@MiraiInternalAPI
@JavaHappyAPI
actual abstract class MemberJavaHappyAPI : QQ() {
    // nothing to do in JS
}