package com.example.easytransaction;

import mydatabase.MyDbHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act2 extends Activity {

	EditText e[];
	//int noofppl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act2);
		Intent in=getIntent();
		final int noofppl=Integer.parseInt(in.getStringExtra("number"));
		e=new EditText[noofppl];
		LinearLayout l=(LinearLayout)findViewById(R.id.l1);
		TextView t1=(TextView)findViewById(R.id.textView1);
		
		for(int i=0;i<noofppl;i++)
		{
		e[i]=new EditText(this);
	//	e[i].setText("hii2");
		l.addView(e[i]);
		}
		
		Button b1=new Button(this);
		b1.setText("Submit and Proceed");
		l.addView(b1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MyDbHelper db=new MyDbHelper(getApplicationContext());
				for(int i=0;i<noofppl;i++)
				db.insertDetails(i+1, e[i].getText()+"");
				Intent in=new Intent(getApplicationContext(),Act3.class);
				in.putExtra("value", noofppl);
				startActivity(in);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act2, menu);
		return true;
	}

}
