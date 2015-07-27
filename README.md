壹收款 Android SDK
============

## 简介

[lib_androidstudio](/lib_androidstudio) 目录是 AndroidStudio 开发环境需要的 aar 文件。<br>
[example_androidstudio](/example_androidstudio) 目录是两个AndroidStudio开发环境的接入示例。分别是使用aar、jar方式，示例仅供参考。<br>

[lib_eclipse](/lib_eclipse) 目录下是 Eclipse 开发环境需要的第三方库。<br>
[libs](/lib_eclipse/libs) 目录下的是 jar 文件。<br>
[bdwallet\_pay\_sdk](/lib_eclipse/bdwallet_pay_sdk) 是 Library Project。<br>
[example_eclipse](/example_eclipse) 文件夹里面是一个Eclipse开发环境的接入示例，该示例仅供参考。<br>

安卓端如果应用没有安装银联支付控件：请到 [这里](http://mobile.unionpay.com/getclient?platform=android&type=securepayplugin) 下载。

## 运行环境

 Android 2.3 及以上版本的 Android 机器上运行。

## 编译环境

Android sdk 4.4 以及以上，jdk1.6 以上。 

##注意事项

壹收款可能会与友盟、百度地图等其他第三方jar包冲突，当同时使用这些jar包的时候用户需要根据情况判断保留哪一方的jar包。

##版本1.0.1
1、申请Charge的时候，增加一个扩展字段extras。extras的各位为一个json对象，接收参数的方式由用户自行解析。<br>
2、增加一个日志类PingppLog，用户可以通过 PingppLog.DEBUG 打开日志开关，方便调试。
3、更改所有资源文件为 pingpp_ 开头
