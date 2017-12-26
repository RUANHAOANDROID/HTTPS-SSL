package com.ssl.example.mina.ssl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import com.ssl.example.MyApplication;

public class AndroidSSLContext {
	private static final String CLIENT_KET_PASSWORD = "u123456";// 私钥密码
	private static final String CLIENT_TRUST_PASSWORD = "u123456";// 信任证书密码
	private static final String CLIENT_AGREEMENT = "SSL";// 使用协议
	private static final String CLIENT_KEY_MANAGER = "X509";// 密钥管理器
	private static final String CLIENT_TRUST_MANAGER = "X509";//
	private static final String CLIENT_KEY_KEYSTORE = "BKS";// 密库，这里用的是BouncyCastle密库
	private static final String CLIENT_TRUST_KEYSTORE = "BKS";//
	private static final String CLIENT_KEY_STORE = "sslandroid";
	private static final String CLIENT_KEY_TRUSTSTORE = "sslandroidtrust";
	public static SSLContext getSSLBKS() {
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
			// 取得KeyManagerFactory和TrustManagerFactory的X509密钥管理器实例
			KeyManagerFactory keyManager = KeyManagerFactory.getInstance(CLIENT_KEY_MANAGER);
			TrustManagerFactory trustManager = TrustManagerFactory.getInstance(CLIENT_TRUST_MANAGER);
			// 取得BKS密库实例
			KeyStore kks = KeyStore.getInstance(CLIENT_KEY_KEYSTORE);
			KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
			// 加客户端载证书和私钥,通过读取资源文件的方式读取密钥和信任证书
			kks.load(MyApplication.getContextObject().getResources().getAssets().open(CLIENT_KEY_STORE),
					CLIENT_KET_PASSWORD.toCharArray());
			tks.load(MyApplication.getContextObject().getResources().getAssets().open(CLIENT_KEY_TRUSTSTORE),
					CLIENT_TRUST_PASSWORD.toCharArray());
			// 初始化密钥管理器
			keyManager.init(kks, CLIENT_KET_PASSWORD.toCharArray());
			trustManager.init(tks);
			// 初始化SSLContext
			sslContext.init(keyManager.getKeyManagers(), trustManager.getTrustManagers(), null);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return sslContext;
	}
}
