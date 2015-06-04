pingpp Android SDK
============

## 简介
lib 目录下是 Android SDK 文件，libs 目录下的 jar 文件放入项目的 libs 目录，bdwallet_pay_sdk 作为 Library Project 导入到项目。<br>
example 文件夹里面是一个简单的接入示例，该示例仅供参考。<br>
安卓端如果应用没有安装银联支付控件：请到 http://mobile.unionpay.com/getclient?platform=android&type=securepayplugin 下载。

## 版本要求
Android SDK 要求 Android 2.3 及以上版本

##注意事项

pingpp Android SDK可能会与友盟、百度地图等其他第三方jar包冲突，当同时使用这些jar包的时候用户需要根据情况判断保留哪一方的jar包。

## 接入方法
关于如何使用 SDK 请参考 [技术文档](https://pingxx.com/document) 或者参考 [example](https://github.com/PingPlusPlus/pingpp-android/tree/master/example) 文件夹里的示例。

## 更新日志

### 2.0.4
增加京东支付（jdpay_wap）
### 2.0.3
* 修复：<br>
Java 版本兼容问题

### 2.0.2
* 更改：<br>
新的测试模式

### 2.0.1
* 更改：<br>
百付宝 SDK 更新

### 2.0.0
* 更改：<br>
添加新渠道：百付宝<br>
callback 添加返回错误信息

### 1.0.5
* 修复：<br>
微信未登录情况下，点返回，应用无响应，需要再按一次返回才能继续操作

### 1.0.4
* 更改：<br>
更换了测试环境 URL
