壹收款 Android SDK
============

## 简介
lib 目录下是「壹收款」sdk 工程，libPingppOne 为「壹收款」library 工程 ，bdwallet_pay_sdk 为百度支付的 library 工程。libPingppOne 需要依赖 bdwallet_pay_sdk 工程<br>
example 文件夹里面是一个简单的接入示例，该示例仅供参考。<br>
docs 目录里面是「壹收款」sdk 的接入指南。

## 运行环境

 Android 2.3 及以上版本的 Android 机器上运行。

## 编译环境

Android sdk 4.4 以上，jdk1.6 以上。 （尽量升级到最新的 Android sdk 和 jdk）

##注意事项

壹收款可能会与友盟、百度地图等其他第三方jar包冲突，当同时使用这些jar包的时候用户需要根据情况判断保留哪一方的jar包。

##版本1.0.1
1、申请Charge的时候，增加一个扩展字段extras。extras的各位为一个json对象，接收参数的方式由用户自行解析。

2、增加一个日志类PingppLog，用户可以通过 PingppLog.DEBUG 打开日志开关，方便调试。

3、更改所有资源文件为 pingpp_ 开头
