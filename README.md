Pingpp Android SDK
============

## 简介
lib 目录下是 Android SDK 文件，libs 目录下的 jar 和 so 文件放入项目的 libs 目录，bdwallet_pay_sdk 作为 Library Project 导入到项目。  
example 文件夹里面是一个简单的接入示例，该示例仅供参考。  
docs 目录里面是 Android SDK 的接入指南。


## 版本要求
Android SDK 要求 Android 2.3 及以上版本

## 注意事项

Pingpp Android SDK可能会与友盟、百度地图等其他第三方 jar 包冲突，当同时使用这些 jar 包的时候用户需要根据情况判断保留哪一方的 jar 包。

## 接入方法
关于如何使用 SDK 请参考 docs 目录下面的接入文档或者参考提供的 demo 工程。

## 更新日志

### 2.0.6
更换银联支付 SDK

### 2.0.5
更新银联支付到 3.1.0，接入方式改为 jar 方式接入。  
更新支付宝到 alipaySDK-20150818.jar

### 2.0.4
增加京东支付（jdpay_wap）

### 2.0.3
* 修复：  
Java 版本兼容问题

### 2.0.2
* 更改：  
新的测试模式

### 2.0.1
* 更改：  
百付宝 SDK 更新

### 2.0.0
* 更改：  
添加新渠道：百付宝  
callback 添加返回错误信息

### 1.0.5
* 修复：  
微信未登录情况下，点返回，应用无响应，需要再按一次返回才能继续操作

### 1.0.4
* 更改：  
更换了测试环境 URL
