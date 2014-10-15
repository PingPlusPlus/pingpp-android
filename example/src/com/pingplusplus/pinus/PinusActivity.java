package com.pingplusplus.pinus;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;
import com.pingplusplus.android.PaymentActivity;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;



public class PinusActivity extends Activity implements View.OnClickListener{

	private static final String URL = "YOUR-URL";
	private static final int REQUEST_CODE_PAYMENT = 1;
    private static final String CHANNEL_UPMP = "upmp";
    private static final String CHANNEL_WECHAT = "wx";
    private static final String CHANNEL_ALIPAY = "alipay";
	
	private EditText amountEditText;
    private Button wechatButton;
    private Button alipayButton;
    private Button upmpButton;
    
    private String currentAmount = "";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        wechatButton = (Button) findViewById(R.id.wechatButton);
        alipayButton = (Button) findViewById(R.id.alipayButton);
        upmpButton = (Button) findViewById(R.id.upmpButton);
         wechatButton.setOnClickListener(PinusActivity.this);
        alipayButton.setOnClickListener(PinusActivity.this);
        upmpButton.setOnClickListener(PinusActivity.this);
	
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

                String replaceable = String.format("[%s,.]", NumberFormat.getCurrencyInstance().getCurrency().getSymbol());
                String cleanString = s.toString().replaceAll(replaceable, "");

                if (new BigDecimal(cleanString).toString().equals("0")) {
                    amountEditText.setText(null);
                } else {
                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));
                    currentAmount = formatted;
                    amountEditText.setText(formatted);
                    amountEditText.setSelection(formatted.length());
                }
                amountEditText.addTextChangedListener(this);
            }
        }
    });
}



   public void onClick(View view) {
       String amountText = amountEditText.getText().toString();
       if (amountText.equals("")) return;

       String replaceable = String.format("[%s,.]", NumberFormat.getCurrencyInstance().getCurrency().getSymbol());
       String cleanString = amountText.toString().replaceAll(replaceable, "");
       int amount = Integer.valueOf(new BigDecimal(cleanString).toString());

       // 支付宝，微信支付，银联按键的点击响应处理
       if (view.getId() == R.id.upmpButton) {
           new PaymentTask().execute(new PaymentRequest(CHANNEL_UPMP, amount));
       } else if (view.getId() == R.id.alipayButton) {
           new PaymentTask().execute(new PaymentRequest(CHANNEL_ALIPAY, amount));
       } else if (view.getId() == R.id.wechatButton) {
           new PaymentTask().execute(new PaymentRequest(CHANNEL_WECHAT, amount));
       }
   }

class PaymentTask extends AsyncTask<PaymentRequest, Void, String> {

    @Override
    protected void onPreExecute() {

        //按键点击之后的禁用，防止重复点击
        wechatButton.setOnClickListener(null);
        alipayButton.setOnClickListener(null);
        upmpButton.setOnClickListener(null);
}

    @Override
    protected String doInBackground(PaymentRequest... pr) {

        PaymentRequest paymentRequest = pr[0];
        String data = null;
        String json = new Gson().toJson(paymentRequest);
        try {

            //向Your Ping++ Server SDK请求数据
            data = postJson(URL, json);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String data) {

        Intent intent = new Intent();
    	String packageName = getPackageName();
    	ComponentName componentName = new ComponentName(packageName, packageName + ".wxapi.WXPayEntryActivity");
        intent.setComponent(componentName);
    	intent.putExtra(PaymentActivity.EXTRA_CHARGE,data);
    	startActivityForResult(intent, REQUEST_CODE_PAYMENT);

    }


}

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        wechatButton.setOnClickListener(PinusActivity.this);
        alipayButton.setOnClickListener(PinusActivity.this);
        upmpButton.setOnClickListener(PinusActivity.this);

        //支付页面返回处理
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");

                /* 处理返回值
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */

                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "User canceled", Toast.LENGTH_SHORT).show();
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Toast.makeText(this, "An invalid Credential was submitted.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private static String postJson(String url, String json) throws IOException {
        MediaType type = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(type, json);
        Request request = new Request.Builder().url(url).post(body).build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }



    class PaymentRequest {
        String channel;
        int amount;

        public PaymentRequest(String channel, int amount) {
            this.channel = channel;
            this.amount = amount;
        }
    }

}
