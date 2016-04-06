package com.pingplusplus.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void btnClick(View v){
    	Class clz = null;
    	switch (v.getId()) {
		case R.id.client_sdk:
			clz = ClientSDKActivity.class;
			break;
		case R.id.one_sdk:
			clz = OneSDKActivity.class;
			break;
		default:
			break;
		}
    	Intent intent = new Intent(MainActivity.this, clz);
    	startActivity(intent);
    }
}
