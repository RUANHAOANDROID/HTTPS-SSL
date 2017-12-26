package com.ssl.example.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.ssl.SslFilter;

import android.util.Log;

public class MinaAndroidHandler extends IoHandlerAdapter {

    private final int[] values = {1, 2, 3};

    public MinaAndroidHandler() {
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
        System.out.println("Closed");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("OPENED");
        for (int i = 0; i < values.length; i++) {
        	String  charset="hello";
        	session.write(charset);
        }
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) {
        System.out.println("*** IDLE #" + session.getIdleCount(IdleStatus.BOTH_IDLE) + " ***");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        session.close();
        System.out.print("exceptionCaught");
        cause.printStackTrace();
        Log.e("ClientHandler", cause.toString());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
       // session.write(new Message());
        System.out.println("messageReceived  : " + message);
        session.write("androidhello");
    }
}
