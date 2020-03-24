/*
 * Copyright 2020 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license that can be found through the following link.
 *
 * https://github.com/mamoe/mirai/blob/master/LICENSE
 */

package net.mamoe.mirai.qqandroid

import net.mamoe.mirai.Bot
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.contact.MemberPermission
import net.mamoe.mirai.contact.QQ
import net.mamoe.mirai.data.FriendNameRemark
import net.mamoe.mirai.data.PreviousNameList
import net.mamoe.mirai.data.Profile
import net.mamoe.mirai.event.selectMessages
import net.mamoe.mirai.message.GroupMessage
import net.mamoe.mirai.message.MessageReceipt
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.ExternalImage
import net.mamoe.mirai.utils.MiraiExperimentalAPI
import kotlin.coroutines.CoroutineContext

suspend fun main() {
    val packet = GroupMessage("", MemberPermission.OWNER, testMember, EmptyMessageChain)


    packet.run {
        // as a workaround, go to net.mamoe.mirai/event/select.kt:103
        // and remove `@BuilderInference`
        val s: String = selectMessages {

            // or you can remove this `has` function
            has<OnlineImage>() {
                message[OnlineImage].originUrl
                // note: this expression isn't resolved
            }

            default {
                "" // string
            }
        }
    }
}


val testMember = object : Member() {
    override val group: Group
        get() = TODO("Not yet implemented")
    override val permission: MemberPermission
        get() = TODO("Not yet implemented")
    override var nameCard: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var specialTitle: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override val muteTimeRemaining: Int
        get() = TODO("Not yet implemented")

    override suspend fun mute(durationSeconds: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun unmute() {
        TODO("Not yet implemented")
    }

    override suspend fun kick(message: String) {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage(message: Message): MessageReceipt<Member> {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage(message: MessageChain): MessageReceipt<out QQ> {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hashCode(): Int {
        TODO("Not yet implemented")
    }

    override val id: Long
        get() = TODO("Not yet implemented")
    override val nick: String
        get() = TODO("Not yet implemented")

    @MiraiExperimentalAPI
    override suspend fun queryProfile(): Profile {
        TODO("Not yet implemented")
    }

    @MiraiExperimentalAPI
    override suspend fun queryPreviousNameList(): PreviousNameList {
        TODO("Not yet implemented")
    }

    @MiraiExperimentalAPI
    override suspend fun queryRemark(): FriendNameRemark {
        TODO("Not yet implemented")
    }

    override suspend fun uploadImage(image: ExternalImage): OfflineFriendImage {
        TODO("Not yet implemented")
    }

    override val bot: Bot
        get() = TODO("Not yet implemented")

    override fun toString(): String {
        TODO("Not yet implemented")
    }

    override val coroutineContext: CoroutineContext
        get() = TODO("Not yet implemented")

}