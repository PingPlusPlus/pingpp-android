package com.jianmi.jmall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pingplusplus.libone.PayActivity;

/**
 * Created by sunkai on 15/3/17.
 * 
 * ping++ 【壹收款】 示例程序，仅供开发者参考。
 * 运行该示例，需要用户填写一个YOUR_URL。
 * 【壹收款】sdk的入口为 PayActivity.CallPayActivity(Activity, Bill, URL);
 * 
 *【壹收款】 使用流程如下：
 * 1) 客户端需要一个 YOUR_URL，该URl会接受一个post 请求，并且返回charge。 
 *    参考文档：https://github.com/PingPlusPlus/pingpp-android/blob/one/docs/壹收款安卓版本使用文档.md
 *           【参数说明】部分
 *    服务端生成charge的方式参考ping++ 官方文档，地址 https://pingxx.com/guidance/server/import
 * 2) 【壹收款】用户点击支付渠道之后，sdk 会自动发起请求，然后收到charge，进行支付。
 * 3) onActivityResult 中获得支付结构。
 * 4)如果支付成功。服务端会收到ping++ 异步通知，支付成功依据服务端异步通知为准。
 */
public class MainActivity extends Activity implements View.OnClickListener {
	/**
	 *开发者需要填一个服务端URL 该URL是用来请求支付需要的charge。务必确保，URL能返回json格式的charge对象。
	 *服务端生成charge 的方式可以参考ping++官方文档，地址 https://pingxx.com/guidance/server/import 
	 *
	 */
     public static final String URL = "YOUR_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        //设置要使用的支付方式
        PayActivity.SHOW_CHANNEL_WECHAT = true;
        PayActivity.SHOW_CHANNEL_UPMP = true;
        PayActivity.SHOW_CHANNEL_BFB = true;
        PayActivity.SHOW_CHANNEL_ALIPAY = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, ExampleActivity.class));
                break;
            case R.id.button2:
                String bill = " {'order_no':'2015032302552213','amount':100,'display':[{'name':'商品','contents':['橡胶花盆 x 1','搪瓷水壶 x 1','扫把和簸箕 x 1']}]}";
                PayActivity.CallPayActivity(this, bill, URL);
                break;
            default:
                break;
        }
    }
    
	/**
	 * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
	 * 最终支付成功根据异步通知为准
	 */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PayActivity.PAYACTIVITY_REQUEST_CODE) {
            if (resultCode == PayActivity.PAYACTIVITY_RESULT_CODE) {
                Toast.makeText(this, data.getExtras().getString("result"), Toast.LENGTH_LONG).show();
            }else if(resultCode == Activity.RESULT_CANCELED){
                //pressed back
                Toast.makeText(this, data.getExtras().getString("result"), Toast.LENGTH_LONG).show();
            }
        }
    }

}
