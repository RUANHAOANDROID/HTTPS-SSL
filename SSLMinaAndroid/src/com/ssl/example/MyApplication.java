package com.ssl.example;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by hao.ruan on 2017/12/21.
 */

public class MyApplication extends Application {
	private static Context context;
	private final Logger log = Logger.getLogger(MyApplication.class);

	@Override
	public void onCreate() {

		context = getApplicationContext();

		Level level = Level.DEBUG;
		String logFile = Environment.getExternalStorageDirectory() + File.separator + "androidmina.txt";
		LogConfigurator log = new LogConfigurator();
		log.setFileName(logFile);
		log.setRootLevel(level);
		log.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
		log.setMaxFileSize(1024 * 1024 * 5);
		log.setImmediateFlush(true);
		log.configure();

	}

	// 返回
	public static Context getContextObject() {
		return context;
	}
}
