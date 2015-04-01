package com.jianmi.jmall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pingplusplus.libone.PayActivity;

/**
 * Created by sunkai on 15/3/17.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    public static final String URL = "YOUR_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
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
            case R.id.button:
                startActivity(new Intent(MainActivity.this, ExampleActivity.class));
                break;
            case R.id.button2:
                String bill = " {\"order_no\":\"201503230255221\",\"amount\":100,\"display\":[{\"name\":\"商品\",\"contents\":[\"橡胶花盆 x 1\",\"搪瓷水壶 x 1\",\"扫把和簸箕 x 1\"]}]}";
                PayActivity.CallPayActivity(this, bill, URL);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PayActivity.PAYACTIVITY_REQUEST_CODE && resultCode == PayActivity.PAYACTIVITY_RESULT_CODE) {
            Toast.makeText(this,data.getExtras().getString("result"),Toast.LENGTH_LONG).show();
        }
    }
}
