Pingpp Android SDK
============

## 简介
lib 目录包含两个 Library Project：`pingpp` 和 `bdwallet_pay_sdk`。  
可以直接将两个 Library Project 作为依赖库，导入到你的项目。支持 Android Studio（建议）和 Eclipse。  
example 文件夹里面是一个简单的接入示例，该示例仅供参考。想使用该示例，请直接将本仓库导入。  
docs 目录里面是 Android SDK 的接入指南。

## 版本要求
Android SDK 要求 Android 2.3 及以上版本  
请使用 Java 7 或以上版本

## 接入方法
关于如何使用 SDK 请参考 docs 目录下面的接入文档或者参考提供的 example 工程。

## 注意事项
* Pingpp Android SDK 可能会与友盟、百度地图等其他第三方 jar 包冲突，当同时使用这些 jar 包的时候用户需要根据情况判断保留哪一方的 jar 包。
* 新版建议使用 Android Studio