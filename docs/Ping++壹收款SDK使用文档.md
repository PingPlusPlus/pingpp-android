## Ping++ Android 壹收款 使用文档

### 一、快速体验

Ping++ SDK 为开发者提供了 demo 程序，可以快速体验`壹收款`接入流程。下载 壹收款-SDK 之后将整个目录导入到您的 Android Studio（建议） 或者 Eclipse 之中。

使用 Android Studio 时，请选择 `Import project (Eclipse ADT, Gradle, etc.)` → `Import project from external model` → `Gradle`  

<img src="image/import-01.png" width="400" >
<img src="image/import-02.png" width="350" >

### 二、快速集成

#### 导入 Ping++ SDK

##### Android Studio
1. 在你的项目里选择 `Import Module...`，Source directory 定位到 `pingppone-android` 目录，也就是 SDK 的根目录。
2. 取消勾选 `:example`，将 `:lib:pingpp_one` 和  `:lib:pingpp`, `:lib:bdwallet_pay_sdk` 导入。
3. 将 `:lib:pingpp_one` 添加到你的项目的 Dependencies。或者直接在 build.gradle 的 dependencies 区块添加如下代码。

        compile project(':lib:pingpp_one')

    <img src="image/import-03.png" width="500" >

##### Eclipse ADT
1. 将 `lib` 目录作为 Library Porject 导入。
2. 导入后，把 pingpp_one 项目作为你的项目的依赖库。

    <img src="image/import-05.png" width="500" >

##### 权限声明
``` xml
<!-- 通用权限 -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.NFC"/>

<!-- 百度支付需要的权限 -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_SMS" />
```

##### 注册 activity
``` xml
<!-- Ping++ SDK -->
<activity
    android:name="com.pingplusplus.android.PaymentActivity"
    android:configChanges="orientation|screenSize"
    android:launchMode="singleTop"
    android:theme="@android:style/Theme.Translucent.NoTitleBar" />
    <!-- 应用内快捷支付 -->
<activity
    android:name="com.pingplusplus.nocard.activity.AddCardActivity"
    android:windowSoftInputMode="stateHidden|adjustPan">
</activity>
<activity
    android:name="com.pingplusplus.nocard.activity.ManagerCardActivity" />

<activity
    android:name="com.pingplusplus.libone.PayActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:theme="@android:style/Theme.Translucent"/>
<activity
    android:name="com.pingplusplus.libone.PaySuccessActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"/>

<!-- 微信支付 -->
<activity-alias
    android:name=".wxapi.WXPayEntryActivity"
    android:exported="true"
    android:targetActivity="com.pingplusplus.android.PaymentActivity" />

<!-- 支付宝 -->
<activity
    android:name="com.alipay.sdk.app.H5PayActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:exported="false"
    android:screenOrientation="behind" >
</activity>
<activity
    android:name="com.alipay.sdk.auth.AuthActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:exported="false"
    android:screenOrientation="behind" >
</activity>

<!-- 银联支付 -->
<activity android:name="com.unionpay.uppay.PayActivity"
android:configChanges="keyboardHidden|navigation|orientation|screenSize" />

<!-- 百度钱包 -->
<activity
    android:name="com.baidu.paysdk.login.LoginActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="false"
    android:theme="@style/EbpayThemeActivityWelcome"
    android:windowSoftInputMode="stateHidden" />
<activity
    android:name="com.baidu.paysdk.ui.WelcomeActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivityWelcome"
    android:windowSoftInputMode="stateHidden" />
<activity
    android:name="com.baidu.paysdk.ui.OrderHomeActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:hardwareAccelerated="false"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateHidden" />
<activity
    android:name="com.baidu.paysdk.ui.PayResultActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateHidden" />
<activity
    android:name="com.baidu.paysdk.ui.PcPwdCheckActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateVisible" />
<activity
    android:name="com.baidu.paysdk.ui.PwdCheckActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateVisible" />
<activity
    android:name="com.baidu.paysdk.ui.PwdSetAndConfirmActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateVisible" />
<activity
    android:name="com.baidu.paysdk.ui.PwdPayActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivitTranslucent"
    android:windowSoftInputMode="stateVisible|adjustResize" />
<activity
    android:name="com.baidu.paysdk.ui.PwdPaySmsActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivitTranslucent"
    android:windowSoftInputMode="stateHidden" />
<activity
    android:name="com.baidu.paysdk.ui.WebViewActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateHidden" />
<activity
    android:name="com.baidu.paysdk.ui.BindCardNoActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:launchMode="singleTask"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateHidden" />
<activity
    android:name="com.baidu.paysdk.ui.BindCardDetailActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:launchMode="singleTask"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateVisible|adjustPan" />
<activity
    android:name="com.baidu.paysdk.ui.BindCardDetailCredit2Activity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:launchMode="singleTask"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateVisible|adjustPan" />
<activity
    android:name="com.baidu.paysdk.ui.BindCardDetailCreditActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:launchMode="singleTask"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateVisible|adjustPan" />
<activity
    android:name="com.baidu.paysdk.ui.BindSmsActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="adjustResize" />
<activity
    android:name="com.baidu.paysdk.ui.SelectBindCardActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateHidden" />
<activity
    android:name="com.baidu.paysdk.ui.SignChannelListActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:hardwareAccelerated="false"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateHidden" />
```
### 三、设置支付渠道及参数

当设置支付渠道对应的常量为 true 的时候，就会在「壹收款」支付页面显示该渠道的支付按钮。

``` java
// 设置要使用的支付方式
PingppOne.enableChannels(new String[]{"wx", "alipay", "upacp", "cnp", "bfb"});
// 设置是否支持外卡支付， true：支持， false：不支持， 默认不支持外卡
PingppOne.SUPPORT_FOREIGN_CARD = true;
// 提交数据的格式，默认格式为 json
// PingppOne.CONTENT_TYPE = "application/x-www-form-urlencoded";
PingppOne.CONTENT_TYPE = "application/json";
// 设置APP_ID和PUBLISHABLE_KEY(应用内快捷支付需要)
PingppOne.APP_ID = "YOUR_APP_ID";
PingppOne.PUBLISHABLE_KEY = "YOUR_PUBLISHABLE_KEY";
// 是否开启日志
PingppLog.DEBUG = true;
```

### 四、发起支付
``` java
// 壹收款: 创建支付通道
PingppOne.showPaymentChannels(getSupportFragmentManager(), bill.toString(), null, URL, new PaymentHandler() {
    /**
     * 返回支付结果
     * @param data
     */
    @Override
    public void handlePaymentResult(Intent data) {}
    /**
     * 获取绑定新卡的info(包含短信验证码)
     * @param operate 操作方式
     * @param info 操作卡片的信息
     * @param callBack 接口回调
     */
    @Override
    public void handleCardOperation(int operate, JSONObject info, CardOperationCallback callBack) {}
});
```

### 五、管理卡片
``` java
/**
 * 跳转卡片管理页面
 */
 PingppOne.openCardManager(this, customer, new PaymentHandler());
```

### 六、结果处理
``` java
new PaymentHandler() {
    /**
     * 返回支付结果
     * @param data
     */
    @Override
    public void handlePaymentResult(Intent data) {
        if (data != null) {
            /**
             * result：支付结果信息
             * code：支付结果码  -2:用户自定义错误、 -1：失败、 0：取消、1：成功  2:应用内快捷支付结果
             */
            if (data.getExtras().getInt("code") != 2) {
                PingppLog.d(data.getExtras().getString("result") + "  " + data.getExtras().getInt("code"));
            } else {
                String result = data.getStringExtra("result");
                try {
                    JSONObject resultJson = new JSONObject(result);
                    if (resultJson.has("error")) {
                        result = resultJson.optJSONObject("error").toString();
                    } else if (resultJson.has("success")) {
                        result = resultJson.optJSONObject("success").toString();
                    }
                    PingppLog.d("result::" + result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 获取绑定新卡的info(包含短信验证码)
     * @param operate 操作方式
     * @param info 操作卡片的信息
     * @param callBack 接口回调
     */
    @Override
    public void handleCardOperation(int operate, JSONObject info, CardOperationCallback callBack) {
        try {
            if (operate == PINGPP_CARD_DELETE) {
                //操作删除卡片操作
            } else if (operate == PINGPP_CARD_SET_DEFAULT) {
                //设置默认卡片操作
            } else if (operate == PINGPP_CARD_ADD) {
                //添加卡片操作
                //String tokenId = tokenInfo.getString("id");
                //JSONObject smsCodeJson = tokenInfo.optJSONObject("sms_code");
            }
            //customer:customer字符串  true:操作卡片成功  errorStr:错误信息
            callBack.continueOperation(customer, true, errorStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 关于定制
用户可以根据需求自行定制一个或者多个支付渠道。但是定制 SDK 的时候需要注意以下几点
- libpingpp-xxxx.jar 这个 jar 包是必须的。
- PaymentActivity 必须在 AndroidManifest.xml 文件里面声明。

        <!-- Ping++ SDK 注册 -->
        <activity
            android:name="com.pingplusplus.android.PaymentActivity"
            android:launchMode="singleTop"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
- 权限
    1. 微信支付渠道是通过向“微信”客户端发起请求进行支付的，要求手机必须安装微信。如果没有安装微信，Ping++ SDK 会在支付结果中给予通知。
    2. 支付宝、银联等渠道，需要的权限为

            <uses-permission android:name="android.permission.INTERNET" />
            <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
            <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
            <uses-permission android:name="android.permission.READ_PHONE_STATE" />
            <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    3. 百度支付渠道，需要额外添加权限

            <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
            <uses-permission android:name="android.permission.WRITE_SETTINGS" />
            <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
            <uses-permission android:name="android.permission.READ_SMS" />
- 依赖
    1. 微信支付依赖包：`libammsdk.jar`
    2. 百度支付依赖：`bdwallet_pay_sdk` 工程
    3. 银联支付依赖：`UPPayAssisEx.jar`、`UPPayPluginExPro.jar`、`android-support-v4.jar`、`pingpp/libs` 目录下的 `.so` 文件和 `pingpp/assets` 目录下的 `data.bin` 文件
    4. 支付宝支付依赖包：`alipayxxxxxxxx.jar`
- 用户如果选择不使用某种渠道，可以把该渠道相关的 Activity 从 AndroidManifest.xml 删除。

### 日志开关
SDK 提供了日志功能，默认日志为关闭状态。
开发者可以通过下面设置打开日志开关。通过 `PING++` 来对日志进行筛选。
``` java
PingppLog.DEBUG = true;
```
