package net.mamoe.mirai.utils.cryptor

actual interface ECDHPrivateKey {
    actual fun getEncoded(): ByteArray
}

actual interface ECDHPublicKey {
    actual fun getEncoded(): ByteArray
}

internal actual class ECDHKeyPairImpl : ECDHKeyPair {
    override val privateKey: ECDHPrivateKey
        get() = error("unsupported")
    override val publicKey: ECDHPublicKey
        get() = error("unsupported")
    override val initialShareKey: ByteArray
        get() = error("unsupported")
}

/**
 * 椭圆曲线密码, ECDH 加密
 */
actual class ECDH actual constructor(actual val keyPair: ECDHKeyPair) {

    /**
     * 由 [keyPair] 的私匙和 [peerPublicKey] 计算 shareKey
     */
    actual fun calculateShareKeyByPeerPublicKey(peerPublicKey: ECDHPublicKey): ByteArray {
        error("unsupported calculateShareKeyByPeerPublicKey")
    }

    actual companion object {
        actual val isECDHAvailable: Boolean
            get() = false

        /**
         * 由完整的 publicKey ByteArray 得到 [ECDHPublicKey]
         */
        actual fun constructPublicKey(key: ByteArray): ECDHPublicKey {
            error("unsupported constructPublicKey")
        }

        /**
         * 生成随机密匙对
         */
        actual fun generateKeyPair(): ECDHKeyPair {
            return ECDHKeyPair.DefaultStub
        }

        /**
         * 由一对密匙计算 shareKey
         */
        actual fun calculateShareKey(
            privateKey: ECDHPrivateKey,
            publicKey: ECDHPublicKey
        ): ByteArray {
            error("unsupported calculateShareKey")
        }

    }

    actual override fun toString(): String {
        return "ECDH(keyPair=$keyPair)"
    }

}

@JsName("newECDH")
@Suppress("FunctionName")
actual fun ECDH(): ECDH {
    return ECDH(ECDH.generateKeyPair())
}