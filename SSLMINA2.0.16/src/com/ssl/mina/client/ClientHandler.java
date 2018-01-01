package com.ssl.mina.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.ssl.SslFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssl.mina.Message;
import com.ssl.mina.server.ServerHandler;

public class ClientHandler extends IoHandlerAdapter {
	private final static Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);
	private boolean finished;
	private final int[] values = { 1, 2, 3 };

	public ClientHandler() {
	}

	public boolean isFinished() {
		return finished;
	}

	@Override
	public void sessionCreated(IoSession session) {
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

		// We're going to use SSL negotiation notification.
		session.setAttribute(SslFilter.USE_NOTIFICATION);
		System.out.println("sessionCreated");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LOGGER.info("CLOSED");
		System.out.println("Closed");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		LOGGER.info("OPENED");
		System.out.println("OPENED");
		session.write("helloserver_sessionOpened");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		LOGGER.info("*** IDLE #" + session.getIdleCount(IdleStatus.BOTH_IDLE) + " ***");
		System.out.println("*** IDLE #" + session.getIdleCount(IdleStatus.BOTH_IDLE) + " ***");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		session.closeNow();
		System.out.println(cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		LOGGER.info("Received : " + message);
		session.write("hello_server");
		System.out.println("Received : " + message);
	}
}
