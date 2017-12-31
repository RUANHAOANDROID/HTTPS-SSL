# Mina-SSL

[web https demo](https://github.com/RUANHAOANDROID/SpringBootDemo)

server demo

client demo

Android demo(跟进中...)


## 双向认证创建证书过程
1.创建服务端证书serverkey.jks

2.从服务端证书导出 server.cer

3.创建客户端jks clientkey.jks

4.从客户端导出cer client.cer

5.把server.cer 导入到clienttrust.jks

6.把client.cer 导入到servertrust.jks

### Android

7.把client.jks 转换为 aclient.bks

8.把clienttrust.jks转换为aclienttrust.bks

## BC库注意事项

/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/security

该路径下配置Java.security文本 更改RSA 适配相应size、配置bc库



## 参考

[mina 官方文档](http://mina.apache.org/mina-project/userguide/ch2-basics/sample-tcp-client.html)

[Java Secure Socket Extension (JSSE) Reference Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/JSSERefGuide.html#SSLContext)

[Netty使用JSSE实现SSLSocket通信](https://segmentfault.com/a/1190000010054860)

[Mina使用SSL结合android客户端SSL](http://blog.sina.com.cn/s/blog_49b531af0102v5g8.html)

[portecle使用文档](http://portecle.sourceforge.net/howtos.html)


[KEYTOOL和PORTECLE介绍](http://alanzhang.me/2014/12/31/KEYTOOL%E5%92%8CPORTECLE%E4%BB%8B%E7%BB%8D/)

[portecle使用教学视频](https://www.youtube.com/watch?v=nSqKv7VlMcg)

[openssl](https://www.openssl.org/docs/manmaster/man1/openssl.html)

[Google Dev Android Https/SSL](https://developer.android.com/training/articles/security-ssl.html)

[Google Dev Android SSLContext参考](https://developer.android.com/reference/javax/net/ssl/SSLContext.html)

#Android支持的协议以及版本

<table>
   <thead>
     <tr>
       <th>Algorithm</th>
       <th>Supported API Levels</th>
     </tr>
   </thead>
   <tbody>
     <tr>
       <td>Default</td>
       <td>10+</td>
     </tr>
     <tr>
       <td>SSL</td>
       <td>10+</td>
     </tr>
     <tr class="deprecated">
       <td>SSLv3</td>
       <td>10-25</td>
     </tr>
     <tr>
       <td>TLS</td>
       <td>1+</td>
     </tr>
     <tr>
       <td>TLSv1</td>
       <td>10+</td>
     </tr>
     <tr>
       <td>TLSv1.1</td>
       <td>16+</td>
     </tr>
     <tr>
       <td>TLSv1.2</td>
       <td>16+</td>
     </tr>
   </tbody>
 </table>


# 爬坑指南

### ava.lang.IllegalArgumentException

   org.apache.mina.util.DefaultExceptionMonitor(1806): Caused by: java.lang.IllegalArgumentException: TLS_ECDH_ECDSA_WITH_RC4_128_SHA is not supported.

参照对照表找对应机型支持的密码套件

<table>
   <thead>
     <tr>
       <th>Cipher suite</th>
       <th>Supported (API Levels)</th>
       <th>Enabled by default (API Levels)</th>
     </tr>
   </thead>
   <tbody>
     <tr class="deprecated">
       <td>SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA</td>
       <td>9-22</td>
       <td>9-19</td>
     </tr>
     <tr class="deprecated">
       <td>SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA</td>
       <td>9-22</td>
       <td>9-19</td>
     </tr>
     <tr class="deprecated">
       <td>SSL_DHE_DSS_WITH_DES_CBC_SHA</td>
       <td>9-22</td>
       <td>9-19</td>
     </tr>
     <tr class="deprecated">
       <td>SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA</td>
       <td>9-22</td>
       <td>9-19</td>
     </tr>
     <tr class="deprecated">
       <td>SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA</td>
       <td>9-22</td>
       <td>9-19</td>
     </tr>
     <tr class="deprecated">
       <td>SSL_DHE_RSA_WITH_DES_CBC_SHA</td>
       <td>9-22</td>
       <td>9-19</td>
     </tr>
     <tr class="deprecated">
       <td>SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA</td>
       <td>9-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>SSL_DH_anon_EXPORT_WITH_RC4_40_MD5</td>
       <td>9-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>SSL_DH_anon_WITH_3DES_EDE_CBC_SHA</td>
       <td>9-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>SSL_DH_anon_WITH_DES_CBC_SHA</td>
       <td>9-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>SSL_DH_anon_WITH_RC4_128_MD5</td>
       <td>9-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>SSL_RSA_EXPORT_WITH_DES40_CBC_SHA</td>
       <td>9-22</td>
       <td>9-19</td>
     </tr>
     <tr class="deprecated">
       <td>SSL_RSA_EXPORT_WITH_RC4_40_MD5</td>
       <td>9-22</td>
       <td>9-19</td>
     </tr>
     <tr>
       <td>SSL_RSA_WITH_3DES_EDE_CBC_SHA</td>
       <td>9+</td>
       <td>9-19</td>
     </tr>
     <tr class="deprecated">
       <td>SSL_RSA_WITH_DES_CBC_SHA</td>
       <td>9-22</td>
       <td>9-19</td>
     </tr>
     <tr class="deprecated">
       <td>SSL_RSA_WITH_NULL_MD5</td>
       <td>9-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>SSL_RSA_WITH_NULL_SHA</td>
       <td>9-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>SSL_RSA_WITH_RC4_128_MD5</td>
       <td>9-25</td>
       <td>9-19</td>
     </tr>
     <tr class="deprecated">
       <td>SSL_RSA_WITH_RC4_128_SHA</td>
       <td>9-25</td>
       <td>9-23</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA</td>
       <td>1-8</td>
       <td>1-8</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA</td>
       <td>1-8</td>
       <td>1-8</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_DSS_WITH_AES_128_CBC_SHA</td>
       <td>9-22</td>
       <td>9-22</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_DSS_WITH_AES_128_CBC_SHA256</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_DSS_WITH_AES_128_GCM_SHA256</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_DSS_WITH_AES_256_CBC_SHA</td>
       <td>9-22</td>
       <td>20-22</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_DSS_WITH_AES_256_CBC_SHA256</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_DSS_WITH_AES_256_GCM_SHA384</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_DSS_WITH_DES_CBC_SHA</td>
       <td>1-8</td>
       <td>1-8</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA</td>
       <td>1-8</td>
       <td>1-8</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA</td>
       <td>1-8</td>
       <td>1-8</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_RSA_WITH_AES_128_CBC_SHA</td>
       <td>9-25</td>
       <td>9-25</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_RSA_WITH_AES_128_CBC_SHA256</td>
       <td>20-25</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_RSA_WITH_AES_128_GCM_SHA256</td>
       <td>20-25</td>
       <td>20-25</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_RSA_WITH_AES_256_CBC_SHA</td>
       <td>9-25</td>
       <td>20-25</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_RSA_WITH_AES_256_CBC_SHA256</td>
       <td>20-25</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_RSA_WITH_AES_256_GCM_SHA384</td>
       <td>20-25</td>
       <td>20-25</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DHE_RSA_WITH_DES_CBC_SHA</td>
       <td>1-8</td>
       <td>1-8</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_DSS_WITH_DES_CBC_SHA</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_RSA_WITH_DES_CBC_SHA</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_anon_WITH_3DES_EDE_CBC_SHA</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_anon_WITH_AES_128_CBC_SHA</td>
       <td>9-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_anon_WITH_AES_128_CBC_SHA256</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_anon_WITH_AES_128_GCM_SHA256</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_anon_WITH_AES_256_CBC_SHA</td>
       <td>9-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_anon_WITH_AES_256_CBC_SHA256</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_anon_WITH_AES_256_GCM_SHA384</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_DH_anon_WITH_DES_CBC_SHA</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr>
       <td>TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA</td>
       <td>20+</td>
       <td>20+</td>
     </tr>
     <tr>
       <td>TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256</td>
       <td>20+</td>
       <td></td>
     </tr>
     <tr>
       <td>TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256</td>
       <td>20+</td>
       <td>20+</td>
     </tr>
     <tr>
       <td>TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA</td>
       <td>20+</td>
       <td>20+</td>
     </tr>
     <tr>
       <td>TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384</td>
       <td>20+</td>
       <td></td>
     </tr>
     <tr>
       <td>TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384</td>
       <td>20+</td>
       <td>20+</td>
     </tr>
     <tr>
       <td>TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256</td>
       <td>24+</td>
       <td>24+</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDHE_ECDSA_WITH_NULL_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDHE_ECDSA_WITH_RC4_128_SHA</td>
       <td>20-25</td>
       <td>20-23</td>
     </tr>
     <tr>
       <td>TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA</td>
       <td>21+</td>
       <td>21+</td>
     </tr>
     <tr>
       <td>TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA</td>
       <td>21+</td>
       <td>21+</td>
     </tr>
     <tr>
       <td>TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256</td>
       <td>24+</td>
       <td>24+</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr>
       <td>TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA</td>
       <td>20+</td>
       <td>20+</td>
     </tr>
     <tr>
       <td>TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256</td>
       <td>20+</td>
       <td></td>
     </tr>
     <tr>
       <td>TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256</td>
       <td>20+</td>
       <td>20+</td>
     </tr>
     <tr>
       <td>TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA</td>
       <td>20+</td>
       <td>20+</td>
     </tr>
     <tr>
       <td>TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384</td>
       <td>20+</td>
       <td></td>
     </tr>
     <tr>
       <td>TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384</td>
       <td>20+</td>
       <td>20+</td>
     </tr>
     <tr>
       <td>TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256</td>
       <td>24+</td>
       <td>24+</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDHE_RSA_WITH_NULL_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDHE_RSA_WITH_RC4_128_SHA</td>
       <td>20-25</td>
       <td>20-23</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_ECDSA_WITH_NULL_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_ECDSA_WITH_RC4_128_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_RSA_WITH_AES_128_CBC_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_RSA_WITH_AES_256_CBC_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_RSA_WITH_NULL_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_RSA_WITH_RC4_128_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_anon_WITH_AES_128_CBC_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_anon_WITH_AES_256_CBC_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_anon_WITH_NULL_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_ECDH_anon_WITH_RC4_128_SHA</td>
       <td>20-22</td>
       <td></td>
     </tr>
     <tr>
       <td>TLS_EMPTY_RENEGOTIATION_INFO_SCSV</td>
       <td>20+</td>
       <td>20+</td>
     </tr>
     <tr>
       <td>TLS_FALLBACK_SCSV</td>
       <td>21+</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_NULL_WITH_NULL_NULL</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_PSK_WITH_3DES_EDE_CBC_SHA</td>
       <td>21-22</td>
       <td></td>
     </tr>
     <tr>
       <td>TLS_PSK_WITH_AES_128_CBC_SHA</td>
       <td>21+</td>
       <td>21+</td>
     </tr>
     <tr>
       <td>TLS_PSK_WITH_AES_256_CBC_SHA</td>
       <td>21+</td>
       <td>21+</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_PSK_WITH_RC4_128_SHA</td>
       <td>21-25</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_RSA_EXPORT_WITH_DES40_CBC_SHA</td>
       <td>1-8</td>
       <td>1-8</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_RSA_WITH_3DES_EDE_CBC_SHA</td>
       <td>1-8</td>
       <td>1-8</td>
     </tr>
     <tr>
       <td>TLS_RSA_WITH_AES_128_CBC_SHA</td>
       <td>9+</td>
       <td>9+</td>
     </tr>
     <tr>
       <td>TLS_RSA_WITH_AES_128_CBC_SHA256</td>
       <td>20+</td>
       <td></td>
     </tr>
     <tr>
       <td>TLS_RSA_WITH_AES_128_GCM_SHA256</td>
       <td>20+</td>
       <td>20+</td>
     </tr>
     <tr>
       <td>TLS_RSA_WITH_AES_256_CBC_SHA</td>
       <td>9+</td>
       <td>20+</td>
     </tr>
     <tr>
       <td>TLS_RSA_WITH_AES_256_CBC_SHA256</td>
       <td>20+</td>
       <td></td>
     </tr>
     <tr>
       <td>TLS_RSA_WITH_AES_256_GCM_SHA384</td>
       <td>20+</td>
       <td>20+</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_RSA_WITH_DES_CBC_SHA</td>
       <td>1-8</td>
       <td>1-8</td>
     </tr>
     <tr class="deprecated">
       <td>TLS_RSA_WITH_NULL_MD5</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_RSA_WITH_NULL_SHA</td>
       <td>1-8</td>
       <td></td>
     </tr>
     <tr class="deprecated">
       <td>TLS_RSA_WITH_NULL_SHA256</td>
       <td>20-22</td>
       <td></td>
     </tr>
   </tbody>
 </table>
