###SDK

####Android

#####下载
    地址：XXXXX
#####配置初始化

#####依赖包
     1. alipaysdk.jar
     2. alipaysecsdk.jar
     3. alipayutdid.jar
     4. libammsdk.jar
     5. libpingpp.jar
     6. UPPayAssisEx.jar
     
     以上jar包位置在下载目录的lib/libs
     
#####依赖工程
     bdwallet_pay_sdk
    
     该工程再下载目录的lib/bdwallet_pay_sdk 
     
 #####备注
 
     银联支付需要安装
#####权限声明
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    <uses-permission android:name="android.permission.SEND_SMS" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    <uses-permission android:name="android.permission.READ_SMS" />            
    
#####注册activity
 
      <!-- ping++SDK 注册 -->
      <activity
            android:name="com.pingplusplus.android.PaymentActivity"
            android:launchMode="singleTop"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
      <!-- 微信支付注册-->
      <activity-alias
            android:name=".wxapi.WXPayEntryActivity"
            android:exported="true"
            android:targetActivity="com.pingplusplus.android.PaymentActivity" />
      <!-- 支付宝注册 -->
      <activity
            android:name="com.alipay.sdk.app.H5PayActivity"
            android:configChanges="orientation|keyboardHidden|navigation"
            android:exported="false"
            android:screenOrientation="behind" />
      <activity
            android:name="com.alipay.sdk.auth.AuthActivity"
            android:configChanges="orientation|keyboardHidden|navigation"
            android:exported="false"
            android:screenOrientation="behind" />
      <!-- 百度支付注册-->
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
            android:theme="@style/EbpayThemeActivityWelcome"
            android:screenOrientation="portrait"
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
            android:theme="@style/EbpayThemeActivit"
            android:screenOrientation="portrait"
            android:windowSoftInputMode="stateVisible" />
        <activity
            android:name="com.baidu.paysdk.ui.PwdCheckActivity"
            android:configChanges="keyboardHidden|navigation|orientation|screenSize"
            android:excludeFromRecents="true"
            android:exported="@bool/bd_wallet_switch_global_debug"
            android:theme="@style/EbpayThemeActivit"
            android:screenOrientation="portrait"
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
            android:theme="@style/EbpayThemeActivitTranslucent"
            android:screenOrientation="portrait"
            android:windowSoftInputMode="stateVisible|adjustResize" />
        <activity
            android:name="com.baidu.paysdk.ui.PwdPaySmsActivity"
            android:configChanges="keyboardHidden|navigation|orientation|screenSize"
            android:excludeFromRecents="true"
            android:exported="@bool/bd_wallet_switch_global_debug"
            android:theme="@style/EbpayThemeActivitTranslucent"
            android:screenOrientation="portrait"
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
            android:theme="@style/EbpayThemeActivit"
            android:screenOrientation="portrait"
            android:windowSoftInputMode="stateHidden" />
        <activity
            android:name="com.baidu.paysdk.ui.BindCardDetailActivity"
            android:configChanges="keyboardHidden|navigation|orientation|screenSize"
            android:excludeFromRecents="true"
            android:exported="@bool/bd_wallet_switch_global_debug"
            android:theme="@style/EbpayThemeActivit"
            android:screenOrientation="portrait"
            android:windowSoftInputMode="stateVisible|adjustPan" />
        <activity
            android:name="com.baidu.paysdk.ui.BindCardDetailCredit2Activity"
            android:configChanges="keyboardHidden|navigation|orientation|screenSize"
            android:excludeFromRecents="true"
            android:exported="@bool/bd_wallet_switch_global_debug"
            android:screenOrientation="portrait"
            android:theme="@style/EbpayThemeActivit"
            android:windowSoftInputMode="stateVisible|adjustPan" />
        <activity
            android:name="com.baidu.paysdk.ui.BindCardDetailCreditActivity"
            android:configChanges="keyboardHidden|navigation|orientation|screenSize"
            android:excludeFromRecents="true"
            android:exported="@bool/bd_wallet_switch_global_debug"
            android:screenOrientation="portrait"
            android:windowSoftInputMode="stateVisible|adjustPan"
            android:theme="@style/EbpayThemeActivit"/>
        <activity
            android:name="com.baidu.paysdk.ui.BindSmsActivity"
            android:configChanges="keyboardHidden|navigation|orientation|screenSize"
            android:excludeFromRecents="true"
            android:screenOrientation="portrait"
            android:exported="@bool/bd_wallet_switch_global_debug"
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
            android:name="com.baidu.paysdk.ui.SecurityCenterActivity"
            android:configChanges="keyboardHidden|navigation|orientation|screenSize"
            android:excludeFromRecents="true"
            android:exported="@bool/bd_wallet_switch_global_debug"
            android:screenOrientation="portrait"
            android:theme="@style/EbpayThemeActivit"
            android:windowSoftInputMode="adjustUnspecified|stateHidden" />
        <activity
            android:name="com.baidu.paysdk.ui.PrivacyProtectionActivity"
            android:configChanges="keyboardHidden|navigation|orientation|screenSize"
            android:excludeFromRecents="true"
            android:exported="@bool/bd_wallet_switch_global_debug"
            android:screenOrientation="portrait"
            android:theme="@style/EbpayThemeActivit"
            android:windowSoftInputMode="adjustUnspecified|stateHidden" />
        <activity
            android:name="com.baidu.paysdk.ui.PhonePwdActivity"
            android:configChanges="keyboardHidden|navigation|orientation|screenSize"
            android:excludeFromRecents="true"
            android:exported="@bool/bd_wallet_switch_global_debug"
            android:theme="@style/EbpayThemeActivit"
            android:screenOrientation="portrait"
            android:windowSoftInputMode="adjustUnspecified|stateHidden" />
            


#####接入准备

#####获得charge对象
    charge对象是一个包含支付信息的json对象，是ping++ SDK发起支付的必须对象。该对象通过请求用户服务器获得，ping++提供了服务器端如何获取charge对象的SDK，只需要按照服务端的说明文档接入到自己服务器即可。

#####发起支付

    Intent intent = new Intent();
    String packageName = getPackageName();
    ComponentName componentName = new ComponentName(packageName, packageName + ".wxapi.WXPayEntryActivity");
    intent.setComponent(componentName);
    intent.putExtra(PaymentActivity.EXTRA_CHARGE, charge);
    startActivityForResult(intent, REQUEST_CODE_PAYMENT);
    
#####获取支付状态

重载onActivityResult 方法可以获得支付结果
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //支付页面返回处理
    if (requestCode == REQUEST_CODE_PAYMENT) {
        if (resultCode == Activity.RESULT_OK) {
            String result = data.getExtras().getString("pay_result");
            /* 处理返回值
             * "success" - payment succeed
             * "fail"    - payment failed
             * "cancel"  - user canceld
             * "invalid" - payment plugin not installed
             *
             * 如果是银联渠道返回 invalid，调用 UPPayAssistEx.installUPPayPlugin(this); 安装银联安全支付控件。
             */
            String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "User canceled", Toast.LENGTH_SHORT).show();
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(this, "An invalid Credential was submitted.", Toast.LENGTH_SHORT).show();
        }
    }
}

#####注意事项
    
    Android不允许再UI线程中进行网络请求，所以请求charge对象的时候请使用 thread+handler 或者使用AsyncTask。example里面的示例程序，使用的就是AsyncTask 方式请求charge对象。
    
####关于定制
    用户可以根据需求自行定制一个或者多个支付渠道。但是定制sdk的时候需要注意以下几点
    
1、libpingpp.jar 、libammsdk.jar 这两个jar包是必须的。
    
    其中libpingpp.jar 是ping++ SDK 的核心类。libammsdk.jar 是微信支付的核心类。因为微信支付方式要求比较特殊，所以无法从ping++ SDK 中彻底剔除微信支付所需的libammsdk.jar 
    
2、PaymentActivity 和 .wxapi.WXPayEntryActivity 必须在AndroidManifest.xml 文件里面声明。

     <!-- ping++SDK 注册 -->
      <activity
            android:name="com.pingplusplus.android.PaymentActivity"
            android:launchMode="singleTop"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
      <!-- 微信支付注册-->
      <activity-alias
            android:name=".wxapi.WXPayEntryActivity"
            android:exported="true"
            android:targetActivity="com.pingplusplus.android.PaymentActivity" />
            
3、权限

    1、微信支付渠道是通过向“微信“客户端发起请求进行支付的，要求手机必须安装微信。如果没有安装微信，ping++ sdk 会在支付结果中给予通知。不需要额外权限。
    2、银联支付渠道是通过“银联手机支付服务“进行支付的，要求手机必须安装“银联手机支付服务”。如果没有安装，ping++ sdk会在支付结果中给予提示。不需求额外权限。
    3、支付宝支付渠道，需要的权限为
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    4、百度支付渠道，需要的权限
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.SEND_SMS" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_SMS" />
    
4、依赖包
   
    1、微信支付依赖包： libammsdk.jar
    2、百度支付依赖包： bdwallet_pay_sdk 工程
    3、银联支付依赖包： UPPayAssisEx.jar 、UPPayPluginEx.apk
    4、支付宝支付依赖包： alipaysdk.jar、alipaysecsdk.jar、alipayutdid.jar
    
5、注册activity

    1、微信支付activity注册：
     <activity-alias
    android:name=".wxapi.WXPayEntryActivity"
    android:targetActivity="com.pingplusplus.android.PaymentActivity"
    android:exported="true"/>
    
    2、支付宝activity注册：
    <activity
    android:name="com.alipay.sdk.app.H5PayActivity"
    android:configChanges="orientation|keyboardHidden|navigation"
    android:exported="false"
    android:screenOrientation="behind" >
    </activity>
    <activity
    android:name="com.alipay.sdk.auth.AuthActivity"
    android:configChanges="orientation|keyboardHidden|navigation"
        android:exported="false"
    android:screenOrientation="behind" >
    </activity>
    
    3、银联支付，不需要注册额外的activity
    
    4、百度支付activity注册：
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
    android:theme="@style/EbpayThemeActivityWelcome"
    android:screenOrientation="portrait"
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
    android:theme="@style/EbpayThemeActivit"
    android:screenOrientation="portrait"
    android:windowSoftInputMode="stateVisible" />
    <activity
    android:name="com.baidu.paysdk.ui.PwdCheckActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:theme="@style/EbpayThemeActivit"
    android:screenOrientation="portrait"
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
    android:theme="@style/EbpayThemeActivitTranslucent"
    android:screenOrientation="portrait"
    android:windowSoftInputMode="stateVisible|adjustResize" />
    <activity
    android:name="com.baidu.paysdk.ui.PwdPaySmsActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:theme="@style/EbpayThemeActivitTranslucent"
    android:screenOrientation="portrait"
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
    android:theme="@style/EbpayThemeActivit"
    android:screenOrientation="portrait"
    android:windowSoftInputMode="stateHidden" />
    <activity
    android:name="com.baidu.paysdk.ui.BindCardDetailActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:theme="@style/EbpayThemeActivit"
    android:screenOrientation="portrait"
    android:windowSoftInputMode="stateVisible|adjustPan" />
    <activity
    android:name="com.baidu.paysdk.ui.BindCardDetailCredit2Activity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="stateVisible|adjustPan" />
    <activity
    android:name="com.baidu.paysdk.ui.BindCardDetailCreditActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:windowSoftInputMode="stateVisible|adjustPan"
    android:theme="@style/EbpayThemeActivit"/>
    <activity
    android:name="com.baidu.paysdk.ui.BindSmsActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:screenOrientation="portrait"
    android:exported="@bool/bd_wallet_switch_global_debug"
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
    android:name="com.baidu.paysdk.ui.SecurityCenterActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="adjustUnspecified|stateHidden" />
    <activity
    android:name="com.baidu.paysdk.ui.PrivacyProtectionActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:screenOrientation="portrait"
    android:theme="@style/EbpayThemeActivit"
    android:windowSoftInputMode="adjustUnspecified|stateHidden" />
    <activity
    android:name="com.baidu.paysdk.ui.PhonePwdActivity"
    android:configChanges="keyboardHidden|navigation|orientation|screenSize"
    android:excludeFromRecents="true"
    android:exported="@bool/bd_wallet_switch_global_debug"
    android:theme="@style/EbpayThemeActivit"
    android:screenOrientation="portrait"
    android:windowSoftInputMode="adjustUnspecified|stateHidden" />
 
            

    
