package com.ssl.example.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
//import org.apache.mina.example.echoserver.ssl.BogusSslContextFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.ssl.example.mina.ssl.AndroidSSL;
import com.ssl.example.mina.ssl.AndroidSSLContext;
import com.ssl.mina.ssl.BogusSslContextFactory;

public class MinaAndroid {
    /** Choose your favorite port number. */
    private static final int PORT = 8386;
    private static final String IP="192.168.28.96";
    // private static final String IP="localhost";
    /** Set this to true if you want to make the server SSL */
    private static final boolean USE_SSL = true;

    public MinaAndroid (){
        NioSocketConnector connector = new NioSocketConnector();
        //connector.setConnectTimeoutMillis(50000);
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();
        // Add SSL filter if SSL is enabled.
        if (USE_SSL) {
           try {
        	   // addSSLSupport(chain);
        	  addSSLContext(chain);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        }
//        ObjectSerializationCodecFactory factory = new ObjectSerializationCodecFactory();
//        factory.setDecoderMaxObjectSize(Integer.MAX_VALUE);
//        factory.setEncoderMaxObjectSize(Integer.MAX_VALUE);
//        connector.getFilterChain().addLast("codec",
//                new ProtocolCodecFilter(factory));
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        // Bind
        connector.setHandler(new AndroidHandler());

        IoSession session;
        for (;;) {
            try {
                ConnectFuture future = connector.connect(new InetSocketAddress(IP,PORT));
                future.awaitUninterruptibly();
                session = future.getSession();

                if (session.isConnected()) {
//                    Message msg = new Message();
//                    msg.setValue(110);
//                    session.write(msg);
                	String  charset="hello";
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
    private static void addSSLSupport(DefaultIoFilterChainBuilder chain) throws Exception {
        SslFilter sslFilter = new SslFilter(BogusSslContextFactory.getInstance(false));
        sslFilter.setUseClientMode(true);
        chain.addLast("sslFilter", sslFilter);
        System.out.println("SSL ON");

    }
    private static void addSSLContext(DefaultIoFilterChainBuilder chain) {
        SslFilter sslFilter = new SslFilter(AndroidSSL.getSSL());
        //SslFilter sslFilter = new SslFilter(AndroidSSLContext.getSSLBKS());
        sslFilter.setUseClientMode(true);
        chain.addLast("sslFilter", sslFilter);
        System.out.println("SSL ON");
    }
}
