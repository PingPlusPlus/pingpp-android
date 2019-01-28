package com.pingxx.demoapp;

import com.pingplusplus.android.Pingpp;
import com.pingplusplus.ui.PaymentHandler;
import com.pingplusplus.ui.PingppUI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class OneSDKActivity extends FragmentActivity
    implements OnClickListener {

  private static String URL = "http://218.244.151.190/demo/charge";

  private ListView mListView;
  private GoodsAdapter myAdapter;
  private List<Good> mList;
  private TextView amountView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_yi_shou_kuan);

    mListView = (ListView) findViewById(R.id.listView);
    amountView = (TextView) findViewById(R.id.textview_amount);
    mList = new ArrayList<Good>();

    mList.add(new Good("橡胶花盆", R.drawable.icon, 1, 0.02f));
    mList.add(new Good("搪瓷水壶", R.drawable.icon2, 1, 0.03f));
    mList.add(new Good("扫把和簸箕", R.drawable.icon3, 1, 0.05f));

    calculate();
    myAdapter = new GoodsAdapter(this, mList);
    mListView.setAdapter(myAdapter);
    findViewById(R.id.button).setOnClickListener(this);
    myAdapter.registerDataSetObserver(new DataSetObserver() {
      @Override public void onChanged() {
        calculate();
        super.onChanged();
      }
    });

    //设置需要使用的支付方式
    PingppUI.enableChannels(new String[] { "wx", "alipay", "upacp", "bfb_wap", "jdpay_wap" });

    Pingpp.DEBUG = true;
  }

  private void calculate() {
    float amount = 0;
    for (Good good : mList) {
      amount += good.getPrice() * good.getCount();
    }
    amountView.setText(String.format("%.2f", amount));
  }

  @Override public void onClick(View v) {
    // 产生个订单号
    String orderNo = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    // 计算总金额（以分为单位）
    int amount = 0;
    JSONArray billList = new JSONArray();
    for (Good good : mList) {
      amount += good.getPrice() * good.getCount() * 100;
      billList.put(good.getName() + " x " + good.getCount());
    }
    // 构建账单json对象
    JSONObject bill = new JSONObject();

    // 自定义的额外信息 选填
    JSONObject extras = new JSONObject();
    try {
      extras.put("extra1", "extra1");
      extras.put("extra2", "extra2");
    } catch (JSONException e) {
      e.printStackTrace();
    }

    try {
      bill.put("order_no", orderNo);
      bill.put("amount", amount);
      bill.put("custom_params", extras);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    // 创建支付选择渠道的面板
    PingppUI.showPaymentChannels(this, bill.toString(), URL, new PaymentHandler() {
      @Override public void handlePaymentResult(Intent data) {
        if (data != null) {
          /**
           * code：支付结果码  -2:服务端错误、 -1：失败、 0：取消、1：成功
           * error_msg：支付结果信息
           */
          int code = data.getExtras().getInt("code");
          String result = data.getExtras().getString("result");
        }
      }
    });
  }
}
