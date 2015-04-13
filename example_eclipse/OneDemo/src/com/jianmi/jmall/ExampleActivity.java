package com.jianmi.jmall;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pingplusplus.libone.PayActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sunkai on 15/3/17.
 */
public class ExampleActivity extends Activity implements View.OnClickListener {
    private ListView mListView;
    private GoodsAdapter myAdapter;
    private List<Good> mList;
    private TextView amountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        mListView = (ListView) findViewById(R.id.listView);
        amountView = (TextView) findViewById(R.id.textview_amount);
        mList = new ArrayList<Good>();

        mList.add(new Good("橡胶花盆", R.drawable.icon, 1, 12.00f));
        mList.add(new Good("搪瓷水壶", R.drawable.icon2, 1, 13.00f));
        mList.add(new Good("扫把和簸箕", R.drawable.icon3, 1, 14.00f));

        calculate();
        myAdapter = new GoodsAdapter(this, mList);
        mListView.setAdapter(myAdapter);
        findViewById(R.id.button).setOnClickListener(this);
        myAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                calculate();
                super.onChanged();
            }
        });
        
        //设置要使用的支付方式
        PayActivity.SHOW_CHANNEL_WECHAT = true;
        PayActivity.SHOW_CHANNEL_UPMP = true;
        PayActivity.SHOW_CHANNEL_BFB = true;
        PayActivity.SHOW_CHANNEL_ALIPAY = true;
    }

    private void calculate() {
        float amount = 0;
        for (Good good : mList) {
            amount += good.getPrice() * good.getCount();
        }
        amountView.setText(String.format("%.2f", amount));
    }

    @Override
    public void onClick(View v) {
        // 产生个订单号
        String orderNo = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        //计算总金额（以分为单位）
        int amount = 0;
        JSONArray billList = new JSONArray();
        for (Good good : mList) {
            amount += good.getPrice() * good.getCount() * 100;
            billList.put(good.getName() + " x " + good.getCount());
        }
        //构建账单json对象
        JSONObject bill = new JSONObject();
        JSONObject displayItem = new JSONObject();
        try {
            displayItem.put("name", "商品");
            displayItem.put("contents", billList);
            JSONArray display = new JSONArray();
            display.put(displayItem);
            bill.put("order_no", orderNo);
            bill.put("amount", amount);
            bill.put("display", display);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //发起支付
        PayActivity.CallPayActivity(this,bill.toString(),MainActivity.URL);
    }
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
