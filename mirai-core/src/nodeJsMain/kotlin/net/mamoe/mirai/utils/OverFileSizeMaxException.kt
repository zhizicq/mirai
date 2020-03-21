package net.mamoe.mirai.utils

/**
 * 图片文件过大
 */ // 不要删除多平台结构, 这是 kotlin 的 bug
actual class OverFileSizeMaxException actual constructor() : IllegalStateException()