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
	private static final String CLIENT_KET_PASSWORD = "u123456";// ˽Կ����
	private static final String CLIENT_TRUST_PASSWORD = "u123456";// ����֤������
	private static final String CLIENT_AGREEMENT = "SSL";// ʹ��Э��
	private static final String CLIENT_KEY_MANAGER = "X509";// ��Կ������
	private static final String CLIENT_TRUST_MANAGER = "X509";//
	private static final String CLIENT_KEY_KEYSTORE = "BKS";// �ܿ⣬�����õ���BouncyCastle�ܿ�
	private static final String CLIENT_TRUST_KEYSTORE = "BKS";//
	private static final String CLIENT_KEY_STORE = "sslandroid";
	private static final String CLIENT_KEY_TRUSTSTORE = "sslandroidtrust";
	public static SSLContext getSSLBKS() {
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
			// ȡ��KeyManagerFactory��TrustManagerFactory��X509��Կ������ʵ��
			KeyManagerFactory keyManager = KeyManagerFactory.getInstance(CLIENT_KEY_MANAGER);
			TrustManagerFactory trustManager = TrustManagerFactory.getInstance(CLIENT_TRUST_MANAGER);
			// ȡ��BKS�ܿ�ʵ��
			KeyStore kks = KeyStore.getInstance(CLIENT_KEY_KEYSTORE);
			KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
			// �ӿͻ�����֤���˽Կ,ͨ����ȡ��Դ�ļ��ķ�ʽ��ȡ��Կ������֤��
			kks.load(MyApplication.getContextObject().getResources().getAssets().open(CLIENT_KEY_STORE),
					CLIENT_KET_PASSWORD.toCharArray());
			tks.load(MyApplication.getContextObject().getResources().getAssets().open(CLIENT_KEY_TRUSTSTORE),
					CLIENT_TRUST_PASSWORD.toCharArray());
			// ��ʼ����Կ������
			keyManager.init(kks, CLIENT_KET_PASSWORD.toCharArray());
			trustManager.init(tks);
			// ��ʼ��SSLContext
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
