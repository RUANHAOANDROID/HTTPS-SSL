package com.ssl.mina.ssl;

import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactorySpi;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Bogus trust manager factory. Creates BogusX509TrustManager
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
class BogusTrustManagerFactory extends TrustManagerFactorySpi {
	static final X509TrustManager X509 = new X509ExtendedTrustManager() {

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			// Nothing to do
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			// Nothing to do
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket)
				throws CertificateException {
			// Nothing to do
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
				throws CertificateException {
			// Nothing to do
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket)
				throws CertificateException {
			// Nothing to do
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
				throws CertificateException {
			// Nothing to do
		}
	};

	static final TrustManager[] X509_MANAGERS = new TrustManager[] { X509 };

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
