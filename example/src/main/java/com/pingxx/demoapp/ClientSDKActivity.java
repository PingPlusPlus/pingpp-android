package com.pingxx.demoapp;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.pingplusplus.android.Pingpp;
import org.json.JSONObject;

/**
 * 
 * ping++ sdk 示例程序，仅供开发者参考。
 * 【说明文档】https://github.com/PingPlusPlus/pingpp-android/blob/master/docs/ping%2B%2B安卓SDK使用文档.md
 * 
 * 【注意】运行该示例，需要用户填写一个YOUR_URL。
 * 
 * ping++ sdk 使用流程如下：
 * 1）客户端已经有订单号、订单金额、支付渠道
 * 2）客户端请求服务端获得charge。服务端生成charge的方式参考ping++ 官方文档，地址 https://pingxx.com/guidance/server/import
 * 3）收到服务端的charge，调用ping++ sdk 。
 * 4）onActivityResult 中获得支付结构。
 * 5）如果支付成功。服务端会收到ping++ 异步通知，支付成功依据服务端异步通知为准。
 */
public class ClientSDKActivity extends Activity implements OnClickListener {

	/**
	 *开发者需要填一个服务端URL 该URL是用来请求支付需要的charge。务必确保，URL能返回json格式的charge对象。
	 *服务端生成charge 的方式可以参考ping++官方文档，地址 https://pingxx.com/guidance/server/import 
	 *
	 *【 http://218.244.151.190/demo/charge 】是 ping++ 为了方便开发者体验 sdk 而提供的一个临时 url 。
	 * 该 url 仅能调用【模拟支付控件】，开发者需要改为自己服务端的 url 。
	 */
    private static String YOUR_URL = "http://218.244.151.190/demo/charge";

    public static final String CHARGE_URL = YOUR_URL;
    public static final boolean LIVEMODE = false;
    
    /**
     * 银联支付渠道
     */
    private static final String CHANNEL_UPACP = "upacp";
    /**
     * 微信支付渠道
     */
    private static final String CHANNEL_WECHAT = "wx";
    /**
     * QQ钱包支付渠道
     */
    private static final String CHANNEL_QPAY = "qpay";
    /**
     * 支付宝支付渠道
     */
    private static final String CHANNEL_ALIPAY = "alipay";
    /**
     * 百度支付渠道
     */
    private static final String CHANNEL_BFB = "bfb_wap";
    /**
     * 京东支付渠道
     */
    private static final String CHANNEL_JDPAY_WAP = "jdpay_wap";

    private EditText amountEditText;
    private Button wechatButton;
    private Button alipayButton;
    private Button upmpButton;
    private Button bfbButton;
    private Button jdpayButton;
    private Button qpayButton;

    private String currentAmount = "";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_sdk);
        
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        wechatButton = (Button) findViewById(R.id.wechatButton);
        alipayButton = (Button) findViewById(R.id.alipayButton);
        upmpButton = (Button) findViewById(R.id.upmpButton);
        bfbButton = (Button) findViewById(R.id.bfbButton);
        jdpayButton =(Button) findViewById(R.id.jdpayButton);
        qpayButton =(Button) findViewById(R.id.qpayButton);

        wechatButton.setOnClickListener(ClientSDKActivity.this);
        alipayButton.setOnClickListener(ClientSDKActivity.this);
        upmpButton.setOnClickListener(ClientSDKActivity.this);
        bfbButton.setOnClickListener(ClientSDKActivity.this);
        jdpayButton.setOnClickListener(ClientSDKActivity.this);
        qpayButton.setOnClickListener(ClientSDKActivity.this);

        Pingpp.DEBUG = true;
        
        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(currentAmount)) {
                    amountEditText.removeTextChangedListener(this);
                    String replaceable = String.format("[%s, \\s.]", NumberFormat.getCurrencyInstance(Locale.CHINA).getCurrency().getSymbol(Locale.CHINA));
                    String cleanString = s.toString().replaceAll(replaceable, "");

                    if (cleanString.equals("") || new BigDecimal(cleanString).toString().equals("0")) {
                        amountEditText.setText(null);
                    } else {
                        double parsed = Double.parseDouble(cleanString);
                        String formatted = NumberFormat.getCurrencyInstance(Locale.CHINA).format((parsed / 100));
                        currentAmount = formatted;
                        amountEditText.setText(formatted);
                        amountEditText.setSelection(formatted.length());
                    }
                    amountEditText.addTextChangedListener(this);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        String amountText = amountEditText.getText().toString();
        if (amountText.equals("")) return;

        String replaceable = String.format("[%s, \\s.]", NumberFormat.getCurrencyInstance(Locale.CHINA).getCurrency().getSymbol(Locale.CHINA));
        String cleanString = amountText.toString().replaceAll(replaceable, "");
        int amount = Integer.valueOf(new BigDecimal(cleanString).toString());

        // 支付宝，微信支付，银联，百度钱包，QQ钱包，京东支付 按键的点击响应处理
        if (view.getId() == R.id.upmpButton) {
            new PaymentTask().execute(new PaymentRequest(CHANNEL_UPACP, amount));
        } else if (view.getId() == R.id.alipayButton) {
            new PaymentTask().execute(new PaymentRequest(CHANNEL_ALIPAY, amount));
        } else if (view.getId() == R.id.wechatButton) {
            new PaymentTask().execute(new PaymentRequest(CHANNEL_WECHAT, amount));
        } else if (view.getId() == R.id.bfbButton) {
        	  new PaymentTask().execute(new PaymentRequest(CHANNEL_BFB, amount));
        } else if(view.getId() == R.id.jdpayButton){
        	  new PaymentTask().execute(new PaymentRequest(CHANNEL_JDPAY_WAP, amount));
        } else if(view.getId() == R.id.qpayButton){
            new PaymentTask().execute(new PaymentRequest(CHANNEL_QPAY, amount));
        }
    }

    class PaymentTask extends AsyncTask<PaymentRequest, Void, String> {

        @Override
        protected void onPreExecute() {
            //按键点击之后的禁用，防止重复点击
            wechatButton.setOnClickListener(null);
            alipayButton.setOnClickListener(null);
            upmpButton.setOnClickListener(null);
            bfbButton.setOnClickListener(null);
            qpayButton.setOnClickListener(null);
        }

        @Override
        protected String doInBackground(PaymentRequest... pr) {

            PaymentRequest paymentRequest = pr[0];
            String data = null;
            try {
                JSONObject object = new JSONObject();
                object.put("channel", paymentRequest.channel);
                object.put("amount", paymentRequest.amount);
                object.put("livemode", paymentRequest.livemode);
                String json = object.toString();
                //向Your Ping++ Server SDK请求数据
                data = postJson(CHARGE_URL, json);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }

        /**
         * 获得服务端的charge，调用ping++ sdk。
         */
        @Override
        protected void onPostExecute(String data) {
        	if(null == data){
        		showMsg("请求出错", "请检查URL", "URL无法获取charge");
        		return;
        	}
        	Log.d("charge", data);

            //除QQ钱包外，其他渠道调起支付方式：
            //参数一：Activity  当前调起支付的Activity
            //参数二：data  获取到的charge或order的JSON字符串
            Pingpp.createPayment(ClientSDKActivity.this, data);

            //QQ钱包调用方式
            //参数一：Activity  当前调起支付的Activity
            //参数二：data  获取到的charge或order的JSON字符串
            //参数三：“qwalletXXXXXXX”需与AndroidManifest.xml中的scheme值一致
            //Pingpp.createPayment(ClientSDKActivity.this, data, "qwalletXXXXXXX");
        }

    }
    
	/**
	 * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
	 * 最终支付成功根据异步通知为准
	 */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        wechatButton.setOnClickListener(ClientSDKActivity.this);
        alipayButton.setOnClickListener(ClientSDKActivity.this);
        upmpButton.setOnClickListener(ClientSDKActivity.this);
        bfbButton.setOnClickListener(ClientSDKActivity.this);
        qpayButton.setOnClickListener(ClientSDKActivity.this);

        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                /* 处理返回值
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                showMsg(result, errorMsg, extraMsg);
            }
        }
    }
    
    public void showMsg(String title, String msg1, String msg2) {
    	String str = title;
    	if (null !=msg1 && msg1.length() != 0) {
    		str += "\n" + msg1;
    	}
    	if (null !=msg2 && msg2.length() != 0) {
    		str += "\n" + msg2;
    	}
    	AlertDialog.Builder builder = new Builder(ClientSDKActivity.this);
    	builder.setMessage(str);
    	builder.setTitle("提示");
    	builder.setPositiveButton("OK", null);
    	builder.create().show();
    }

    /**
     * 获取charge
     * @param urlStr charge_url
     * @param json 获取charge的传参
     * @return charge
     * @throws IOException
     */
    private static String postJson(String urlStr, String json) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.getOutputStream().write(json.getBytes());

        if(conn.getResponseCode() == 200) {
            BufferedReader
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
        return null;
    }

    class PaymentRequest {
        String channel;
        int amount;
        boolean livemode;

        public PaymentRequest(String channel, int amount) {
            this.channel = channel;
            this.amount = amount;
            this.livemode = LIVEMODE;
        }
    }
}
