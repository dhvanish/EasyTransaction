package com.example.easytransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText noofppl;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noofppl=(EditText)findViewById(R.id.editText1);
        Button ok=(Button)findViewById(R.id.button1);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void gotoact2(View v)
    {
    	Intent in=new Intent(this,Act2.class);
    	in.putExtra("number",noofppl.getText()+"");
    	startActivity(in);
    }
}
