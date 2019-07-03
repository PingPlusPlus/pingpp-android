Android 中集成 Ping++ SDK
======================
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
lib 目录包含 Library Project：pingpp。
可以直接将两个 Library Project 作为依赖库，导入到你的项目。支持 Android Studio。
example 文件夹里面是一个简单的接入示例，该示例仅供参考。想使用该示例，请直接将本仓库导入。
docs 目录里面是 Android SDK 的接入指南。

## <h2 id='2'>版本要求</h2>
Android SDK 要求 Android 4.1 及以上版本  
请使用 Java 8 或以上版本
 
## <h2 id='3'>快速体验</h2>
### Android Studio
导入 pingpp-android 整个项目，即可运行该 demo。

<font color="red">需要注意: </font>测试微信支付,需要签名和包名与微信开放平台上的一致，才可支付成功。给出的demo并没给出正确的签名，会返回微信支付失败的结果。
<font color="red">导入demo中可能会遇到的开发环境版本问题，修改 build.gradle 中的版本</font>

## <h2 id='4'>工程配置及使用</h2>
### <h3 id='4.1'>一、导入依赖包</h3>

<font color='red'>(注：依赖渠道SDK时,可能会和其他第三方SDK有冲突,移除依赖冲突的包就可以.如：[问题二](#issue2)、[问题三](#issue3))</font>

#### Gradle 导入方式

```groovy
dependencies {
    implementation 'com.pingxx:pingpp-android:2.2.3' // (Ping++ 标准版 SDK) 必须添加
    implementation 'com.tencent.mm.opensdk:wechat-sdk-android-without-mta:5.4.0' // 使用微信支付时添加,具体版本参考微信官方文档
    implementation 'com.pingxx:pingpp-android-alipay:2.2.3' // 使用支付宝时添加
    implementation 'com.pingxx:pingpp-android-upacp:2.2.3' // 使用银联支付时添加
    implementation 'com.pingxx:pingpp-qpay:2.1.19' // 使用QQ钱包时添加
    implementation 'com.pingxx:pingpp-cmbwallet:2.1.19' // 使用招行一网通时添加
    implementation 'com.pingxx:pingpp-ccbpay:2.1.19' // 使用建行支付时添加
    implementation 'com.pingxx:pingpp-android-cmpay:2.2.2' // 使用和包支付时添加
}
```

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

#### 下载 SDK 导入
在 lib 目录中包含 pingpp（标准版 SDK）资源，其中包含支付所需的 jar 包和资源包，请按需拷贝相应的文件到项目中。

##### pingpp

- Ping++ 依赖包：`libpingpp-x.x.x`、`res` 资源文件和 `libpingpp.so` 文件(必须依赖的)
- 微信依赖包：`wechat-sdk-android-without-mta.jar`
- 支付宝依赖包：`alipaySdkxxxxxxxx.jar`
- 银联支付依赖包：`UPPayAssistEx.jar`、`UPPayPluginExPro.jar`、`libentryexpro.so`、`libuptsmaddon.so` 和 `assets` 目录下 `data.bin` 文件
- QQ钱包依赖包：`mqqopenpay.jar`
- 招行一网通(混淆加密方式可不配置相关的参数,非混淆加密方式需配置)：`cmbkeyboard.jar` 和 `res` 目录下 `cmb_` 开头的资源文件

### <h3 id='4.2'>二、清单文件配置所需权限</h3>

<font color='red'>(注：有些权限是需要动态注册的,如"READ_PHONE_STATE"权限)</font>

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

- Ping++ SDK所需要注册

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
    1. 需要将以下"替换成自己APK的包名"换成在微信平台上注册填写的包名
    2. WxPayEntryActivity这个类在SDK内部实现，开发者不需要额外实现该类
    </font>

```xml
<activity-alias
    android:name="替换成自己APK的包名.wxapi.WXPayEntryActivity"
    android:exported="true"
    android:targetActivity="com.pingplusplus.android.PaymentActivity" />
```

- QQ钱包需注册(scheme填写规则：qwallet + QQ钱包中的app_id)

```xml
<intent-filter>
   <action android:name="android.intent.action.VIEW"/>
   <category android:name="android.intent.category.BROWSABLE"/>
   <category android:name="android.intent.category.DEFAULT"/>
   <data android:scheme="qwalletXXXXXXXX"/>
</intent-filter>
```

将以上代码添加到Ping++ SDK注册的Activity，如：

```xml
<activity
    android:name="com.pingplusplus.android.PaymentActivity"
    android:configChanges="orientation|keyboardHidden|navigation|screenSize"
    android:launchMode="singleTop"
    android:theme="@android:style/Theme.Translucent.NoTitleBar" >
    <intent-filter>
        <action android:name="android.intent.action.VIEW"/>
        <category android:name="android.intent.category.BROWSABLE"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <data android:scheme="qwallet1234567890"/>
    </intent-filter>
</activity>
```

- 招行一网通(非混淆加密方式)需注册

```xml
<service android:name="cmb.pb.cmbsafe.CmbService" android:exported="false"/>
<activity
         android:name="cmb.pb.ui.PBKeyboardActivity"
         android:theme="@style/CmbDialogStyleBottom" />
```

(<font color='red'>招行一网通在非混淆加密方式下：需在string.xml中配置cmbkb_publickey字段，如：</font>)

```xml
<string name="cmbkb_publickey">填写自己的publickey</string>
```

#### 2. 获取到charge/order后，调起支付
##### 获取charge/order

charge/order 对象是一个包含支付信息的 JSON 对象，是 Ping++ SDK 发起支付的必要参数。该参数需要请求用户服务器获得，服务端生成 charge 或 order 的方式参考 服务端接入简介。SDK 中的 demo 里面提供了如何获取 charge/order 的实例方法，供用户参考。

##### 调起支付

因为 Ping++ 已经封装好了相应的调用方法，所以只需要调用支付方法即可调起支付控件：
(<font color='red'>注：该调用方法需要在主线程(UI线程)完成</font>)

```java
//参数一：Activity  当前调起支付的Activity
//参数二：data  获取到的charge或order的JSON字符串
Pingpp.createPayment(YourActivity.this, data);
```

#### 3. 获取支付结果

从 Activity 的 onActivityResult 方法中获得支付结果。支付成功后，用户服务器也会收到 Ping++ 服务器发送的异步通知。 (<font color='red'>最终支付成功请根据服务端异步通知为准</font>)

```java
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //支付页面返回处理
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

## 混淆设置
<font color='red'>(注：将以下对应渠道的混淆代码加到主module以及该SDK依赖所在的module中，不然会出现jar包重复或者找不到该类的问题，如：[问题二](#issue2))</font>

```
# Ping++ 混淆过滤
-dontwarn com.pingplusplus.**
-keep class com.pingplusplus.** {*;}

# 支付宝混淆过滤
-dontwarn com.alipay.**
-keep class com.alipay.** {*;}
 
# 微信或QQ钱包混淆过滤
-dontwarn  com.tencent.**
-keep class com.tencent.** {*;}
 
# 银联支付混淆过滤
-dontwarn  com.unionpay.**
-keep class com.unionpay.** {*;}
 
# 招行一网通混淆过滤
-keepclasseswithmembers class cmb.pb.util.CMBKeyboardFunc {
    public <init>(android.app.Activity);
    public boolean HandleUrlCall(android.webkit.WebView,java.lang.String);
    public void callKeyBoardActivity();
}
 
# 内部WebView混淆过滤
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
```

## <h2 id='5'>日志开关</h2>

SDK 提供了日志功能，默认日志为关闭状态。 开发者可以通过下面设置打开日志开关。通过 PING++ 来对日志进行筛选。

``` java
//开启调试模式
Pingpp.DEBUG = true;
```

## <h2 id='6'>注意事项</h2>

- Pingpp Android SDK 可能会与友盟、百度地图等其他第三方 jar 包冲突，当同时使用这些 jar 包的时候用户需要根据情况判断保留哪一方的 jar 包。
- 新版建议使用 Android Studio
- 请勿直接使用客户端支付结果作为最终判定订单状态的依据，由于 Ping++ 没有参与你的客户端和第三方渠道的交互，无法保证客户端支付结果的安全性，建议订单状态的更新对比客户端的渠道同步回调信息和服务端的 Ping++ Webhooks 通知来确定是否修改。

## <span id = "issues">常见问题</span>
### 问题一： 微信支付失败，返回wx_err_code:-1

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
    2. 有重复的jar包存在

- 解决方案:
    1. 加上混淆过滤的代码(出现a/a/a/a.class的log时)
    2. 删除重复的jar包(可以是第三方SDK中的,也可以是Ping++SDK中的jar包)

### <span id = "issue3">问题三：Ping++和其他第三方SDK(如：高德地图)同时存在，使用gradle导入Ping++, 会导致其他第三方SDK（如：高德地图）找不到so而无法使用</span>

- 报错原因:
    Ping++ SDK 提供了armeabi、armeabi-v7a 而其他第三方SDK(如：高德地图)提供了armeabi, 当手机是armeabi-v7a的 会去加载armeabi-v7a包下的so文件 这是会报其他第三方SDK(如：高德地图)的so文件找不到 而你上面的代码在打包的时候就只打包了armeabi 所以只会去armeabi包下找 因此不会出现报错 建议使用各种SDK时保持相同的so文件。

- 解决方案:
在build.gradle中设置ndk

```java
ndk {
    //选择要添加的对应cpu类型的.so库。选择的so文件需要各种SDK保持一致
    abiFilters 'armeabi', 'x86'
    // 还可以添加 'x86_64', 'mips', 'mips64',, 'armeabi-v7a', 'armeabi-v8a'
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
    将example中libs下的 `org.simalliance.openmobileapi.jar` 拷到项目中依赖，但不要打包进apk中，有些手机会存在这个jar包
    
    ```java
    dependencies {
        provided files('libs/org.simalliance.openmobileapi.jar') //使用provided,不打包进apk
    }
    ```
    
### 问题五：招行一网通键盘奔溃或弹不出键盘

- 报错原因:
    没有在 `string.xml` 中配置 `cmbkb_publickey` 字段
- 解决方案:
    在自己项目中 `res/values/string.xml` 下配置该字段
    
    ```xml
    <string name="cmbkb_publickey">填写自己的publickey</string>
    ```

### 更多问题请到[帮助中心](https://help.pingxx.com/)搜索
