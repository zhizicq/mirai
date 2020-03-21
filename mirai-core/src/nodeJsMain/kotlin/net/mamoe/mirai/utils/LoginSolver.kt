package net.mamoe.mirai.utils

import net.mamoe.mirai.Bot
import net.mamoe.mirai.network.BotNetworkHandler
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * 验证码, 设备锁解决器
 */
actual abstract class LoginSolver {
    actual abstract suspend fun onSolvePicCaptcha(bot: Bot, data: ByteArray): String?
    actual abstract suspend fun onSolveSliderCaptcha(bot: Bot, url: String): String?
    actual abstract suspend fun onSolveUnsafeDeviceLoginVerify(
        bot: Bot,
        url: String
    ): String?

    actual companion object {
        actual val Default: LoginSolver
            get() = error("on JS there is no default LoginSolver")
    }

}


@Suppress("ClassName", "PropertyName")
actual open class BotConfiguration actual constructor() {
    /**
     * 日志记录器
     */
    actual var botLoggerSupplier: ((Bot) -> MiraiLogger) = { DefaultLogger("Bot(${it.uin})") }

    /**
     * 网络层日志构造器
     */
    actual var networkLoggerSupplier: ((BotNetworkHandler) -> MiraiLogger) = { DefaultLogger("Network(${it.bot.uin})") }

    /**
     * 设备信息覆盖. 默认使用随机的设备信息.
     */
    actual var deviceInfo: ((Context) -> DeviceInfo)? = null

    /**
     * 父 [CoroutineContext]
     */
    actual var parentCoroutineContext: CoroutineContext = EmptyCoroutineContext

    /**
     * 心跳周期. 过长会导致被服务器断开连接.
     */
    actual var heartbeatPeriodMillis: Long = 60.secondsToMillis

    /**
     * 每次心跳时等待结果的时间.
     * 一旦心跳超时, 整个网络服务将会重启 (将消耗约 5s). 除正在进行的任务 (如图片上传) 会被中断外, 事件和插件均不受影响.
     */
    actual var heartbeatTimeoutMillis: Long = 2.secondsToMillis

    /**
     * 心跳失败后的第一次重连前的等待时间.
     */
    actual var firstReconnectDelayMillis: Long = 5.secondsToMillis

    /**
     * 重连失败后, 继续尝试的每次等待时间
     */
    actual var reconnectPeriodMillis: Long = 5.secondsToMillis

    /**
     * 最多尝试多少次重连
     */
    actual var reconnectionRetryTimes: Int = Int.MAX_VALUE

    /**
     * 验证码处理器
     */
    actual var loginSolver: LoginSolver = LoginSolver.Default

    actual companion object {
        /**
         * 默认的配置实例
         */
        actual val Default = BotConfiguration()
    }

    actual operator fun _NoNetworkLog.unaryPlus() {
        networkLoggerSupplier = supplier
    }

    /**
     * 不记录网络层的 log.
     * 网络层 log 包含包接收, 包发送, 和一些调试用的记录.
     */
    @BotConfigurationDsl
    actual val NoNetworkLog: _NoNetworkLog
        get() = _NoNetworkLog

    @BotConfigurationDsl
    actual object _NoNetworkLog {
        internal val supplier = { _: BotNetworkHandler -> SilentLogger }
    }
}
