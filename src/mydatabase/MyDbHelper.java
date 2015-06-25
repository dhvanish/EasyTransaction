package mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper{

	public final static String Db_Name="Transaction.sqlite";
	//tables 
	public final static String Table_People="People";
	public final static String Table_Transaction="Trans";
	
	//People table column names
	public final static String Col_Id="Id";
	public final static String Col_Name="Name";
	
	//Transaction table column name
	public final static String Col_Id_Tran="Id";
	public final static String Col_Amt="Amount";
	public final static String Col_Date="Date";
	public final static String Col_Desc="Description";
	public final static String Col_UserId="UserId";
	
	public final static int Db_Version=1;
	
	public static final String Create_Table="create table "+Table_People+"("
												+Col_Id+" integer primary key,"
												+Col_Name+" text)";
	
	public static final String Create_Table_2="create table "+Table_Transaction+"("
			+Col_Id_Tran+" integer primary key,"
			+Col_UserId+" integer,"
			+Col_Amt+" real,"
			+Col_Date+" text,"
			+Col_Desc+" text)";

	public MyDbHelper(Context context) {
		super(context, Db_Name, null, Db_Version);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
db.execSQL(Create_Table);
db.execSQL(Create_Table_2);

	}

	public boolean insertDetails(int id,String name)
	{
		SQLiteDatabase db=getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Col_Id,id);
		cv.put(Col_Name,name);
		return db.insert(Table_People, null, cv)>0;
		 
	}
	public Object[] getDetails(int no)
	{
		SQLiteDatabase db=getReadableDatabase();
		String[] columns=new String[2];
		columns[0]=Col_Id;
		columns[1]=Col_Name;
		Object ob[]=new Object[no*2];
		Cursor cursor=db.query(Table_People, columns, null, null, null, null, null);
		for(int i=0;cursor.moveToNext();i+=2)
		{
			ob[i]=cursor.getInt(0);
			ob[i+1]=cursor.getString(1);	
		}
		return ob;
	}
	
	public boolean insertDetails2(int id,int userid, float amt, String date,String desc)
	{
		
		SQLiteDatabase db=getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Col_Id_Tran,id);
		cv.put(Col_UserId,userid);
		cv.put(Col_Amt,amt);
		cv.put(Col_Date,date);
		cv.put(Col_Desc, desc);
		
		return db.insert(Table_Transaction, null, cv)>0;
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
