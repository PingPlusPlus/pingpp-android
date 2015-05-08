壹收款 Android SDK
============

## 简介

[lib_androidstudio](/lib_androidstudio) 目录是 Android Studio 开发环境需要的 aar 文件。<br>
[example_androidstudio](/example_androidstudio) 目录是一个简单的接入示例，该示例仅供参考。<br>

[lib_eclipse](/lib_eclipse) 目录下是 Eclipse 开发环境需要的第三方库。<br>
[libs](/lib_eclipse/libs) 目录下的是 jar 文件。<br>
[bdwallet\_pay\_sdk](/lib_eclipse/bdwallet_pay_sdk) 是 Library Project。<br>
[example_eclipse](/example_eclipse) 文件夹里面是一个简单的接入示例，该示例仅供参考。<br>

安卓端如果应用没有安装银联支付控件：请到 [这里](http://mobile.unionpay.com/getclient?platform=android&type=securepayplugin) 下载。

## 版本要求
Android SDK 要求 Android 2.3 及以上版本


##版本1.0.1
1、申请Charge的时候，增加一个扩展字段extras。extras的各位为一个json对象，接收参数的方式由用户自行解析。<br>
2、增加一个日志类PingppLog，用户刻意通过 PingppLog.DEBUG 打开日志开关，方便调试。
