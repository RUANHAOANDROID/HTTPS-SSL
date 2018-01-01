package com.ssl.mina.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Security;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

public class BogusSslContextFactory {

	/**
	 * Protocol to use.
	 */
	private static final String PROTOCOL = "TLS";

	private static final String KEY_MANAGER_FACTORY_ALGORITHM;

	static {
//		System.setProperty("javax.net.ssl.keyStore", "E:\\Demo\\SSLMina2.0.7\\ssl\\keystore");
//		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		String algorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");

		if (algorithm == null) {
			algorithm = KeyManagerFactory.getDefaultAlgorithm();
		}

		KEY_MANAGER_FACTORY_ALGORITHM = algorithm;
	}

	/**
	 * Bogus Server certificate keystore file name.
	 */
	private static final String BOGUS_KEYSTORE = "bogus.cert";
	//private static final String BOGUS_KEYSTORE = "E:\\Demo\\SSLMina2.0.7\\ssl\\bogus.cert";

	// NOTE: The keystore was generated using keytool:
	// keytool -genkey -alias bogus -keysize 512 -validity 3650
	// -keyalg RSA -dname "CN=bogus.com, OU=XXX CA,
	// O=Bogus Inc, L=Stockholm, S=Stockholm, C=SE"
	// -keypass boguspw -storepass boguspw -keystore bogus.cert

	/**
	 * Bougus keystore password.
	 */
			private static final char[] BOGUS_PW = { 'b', 'o', 'g', 'u', 's', 'p', 'w' };
			//private static final char[] BOGUS_PW = {'1','2','3','4','5','6'};

	private static SSLContext serverInstance = null;

	private static SSLContext clientInstance = null;

	/**
	 * Get SSLContext singleton.
	 *
	 * @param server
	 *            A flag to tell if this is a Client or Server instance we want to
	 *            create
	 * @return SSLContext The created SSLContext
	 * @throws GeneralSecurityException
	 *             If we had an issue creating the SSLContext
	 */
	public static SSLContext getInstance(boolean server) throws GeneralSecurityException {
		SSLContext retInstance;

		if (server) {
			synchronized (BogusSslContextFactory.class) {
				if (serverInstance == null) {
					try {
						serverInstance = createBougusServerSslContext();
					} catch (Exception ioe) {
						throw new GeneralSecurityException("Can't create Server SSLContext:" + ioe);
					}
				}
			}

			retInstance = serverInstance;
		} else {
			synchronized (BogusSslContextFactory.class) {
				if (clientInstance == null) {
					clientInstance = createBougusClientSslContext();
				}
			}

			retInstance = clientInstance;
		}

		return retInstance;
	}

	private static SSLContext createBougusServerSslContext() throws GeneralSecurityException, IOException {
		// Create keystore
		KeyStore ks = KeyStore.getInstance("JKS");
		InputStream in = null;

		try {
			in = BogusSslContextFactory.class.getResourceAsStream(BOGUS_KEYSTORE);
			ks.load(in, BOGUS_PW);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ignored) {
				}
			}
		}

		// Set up key manager factory to use our key store
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KEY_MANAGER_FACTORY_ALGORITHM);
		kmf.init(ks, BOGUS_PW);

		// Initialize the SSLContext to work with our key managers.
		SSLContext sslContext = SSLContext.getInstance(PROTOCOL);
		sslContext.init(kmf.getKeyManagers(), BogusTrustManagerFactory.X509_MANAGERS, null);

		return sslContext;
	}

	private static SSLContext createBougusClientSslContext() throws GeneralSecurityException {
		SSLContext context = SSLContext.getInstance(PROTOCOL);
		context.init(null, BogusTrustManagerFactory.X509_MANAGERS, null);

		return context;
	}
}
