package com.ssl.example;

import com.example.sslminaandroid.R;
import com.ssl.example.mina.MyService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView mTextMessage;

	@Override
	protected void onPause() {
		super.onPause();
		Intent intent = new Intent(MainActivity.this, MyService.class);
		stopService(intent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextMessage = (TextView) findViewById(R.id.message);
		Intent intent = new Intent(MainActivity.this, MyService.class);
		startService(intent);
	}

}
