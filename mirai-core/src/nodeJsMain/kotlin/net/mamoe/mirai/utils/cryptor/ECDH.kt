package net.mamoe.mirai.utils.cryptor

actual interface ECDHPrivateKey {
    actual fun getEncoded(): ByteArray
}

actual interface ECDHPublicKey {
    actual fun getEncoded(): ByteArray
}

internal actual class ECDHKeyPairImpl : ECDHKeyPair {
    override val privateKey: ECDHPrivateKey
        get() = TODO("Not yet implemented")
    override val publicKey: ECDHPublicKey
        get() = TODO("Not yet implemented")
    override val initialShareKey: ByteArray
        get() = TODO("Not yet implemented")
}

/**
 * 椭圆曲线密码, ECDH 加密
 */
actual class ECDH actual constructor(keyPair: ECDHKeyPair) {
    actual val keyPair: ECDHKeyPair
        get() = TODO("Not yet implemented")

    /**
     * 由 [keyPair] 的私匙和 [peerPublicKey] 计算 shareKey
     */
    actual fun calculateShareKeyByPeerPublicKey(peerPublicKey: ECDHPublicKey): ByteArray {
        TODO("Not yet implemented")
    }

    actual companion object {
        actual val isECDHAvailable: Boolean
            get() = TODO("Not yet implemented")

        /**
         * 由完整的 publicKey ByteArray 得到 [ECDHPublicKey]
         */
        actual fun constructPublicKey(key: ByteArray): ECDHPublicKey {
            TODO("Not yet implemented")
        }

        /**
         * 生成随机密匙对
         */
        actual fun generateKeyPair(): ECDHKeyPair {
            TODO("Not yet implemented")
        }

        /**
         * 由一对密匙计算 shareKey
         */
        actual fun calculateShareKey(
            privateKey: ECDHPrivateKey,
            publicKey: ECDHPublicKey
        ): ByteArray {
            TODO("Not yet implemented")
        }

    }

    actual override fun toString(): String {
        TODO("Not yet implemented")
    }

}

@JsName("newECDH")
@Suppress("FunctionName")
actual fun ECDH(): ECDH {
    TODO("Not yet implemented")
}