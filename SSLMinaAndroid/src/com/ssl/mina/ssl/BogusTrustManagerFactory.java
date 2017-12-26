package com.ssl.mina.ssl;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.X509Certificate;

import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactorySpi;
import javax.net.ssl.X509TrustManager;

/**
 * Bogus trust manager factory. Creates BogusX509TrustManager
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
class BogusTrustManagerFactory extends TrustManagerFactorySpi {

	static final TrustManager[] X509_MANAGERS = new TrustManager[] { new X509TrustManager() {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
			// return null;
		}

		@Override
		public void checkClientTrusted(X509Certificate[] certs, String authType) {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] certs, String authType) {
		}

	} };

	public BogusTrustManagerFactory() {
		// Do nothing
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TrustManager[] engineGetTrustManagers() {
		return X509_MANAGERS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void engineInit(KeyStore keystore) throws KeyStoreException {
		// noop
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void engineInit(ManagerFactoryParameters managerFactoryParameters)
			throws InvalidAlgorithmParameterException {
		// noop
	}
}
