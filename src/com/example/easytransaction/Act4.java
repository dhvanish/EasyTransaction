package com.example.easytransaction;

import java.util.Calendar;

import mydatabase.MyDbHelper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Act4 extends Activity implements OnClickListener{

	int year,month,day,noofppl;
	Button dp[];
	int ids[];
	String names[];
	EditText et[],desc[];
	float amtshare[],finalamtshare[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act4);
		
		TableLayout tbl=(TableLayout)findViewById(R.id.tableLayout1);
		TableRow tr[];
		TextView tv[];
		
		Calendar cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		
		Intent in=getIntent();
		names=in.getStringArrayExtra("checkedvaluenames");
		ids=in.getIntArrayExtra("checkedvalueids");
		noofppl=names.length;
		tr=new TableRow[names.length];
		tv=new TextView[names.length];
		et=new EditText[names.length];
		dp=new Button[names.length];
		desc=new EditText[names.length];
		
	
		View v=new View(this);
		for(int i=0;i<names.length;i++)
		{
			/*v.setMinimumHeight(2);
			v.setBackgroundColor(Color.RED);
			tbl.addView(v);*/
			tr[i]=new TableRow(this);
			
			tv[i]=new TextView(this);
			tv[i].setGravity(Gravity.CENTER);
			tv[i].setBackgroundResource(R.drawable.cell_shape);
			tv[i].setText(names[i]);
			tv[i].setTextAppearance(this, R.id.textView2);
			
			et[i]=new EditText(this);
			et[i].setBackgroundResource(R.drawable.cell_shape);
			
			dp[i]=new Button(this);
			dp[i].setOnClickListener(this);
			dp[i].setBackgroundResource(R.drawable.cell_shape);
			
			//dp[i].setId(i);
			
			desc[i]=new EditText(this);
			desc[i].setBackgroundResource(R.drawable.cell_shape);
			
			tr[i].addView(tv[i]);
			tr[i].addView(et[i]);
			tr[i].addView(dp[i]);
			tr[i].addView(desc[i]);
			
			tbl.addView(tr[i]);
		}
	Button b=new Button(this);
	b.setId(11);
	b.setText("Calculate and store");
	b.setOnClickListener(this);
	tbl.addView(b);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act4, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		if(v.getId()==11)
		{
			MyDbHelper db=new MyDbHelper(getApplicationContext());
			amtshare=new float[noofppl];
			finalamtshare=new float[noofppl];
			
			for(int i=0;i<noofppl;i++)
			{	db.insertDetails2(i+1,ids[i],Float.parseFloat(et[i].getText()+""),dp[i].getText()+"",desc[i].getText()+"");
			
			amtshare[i]=Float.parseFloat(et[i].getText()+"");
			}
			Toast.makeText(getApplicationContext(), "Stored successfully!!", Toast.LENGTH_LONG).show();
			
			for(int i=0;i<noofppl;i++)
			{
				for(int j=0;j<noofppl;j++)
				{
					if(i!=j)
					finalamtshare[i]+=(amtshare[j]/noofppl);
				}
				finalamtshare[i]-=(amtshare[i]/noofppl);
			}
			Intent in=new Intent(getApplicationContext(),Act5.class);
			in.putExtra("names", names);
			in.putExtra("Amount", finalamtshare);
			startActivity(in);
		}
		else
		showDialog(0);
	}
	@Override
	 @Deprecated
	 protected Dialog onCreateDialog(int id) {
	  return new DatePickerDialog(this, datePickerListener, year, month, day);
	 }

	 private DatePickerDialog.OnDateSetListener datePickerListener = 
			 new DatePickerDialog.OnDateSetListener() 
	 { 
		 public void onDateSet(DatePicker view, int selectedYear,
	     int selectedMonth, int selectedDay) 
		 {
	     dp[0].setText(selectedDay + " / " + (selectedMonth + 1) + " / "
	     + selectedYear);
	     
	     }
	 };
}
