package com.jianmi.jmall;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pingplusplus.android.PingppLog;
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
public class ExampleActivity extends Activity implements View.OnClickListener {

	/**
	 *开发者需要填一个服务端URL 该URL是用来请求支付需要的charge。务必确保，URL能返回json格式的charge对象。
	 *服务端生成charge 的方式可以参考ping++官方文档，地址 https://pingxx.com/guidance/server/import 
	 *
	 *【 http://218.244.151.190/demo/charge 】是 ping++ 为了方便开发者体验 sdk 而提供的一个临时 url 。
	 * 改 url 仅能调用【模拟支付控件】，开发者需要改为自己服务端的 url 。 
	 */
	private static String YOUR_URL ="http://218.244.151.190/demo/charge";
	public static final String URL = YOUR_URL;
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

		// 设置要使用的支付方式
		PayActivity.SHOW_CHANNEL_WECHAT = true;
		PayActivity.SHOW_CHANNEL_UPMP = true;
		PayActivity.SHOW_CHANNEL_BFB = true;
		PayActivity.SHOW_CHANNEL_ALIPAY = true;
		// PingPP日志开关
		PingppLog.DEBUG = true;
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
		String orderNo = new SimpleDateFormat("yyyyMMddhhmmss")
				.format(new Date());

		// 计算总金额（以分为单位）
		int amount = 0;
		JSONArray billList = new JSONArray();
		for (Good good : mList) {
			amount += good.getPrice() * good.getCount() * 100;
			billList.put(good.getName() + " x " + good.getCount());
		}
		// 自定义的额外信息 选填
		JSONObject extras = new JSONObject();
		try {
			extras.put("extra1", "extra1");
			extras.put("extra2", "extra2");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// 构建账单json对象
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
			bill.put("extras", extras);// 该字段选填
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// 发起支付
		PayActivity.CallPayActivity(this, bill.toString(), URL);
	}

	/**
	 * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
	 * 最终支付成功根据异步通知为准
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PayActivity.PAYACTIVITY_REQUEST_CODE) {
			if (resultCode == PayActivity.PAYACTIVITY_RESULT_CODE) {
				Toast.makeText(
						this,
						data.getExtras().getString("result") + "  "
								+ data.getExtras().getInt("code"),
						Toast.LENGTH_LONG).show();
			}
		}
	}

}
