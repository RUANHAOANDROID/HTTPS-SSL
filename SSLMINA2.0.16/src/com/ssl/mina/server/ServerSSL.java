package com.ssl.mina.server;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class ServerSSL {
	//	private static final String PROTOCOL = "TLSv1.2";
	private static final String PROTOCOL = "SSL";
	private static final SSLContext SERVER_CONTEXT;
	private static String SERVER_KEY_STORE = ".\\ssl\\sslserverkeys";
	private static String SERVER_TRUST_KEY_STORE = ".\\ssl\\sslservertrust";
	private static String SERVER_KEY_STORE_PASSWORD = "unistrong123456";
	private static String SERVER_TRUST_KEY_STORE_PASSWORD = "unistrong123456";

	static {
		String algorithm = System.getProperty("ssl.KeyManagerFactory.algorithm");
		if (algorithm == null) {
			algorithm = "SunX509";
		}
		SSLContext serverContext;
		try {
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(SERVER_KEY_STORE), SERVER_KEY_STORE_PASSWORD.toCharArray());
			KeyStore tks = KeyStore.getInstance("JKS");
			tks.load(new FileInputStream(SERVER_TRUST_KEY_STORE), SERVER_TRUST_KEY_STORE_PASSWORD.toCharArray());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			kmf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());
			tmf.init(tks);
			serverContext = SSLContext.getInstance(PROTOCOL);
			serverContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		} catch (Exception e) {
			throw new Error(e);
		}
		SERVER_CONTEXT = serverContext;
	}

	public static SSLContext getServerContext() {
		return SERVER_CONTEXT;
	}
}
