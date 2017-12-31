# Mina-SSL

[web https demo](https://github.com/RUANHAOANDROID/SpringBootDemo)

server demo

client demo

Android demo


## 双向认证创建证书过程
1.创建服务端证书serverkey.jks

keytool -genkey -alias serverkey -keyalg RSA -keystore serverkey.jks -keysize 2048

输入密钥库口令:  

再次输入新口令: 

您的名字与姓氏是什么?

  [Unknown]:  rh
  
您的组织单位名称是什么?

  [Unknown]:  unistrong
  
您的组织名称是什么?

  [Unknown]:  unistrong
  
您所在的城市或区域名称是什么?

  [Unknown]:  sh
  
您所在的省/市/自治区名称是什么?

  [Unknown]:  sh
  
该单位的双字母国家/地区代码是什么?

  [Unknown]:  cn
  


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

[参照Google Android Cipher suites](https://developer.android.com/reference/javax/net/ssl/SSLEngine.html)

