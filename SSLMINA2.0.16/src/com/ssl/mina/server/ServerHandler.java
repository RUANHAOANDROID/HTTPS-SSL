package com.ssl.mina.server;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.ssl.SslFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssl.mina.Message;

/**
 * {@link IoHandler} implementation for echo server.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class ServerHandler extends IoHandlerAdapter {
	private final static Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

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
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		LOGGER.info("*** IDLE #" + session.getIdleCount(IdleStatus.BOTH_IDLE) + " ***");
		System.out.println("*** IDLE #" + session.getIdleCount(IdleStatus.BOTH_IDLE) + " ***");
	}

	@Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        session.closeNow();
        System.out.println("exceptionCaught");
        System.out.print(cause);
    }

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		LOGGER.info("Received : " + message);
		// Write the received data back to remote peer
		//session.write(((IoBuffer) message).duplicate());
		//session.write(new Message());
		session.write("Server Java");
		System.out.println("Received : " + message);
	}
}
