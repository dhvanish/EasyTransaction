package com.example.easytransaction;

import mydatabase.MyDbHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act3 extends Activity {

	int noofppl;
	CheckBox cbname[];
	MyDbHelper db;
	int cbid[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act3);
		
		LinearLayout l=(LinearLayout)findViewById(R.id.l2);
		TextView t1=(TextView)findViewById(R.id.textView2);
		Intent in=getIntent();
		noofppl=in.getIntExtra("value",0);
		db=new MyDbHelper(this);
		Object[] ob=db.getDetails(noofppl);
		cbname=new CheckBox[noofppl];
		cbid=new int[noofppl];
		for(int i=0,j=0;i<(noofppl*2);i+=2,j++)
		{
		cbname[j]=new CheckBox(this);
		cbid[j]=(Integer)ob[i];
		cbname[j].setText((String)ob[i+1]);
		l.addView(cbname[j]);
		}
		
		Button b1=new Button(this);
		b1.setText("Next-->");
		l.addView(b1);
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int check=0;
				for(int i=0;i<noofppl;i++)
				{
					if(cbname[i].isChecked())
						check++;
				}
				String sendnames[]=new String[check];
				int sendids[]=new int[check];
				for(int i=0,j=0;i<noofppl;i++)
				{
					if(cbname[i].isChecked())
					{
						sendnames[j]=cbname[i].getText()+"";
						sendids[j]=cbid[i];
						j++;
					}	
				}
				// TODO Auto-generated method stub
				Intent in=new Intent(getApplicationContext(),Act4.class);
				in.putExtra("checkedvaluenames", sendnames);
				in.putExtra("checkedvalueids",cbid);
				startActivity(in);
			}
		});
		
		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act3, menu);
		return true;
	}

}
