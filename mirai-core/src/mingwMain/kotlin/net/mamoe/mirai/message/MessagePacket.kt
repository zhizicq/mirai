package net.mamoe.mirai.message

import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.contact.QQ
import net.mamoe.mirai.utils.MiraiInternalAPI

/**
 * 一条从服务器接收到的消息事件.
 * 请查看各平台的 `actual` 实现的说明.
 */
@OptIn(MiraiInternalAPI::class)
actual abstract class MessagePacket<TSender : QQ, TSubject : Contact> actual constructor() :
    MessagePacketBase<TSender, TSubject>()