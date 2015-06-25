package com.example.easytransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act5 extends Activity {

	String names[];
	float amtshare[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act5);
		
		LinearLayout l=(LinearLayout)findViewById(R.id.l3);
		Intent in=getIntent();
		names=in.getStringArrayExtra("names");
		amtshare=in.getFloatArrayExtra("Amount");
		TextView t[]=new TextView[names.length];
		for(int i=0;i<names.length;i++)
		{
			t[i]=new TextView(this);
			t[i].setText(names[i]+" : "+amtshare[i]);
			l.addView(t[i]);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act5, menu);
		return true;
	}

}
