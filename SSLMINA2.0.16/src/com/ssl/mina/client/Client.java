package com.ssl.mina.client;

import java.net.InetSocketAddress;

import org.apache.log4j.Level;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.ssl.mina.Message;
import com.ssl.mina.log.LogConfigurator;
import com.ssl.mina.ssl.BogusSslContextFactory;

public class Client {
	/** Choose your favorite port number. */
	private static final int PORT = 8386;

	/** Set this to true if you want to make the server SSL */
	private static final boolean USE_SSL = false;

	static {
		Level level=org.apache.log4j.Level.ALL;
		String logFile=".\\log\\client.log";
		LogConfigurator log =new LogConfigurator(logFile,level);
		log.setUseFileAppender(true);
		log.setUseLogCatAppender(true);
		log.setImmediateFlush(true);
		log.configure();
	}
	public static void main(String[] args) throws Exception {

		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(50000);
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		// Add SSL filter if SSL is enabled.
		if (USE_SSL) {
			//addSSLSupport(chain);
			addSSLContext(chain);
			
		}
		connector.getFilterChain().addLast("codec",
	            new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
	
		// Bind
		connector.setHandler(new ClientHandler());

		IoSession session;
		for (;;) {
			try {
				ConnectFuture future = connector.connect(new InetSocketAddress(PORT));
				future.awaitUninterruptibly();
				session = future.getSession();
				
				if (session.isConnected()) {
					Message msg = new Message();
					msg.setValue(110);
					session.write(msg);
				}
				
				break;
			} catch (RuntimeIoException e) {
				System.out.println(e);
				e.printStackTrace();
				Thread.sleep(5000);
			}
		}
		// wait until the summation is done
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}

	private static void addSSLSupport(DefaultIoFilterChainBuilder chain) throws Exception {
		SslFilter sslFilter = new SslFilter(BogusSslContextFactory.getInstance(false));
		sslFilter.setUseClientMode(true);
		chain.addLast("sslFilter", sslFilter);
		System.out.println("SSL ON");
	}
	private static void addSSLContext(DefaultIoFilterChainBuilder chain) {
		SslFilter sslFilter = new SslFilter(ClientSSL.getClientContext());
		sslFilter.setUseClientMode(true);
		chain.addLast("sslFilter", sslFilter);
		System.out.println("SSL ON");
	}
}
