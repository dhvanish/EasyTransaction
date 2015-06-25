package com.example.easytransaction;

import mydatabase.MyDbHelper;
import android.app.Application;

public class MyConstApp extends Application {
	@Override
	public void onCreate(){super.onCreate();
	
	MyDbHelper dB=new MyDbHelper(this);
	if(this.getDatabasePath(dB.Db_Name).exists())
		this.deleteDatabase(dB.Db_Name);
	dB.getReadableDatabase();
	}
}
