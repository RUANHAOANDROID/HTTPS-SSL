/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package com.ssl.mina.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.ssl.mina.log.LogConfigurator;
import com.ssl.mina.ssl.BogusSslContextFactory;

/**
 * (<b>Entry point</b>) Echo server
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class Server {
	/** 选择一个端口 */
	private static final int PORT = 8386;

	/** Set this to true if you want to make the server SSL */
	private static final boolean USE_SSL = true;
	static {
		org.apache.log4j.Level level=org.apache.log4j.Level.ALL;
		String logFile="E:\\Demo\\SSLMINA2.0.16\\server.log";
		LogConfigurator log =new LogConfigurator(logFile,level);
		log.setUseFileAppender(true);
		log.setUseLogCatAppender(true);
		log.setImmediateFlush(true);
		log.configure();
	}
	public static void main(String[] args) throws Exception {
		SocketAcceptor acceptor = new NioSocketAcceptor();
		acceptor.setReuseAddress(true);
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		// Add SSL filter if SSL is enabled.
		if (USE_SSL) {
			//addSSLSupport(chain);
			addSSLContext(chain);
		}
		 acceptor.getFilterChain().addLast("codec",
		 new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		 //acceptor.getFilterChain().addLast("threaddPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		// Bind
		acceptor.setHandler(new ServerHandler());
		acceptor.bind(new InetSocketAddress(PORT));

		System.out.println("Listening on port " + PORT);

		for (;;) {
			System.out.println("R: " + acceptor.getStatistics().getReadBytesThroughput() + ", W: "
					+ acceptor.getStatistics().getWrittenBytesThroughput());
			Thread.sleep(3000);
		}
	}

	private static void addSSLSupport(DefaultIoFilterChainBuilder chain) throws Exception {
		SslFilter sslFilter = new SslFilter(BogusSslContextFactory.getInstance(true));
		chain.addLast("sslFilter", sslFilter);
		System.out.println("SSL ON");
	}

	private static void addSSLContext(DefaultIoFilterChainBuilder chain) {
		SslFilter sslFilter = new SslFilter(ServerSSL.getServerContext());
		chain.addLast("sslFilter", sslFilter);
		System.out.println("SSL ON");
	}
}
