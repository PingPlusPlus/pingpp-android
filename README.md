# Ping++ Android SDK

## 目录

* [1. 简介](#1)
* [2. 环境要求](#2)
* [3. 快速体验](#3)
* [4. 工程配置及使用](#4)
    * [4.1 导入依赖包](#4.1)
    * [4.2 权限配置](#4.2)
    * [4.3 使用 Ping++ 标准版 SDK](#4.3)
* [5. 日志开关](#5)
* [6. 注意事项](#6)
* [7. 常见问题](#issues)

## <h2 id='1'>简介</h2>

lib 目录包含 Library Project: `pingpp`。  
可以直接将两个 Library Project 作为依赖库，导入到你的项目。支持 Android Studio。  
example 文件夹里面是一个简单的接入示例，该示例仅供参考。想使用该示例，请直接将本仓库导入。  
docs 目录里面是 Android SDK 的接入指南。

* **推荐使用 Gradle 方式**，本项目的库文件更新可能有延迟。

## <h2 id='2'>版本要求</h2>

Android SDK 要求 `Android 4.1` 及以上版本  
请使用 `Java 8` 或以上版本

**需要 Kotlin 标准库**

## 额外说明

1. [中间页说明](/docs/支付中间页面说明.md)

## <h2 id='3'>快速体验</h2>

### Android Studio

导入 pingpp-android 整个项目，即可运行该 demo。

<font color="red">需要注意: </font>测试微信支付，需要签名和包名与微信开放平台上的一致，才可支付成功。给出的 demo 并没给出正确的签名，会返回微信支付失败的结果。  
<font color="red">导入 demo 中可能会遇到的开发环境版本问题，修改 build.gradle 中的版本</font>

## <h2 id='4'>工程配置及使用</h2>

### <h3 id='4.1'>一、导入依赖包</h3>

<font color='red'>(注：依赖渠道 SDK 时，可能会和其他第三方SDK有冲突，移除依赖冲突的包就可以。如：[问题二](#issue2)、[问题三](#issue3))</font>

#### Gradle 导入方式

修改项目的 `build.gradle` 文件，添加 `bintray` 仓库地址

```groovy
allprojects {
    repositories {
        // ...其他仓库地址...
        mavenCentral()
        // maven {
        //     url "https://maven.aliyun.com/repository/jcenter"
        // }

        // 本地 aar 目录，根据实际 aar 放置的目录修改 dirs 中内容。以下为当前项目的示例
        flatDir {
            dirs(
                project(":lib:pingpp").file("libs")
            )
        }
    }
}
```

```groovy
dependencies {
    implementation 'com.pingxx:pingpp-android:2.3.6' // (Ping++ 标准版 SDK) 必须添加
    implementation 'com.tencent.mm.opensdk:wechat-sdk-android:6.8.26' // 使用微信支付时添加,具体版本参考微信官方文档
    implementation "com.alipay.sdk:alipaysdk-android:15.8.17" // 使用支付宝时添加。可参考 https://opendocs.alipay.com/open/04km1h
    implementation "com.pingxx:pingpp-android-upacp:3.5.9" // 使用银联支付时添加
    implementation 'com.pingxx:pingpp-qpay:2.1.19' // 使用QQ钱包时添加
    implementation 'com.pingxx:pingpp-android-cmbwallet:1.1.1' // 使用招行一网通时添加
    implementation 'com.pingxx:pingpp-ccbpay:2.1.19' // 使用建行支付时添加
    implementation 'com.pingxx:pingpp-android-cmpay:2.2.2' // 使用和包支付时添加
    implementation 'com.pingxx:pingpp-android-lakala:2.0.0' // 使用拉卡拉支付时添加
}
```

[微信 SDK](https://developers.weixin.qq.com/doc/oplatform/Downloads/Android_Resource.html)

#### Maven 导入方式

```xml
<dependency>
  <groupId>com.pingxx</groupId>
  <artifactId>pingpp-android</artifactId>
  <!--请将 VERSION 换成 SDK 对应的版本号-->
  <version>VERSION</version>
  <type>pom</type>
</dependency>
```

### <h3 id='4.2'>二、清单文件配置所需权限</h3>

> 缺少时或者需要覆盖时可按下述说明添加

<font color='red'>(注：有些权限是需要动态注册的,如 `READ_PHONE_STATE` 权限)</font>

``` xml
<!-- 通用权限 -->
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<!-- 银联需要的权限 -->
<uses-permission android:name="android.permission.NFC"/>
```

### <h3 id='4.3'>三、使用 Ping++ 标准版 SDK</h3>

#### 1. 清单文件注册相关类

- Ping++ SDK 所需要注册

> 已默认包含于 aar 文件中，如果不需要自定义，可以忽略。

```xml
<activity
    android:name="com.pingplusplus.android.PaymentActivity"
    android:configChanges="orientation|keyboardHidden|navigation|screenSize"
    android:launchMode="singleTop"
    android:theme="@android:style/Theme.Translucent.NoTitleBar" >
</activity>
```

- 微信支付需要注册
    <font color='red'> 注：
    1. 需要将以下“替换成自己 APK 的包名”换成在微信平台上注册填写的包名
    2. WxPayEntryActivity 这个类在 SDK 内部实现，开发者不需要额外实现该类
    </font>

```xml
<activity-alias
    android:name="替换成自己 APK 的包名.wxapi.WXPayEntryActivity"
    android:exported="true"
    android:targetActivity="com.pingplusplus.android.PaymentActivity" />
```

- QQ 钱包需注册(scheme 填写规则：qwallet + QQ 钱包中的 app_id)

```xml
<intent-filter>
   <action android:name="android.intent.action.VIEW"/>
   <category android:name="android.intent.category.BROWSABLE"/>
   <category android:name="android.intent.category.DEFAULT"/>
   <data android:scheme="qwalletXXXXXXXX"/>
</intent-filter>
```

将以上代码添加到 Ping++ SDK 注册的 Activity，如：

```xml
<activity
    android:name="com.pingplusplus.android.PaymentActivity">

    <intent-filter>
        <action android:name="android.intent.action.VIEW"/>
        <category android:name="android.intent.category.BROWSABLE"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <data android:scheme="qwallet1234567890"/>
    </intent-filter>
</activity>
```

- 招行一网通 app 需注册 `scheme`

```xml
<intent-filter>
   <action android:name="android.intent.action.VIEW"/>
   <category android:name="android.intent.category.DEFAULT"/>
   <data android:scheme="招行分配的 scheme"/>
</intent-filter>
```

将以上代码添加到 Ping++ SDK 注册的 Activity，如：

```xml
<activity
    android:name="com.pingplusplus.android.PaymentActivity">

    <intent-filter>
        <action android:name="android.intent.action.VIEW"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <data android:scheme="招行分配的 scheme"/>
    </intent-filter>
</activity>
```

- 建行支付需注册 (自定义 action-name 保持与服务端的 third_app_info 一致)

``` xml
<activity android:name="com.ccb.ccbnetpay.activity.appresult.ResultActivity"
    android:configChanges="orientation|keyboardHidden|screenSize"
    android:screenOrientation="portrait">
    <intent-filter>
        <action android:name="自定义 action-name"/>
        <category android:name="android.intent.category.DEFAULT"/>
    </intent-filter>
</activity>

<activity android:name="com.ccb.ccbnetpay.activity.CcbUnionPayActivity"
    android:configChanges="orientation|keyboardHidden|screenSize"
    android:screenOrientation="portrait" />

<activity android:name="com.ccb.ccbnetpay.activity.CcbH5PayActivity"
    android:configChanges="orientation|keyboardHidden|screenSize"
    android:screenOrientation="portrait" />
```

- 使用拉卡拉微信(wx_app_lakala)需要添加声明

```xml
<activity
    android:name="com.swwx.paymax.PaymentActivity"
    android:launchMode="singleTask"
    android:theme="@android:style/Theme.Translucent.NoTitleBar" />

<activity-alias
    android:name="${applicationId}.wxapi.WXEntryActivity"
    android:exported="true"
    android:targetActivity="com.swwx.paymax.PaymentActivity" />
```

#### 2. 获取到 charge/order 后，调起支付

##### 获取 charge/order

charge/order 对象是一个包含支付信息的 JSON 对象，是 Ping++ SDK 发起支付的必要参数。该参数需要请求用户服务器获得，服务端生成 charge 或 order 的方式参考 服务端接入简介。SDK 中的 demo 里面提供了如何获取 charge/order 的实例方法，供用户参考。

##### 调起支付

因为 Ping++ 已经封装好了相应的调用方法，所以只需要调用支付方法即可调起支付控件：  
(<font color='red'>注：该调用方法需要在主线程(UI 线程)完成</font>)

```java
// 参数一：Activity  当前调起支付的 Activity
// 参数二：data  获取到的 charge 或 order 的 JSON 字符串
Pingpp.createPayment(YourActivity.this, data);
```

#### 3. 获取支付结果

从 Activity 的 onActivityResult 方法中获得支付结果。支付成功后，用户服务器也会收到 Ping++ 服务器发送的异步通知。 (<font color='red'>最终支付成功请根据服务端异步通知为准</font>)

```java
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // 支付页面返回处理
    if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
       String result = data.getExtras().getString("pay_result");
       /* 处理返回值
        * "success" - 支付成功
        * "fail"    - 支付失败
        * "cancel"  - 取消支付
        * "invalid" - 支付插件未安装（一般是微信客户端未安装的情况）
        * "unknown" - app进程异常被杀死(一般是低内存状态下,app进程被杀死)
        */
       String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
       String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
    }
}

// 注：线下渠道无支付结果返回, 返回 unknown 字段。需要从服务端获取正确的支付结果。
```

#### 4. 其他辅助方法及变量

##### 1. 判断是否安装了招商银行客户端

```
// isInstalled   true: 已安装    false: 未安装
boolean isInstalled = Pingpp.isCmbWalletInstalled(context);
```

##### 2. 是否允许使用手机 Pay

```
// true: 允许 (默认)   false: 不允许
Pingpp.useSEPay(false);
```

##### 3. 控制招行支付方式

```
// 自动判断，安装有招行 app 时打开 app 支付，否则通过 webView 打开 H5 页面支付
Pingpp.setCmbPayMethod(CmbPayMethod.AUTO);
// 仅通过 webView 打开 H5 页面支付
Pingpp.setCmbPayMethod(CmbPayMethod.H5_ONLY);
// 仅通过打开招行 app 支付
Pingpp.setCmbPayMethod(CmbPayMethod.APP_ONLY);
```

##### 3. 设置招行 H5 地址

> 生产环境不需要设置

```
// 参数 1，Boolean，是否启用招行测试地址；
// 参数 2，String，招行测试 H5 地址。仅参数 1 为 true 时生效。如果参数 2 传 null，则会使用默认测试地址。
Pingpp.setCmbEnv(true, null);
Pingpp.setCmbEnv(true, "https://...");
```

#### 5. 调起签约接口

```java
// 参数一：Activity  当前调起支付的Activity
// 参数二：data  获取到的 agreement 的 JSON 字符串
Pingpp.signAgreement(YourActivity.this, data);
```

##### 获取签约结果

签约结果不会在客户端返回，商户需要自己在前端查询服务端的 `agreement` 对象状态。

## 混淆设置

> 使用 aar/jcenter 方式导入的不需要额外设置混淆。

<font color='red'>(注：将以下对应渠道的混淆代码加到主 module 以及该 SDK 依赖所在的 module 中，不然会出现 jar 包重复或者找不到该类的问题，如：[问题二](#issue2))</font>

```
# Ping++ 混淆过滤
-dontwarn com.pingplusplus.**
-keep class com.pingplusplus.** {*;}
-keep public class com.pingplusplus.android.Pingpp$CmbPayMethod {
    *;
}

# 支付宝混淆过滤
-dontwarn com.alipay.**
-keep class com.alipay.** {*;}

# 微信或 QQ 钱包混淆过滤
-dontwarn  com.tencent.**
-keep class com.tencent.** {*;}

# 银联支付混淆过滤
-dontwarn  com.unionpay.**
-keep class com.unionpay.** {*;}

# 内部 WebView 混淆过滤
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
```

## <h2 id='5'>日志开关</h2>

SDK 提供了日志功能，默认日志为关闭状态。 开发者可以通过下面设置打开日志开关。通过 PING++ 来对日志进行筛选。

``` java
//开启调试模式
Pingpp.enableDebugLog(true);
```

## <h2 id='6'>注意事项</h2>

- Pingpp Android SDK 可能会与友盟、百度地图等其他第三方 jar 包冲突，当同时使用这些 jar 包的时候用户需要根据情况判断保留哪一方的 jar 包。
- 请勿直接使用客户端支付结果作为最终判定订单状态的依据，由于 Ping++ 没有参与你的客户端和第三方渠道的交互，无法保证客户端支付结果的安全性，建议订单状态的更新对比客户端的渠道同步回调信息和服务端的 Ping++ Webhooks 通知来确定是否修改。

## <span id = "issues">常见问题</span>
### 问题一： 微信支付失败，返回 wx_err_code:-1

- 报错原因:
    微信验证 apk 的签名包名失败。
- 解决方案:
    1. 项目的 package 名字、AndroidManifest.xml 里面的包名，必须和微信开放平台注册的一致；
    2. 必须打包成为发布版本的 apk，apk 签名必须和在微信开放平台注册的一致，微信开放平台签名要求: MD5，无冒号；
    3. 清理微信缓存；
    4. 如果签名包名均正确，仍旧返回 -1 报错，请检查时间戳格式是否有问题或重置微信开放平台的安卓版本的签名包名。

###  <span id = "issue2">问题二：与其他第三方SDK有冲突(如:友盟SDK)</span>

- 报错Log:

```java
Error:Execution failed for task ':app:transformClassesWithJarMergingForDebug'.
com.android.build.api.transform.TransformException:
java.util.zip.ZipException: duplicate entry: a/a/a/a.class
```

- 报错原因:
    1. 没有加过滤混淆的代码
    2. 有重复的 jar 包存在

- 解决方案:
    1. 加上混淆过滤的代码(出现 a/a/a/a.class 的 log 时)
    2. 删除重复的 jar 包(可以是第三方 SDK 中的，也可以是 Ping++ SDK 中的 jar 包)

### <span id = "issue3">问题三：Ping++ 和其他第三方 SDK(如：高德地图)同时存在，使用 gradle 导入 Ping++, 会导致其他第三方 SDK（如：高德地图）找不到 so 而无法使用</span>

- 报错原因:
    Ping++ SDK 提供了 `armeabi-v7a`、`arm64-v8a`，而其他第三方 SDK(如：高德地图)提供了 `armeabi-v7a`, 当手机是 `arm64-v8a` 的会去加载 `arm64-v8a` 包下的 so 文件，这时会报其他第三方 SDK (如：高德地图)的 so 文件找不到，而你上面的代码在打包的时候就只打包了 `armeabi-v7a`，所以只会去 `armeabi-v7a` 包下找，因此不会出现报错 建议使用各种 SDK 时保持相同的 so 文件。

- 解决方案:
在 build.gradle 中设置 ndk

```java
ndk {
    // 选择要添加的对应 cpu 类型的 .so 库。选择的 so 文件需要各种 SDK 保持一致
    abiFilters "armeabi-v7a", "arm64-v8a", "x86", "x86_64"
    // 根据实际情况选择所有 SDK 可以支持的类型
}
```

### 问题四：银联支付报错：

```java
java.lang.ClassNotFoundException: org.simalliance.openmobileapi.SEService
或 can’t find reference class org.openmobileapi.SEService
```

- 报错原因:
    缺少 `org.simalliance.openmobileapi.SEService`, 编译失败

- 解决方案:
    将 example 中 libs 下的 `org.simalliance.openmobileapi.jar` 拷到项目中依赖，但不要打包进 apk 中，有些手机会存在这个 jar 包

    ```java
    dependencies {
        compileOnly files('libs/org.simalliance.openmobileapi.jar') // 使用 compileOnly, 不打包进 apk
    }
    ```

### 更多问题请到[帮助中心](https://help.pingxx.com/)搜索
