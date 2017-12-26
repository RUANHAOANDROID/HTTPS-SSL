package com.ssl.example.mina;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by hao.ruan on 2017/12/21.
 */

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new MinaClient();
			}
		});
		thread.start();
	}
}
