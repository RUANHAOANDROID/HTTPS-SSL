package com.ssl.example.mina.ssl;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.ssl.example.MyApplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

/**
 * Created by hao.ruan on 2017/12/21.
 */

public class MinaAndroidSSL {
	 private static final String PROTOCOL = "TLSv1.2";// 我是坑
	 //private static final String PROTOCOL = "TLS";

	public static SSLContext getClientContext(Context c) {
		SSLContext clientContext = null;

		try {
			String keyStorePassword = "u123456";
			
			clientContext = SSLContext.getInstance(PROTOCOL);
			// 一定要声明密钥是BKS格式
			KeyStore ks = KeyStore.getInstance("BKS");
			ks.load(c.getResources().getAssets().open("aclientkey.bks"), keyStorePassword.toCharArray());

			// getDefaultAlgorithm 可能为null 设定为X509
			String algorithm = "X509";
			// String algorithm=KeyManagerFactory.getDefaultAlgorithm();
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
			kmf.init(ks, keyStorePassword.toCharArray());

			// truststore
			KeyStore ts = KeyStore.getInstance("BKS");
			ts.load(c.getResources().getAssets().open("aclienttrust.bks"), keyStorePassword.toCharArray());

			// String trustAlgorithm=TrustManagerFactory.getDefaultAlgorithm();
			String trustAlgorithm = "X509";
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(trustAlgorithm);
			tmf.init(ts);
			
			clientContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
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

		return clientContext;
	}
}
