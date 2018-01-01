package com.ssl.example.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import javax.net.ssl.SSLContext;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
//import org.apache.mina.example.echoserver.ssl.BogusSslContextFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.ssl.example.MyApplication;
import com.ssl.example.mina.ssl.MinaAndroidSSL;
/**
 * 
 * @author rh
 *
 */
public class MinaClient {
	/** Choose your favorite port number. */
	private static final int PORT = 8386;
	
	// private static final String IP="10.0.2.2";//模拟器
	
	private static final String IP = "192.168.0.106";//填写Server IP
	
	/** Set this to true if you want to make the server SSL */
	private static final boolean USE_SSL = true;

	public MinaClient() {
		NioSocketConnector connector = new NioSocketConnector();
		// connector.setConnectTimeoutMillis(50000);
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		// Add SSL filter if SSL is enabled.
		if (USE_SSL) {
			addSSLContext(chain);
		}

		TextLineCodecFactory textLineCodecFactory = new TextLineCodecFactory(Charset.forName("UTF-8"));
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(textLineCodecFactory));
		
		// Bind
		connector.setHandler(new MinaAndroidHandler());

		IoSession session;
		for (;;) {
			try {
				ConnectFuture future = connector.connect(new InetSocketAddress(IP, PORT));
				future.awaitUninterruptibly();
				session = future.getSession();

				if (session.isConnected()) {
					String charset = "hello";
					session.write(charset);
				}

				break;
			} catch (RuntimeIoException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		// wait until the summation is done
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}

	private void addSSLContext(DefaultIoFilterChainBuilder chain) {
		SSLContext sslContext = MinaAndroidSSL.getClientContext(MyApplication.getContextObject());
		SslFilter sslFilter = new SslFilter(sslContext);
		sslFilter.setUseClientMode(true);
		// sslFilter.setEnabledCipherSuites(sslContext.getSupportedSSLParameters().getCipherSuites());
		chain.addLast("SSL", sslFilter);
		System.out.println("SSL ON");
	}
}
