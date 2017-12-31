# 示例包含

[Web HTTPS example](https://github.com/RUANHAOANDROID/SpringBootDemo)

Server example

Client example

Android example

## SSL/TLS概念以及应用参阅

[SSL/TLS 维基百科](https://zh.wikipedia.org/wiki/%E5%82%B3%E8%BC%B8%E5%B1%A4%E5%AE%89%E5%85%A8%E6%80%A7%E5%8D%94%E5%AE%9A)

[JSEE](https://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/JSSERefGuide.html#SSLContext)

[openssl](https://www.openssl.org/docs/manmaster/man1/openssl.html)

[keytool](https://docs.oracle.com/javase/6/docs/technotes/tools/solaris/keytool.html)


## 证书创建过程

#### Java Server and Client App
1. 创建服务器证书

```
keytool -genkey -alias serverkey -keyalg RSA -keystore serverkey.jks -keysize 2048

```

2.从服务端证书导出 server.cer

```
keytool -export -alias serverkey -keystore serverkey.jks -file server.cer
```


3.创建客户端jks clientkey.jks

```
keytool -genkey -alias clientkey -keyalg RSA -keystore clientkey.jks -keysize 2048

```

4.从客户端导出cer client.cer

```
keytool -export -alias clientkey -keystore clientkey.jks -file client.cer
```

5.把server.cer 导入到clienttrust.jks

```
keytool -import -alias serverkey -keystore clienttrust.jks -file server.cer
```

6.把client.cer 导入到servertrust.jks

```
keytool -import -alias clientkey -keystore servertrust.jks -file client.cer
```
### Android app

7.转换clientkey.jks为clientkey.bks(  [使用Portec工具](http://portecle.sourceforge.net/)、[使用文档](http://portecle.sourceforge.net/howtos.html))

8.转换clienttrust.jks 为 clienttrust.bks（同步骤7）

> 注：也可以在不使用Portecle工具的情况下使用BC命令行，[参照这篇netty 双向SSL/TLS双向认证](https://www.cnblogs.com/wangshen/p/5956854.html)

## BC库注意事项

/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/security

该路径下配置Java.security文本 更改RSA 适配相应size、配置bc库

## Android支持的协议以及版本

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


## 爬坑指南

#### java.lang.IllegalArgumentException
```
   org.apache.mina.util.DefaultExceptionMonitor(1806): Caused by: java.lang.IllegalArgumentException: TLS_ECDH_ECDSA_WITH_RC4_128_SHA is not supported.
  
```
[相应问题参照 Google Android “Cipher suites”](https://developer.android.com/reference/javax/net/ssl/SSLEngine.html)


## 参考

[mina 官方文档](http://mina.apache.org/mina-project/userguide/ch2-basics/sample-tcp-client.html)

[Java Secure Socket Extension (JSSE) Reference Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/JSSERefGuide.html#SSLContext)

[Netty使用JSSE实现SSLSocket通信](https://segmentfault.com/a/1190000010054860)

[portecle使用文档](http://portecle.sourceforge.net/howtos.html)

[KEYTOOL和PORTECLE介绍](http://alanzhang.me/2014/12/31/KEYTOOL%E5%92%8CPORTECLE%E4%BB%8B%E7%BB%8D/)

[portecle使用教学视频](https://www.youtube.com/watch?v=nSqKv7VlMcg)

[openssl](https://www.openssl.org/docs/manmaster/man1/openssl.html)

[Google Dev Android Https/SSL](https://developer.android.com/training/articles/security-ssl.html)

[Google Dev Android SSLContext参考](https://developer.android.com/reference/javax/net/ssl/SSLContext.html)

