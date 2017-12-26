package com.ssl.example.mina.ssl;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import com.ssl.example.MyApplication;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * Created by hao.ruan on 2017/12/21.
 */

public class AndroidSSL {
	private static final String PROTOCOL = "TLSv1.2";
	//private static final String PROTOCOL    = "TLS";
	public static SSLContext getSSL() {
		Context c = MyApplication.getContextObject();
		SSLContext clientContext = null;

		try {
			String keyStorePassword = "u123456";

			// 一定要声明密钥是BKS格式
			KeyStore ks = KeyStore.getInstance("BKS");
			ks.load(c.getResources().getAssets().open("sslandroid.bks"), keyStorePassword.toCharArray());

			// 这里默认是SunX509
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(ks, keyStorePassword.toCharArray());

			// truststore
			KeyStore ts = KeyStore.getInstance("BKS");
			ts.load(c.getResources().getAssets().open("sslandroidtrust.bks"), keyStorePassword.toCharArray());

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(ts);
			clientContext = SSLContext.getInstance(PROTOCOL);
			clientContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clientContext;
	}
}
