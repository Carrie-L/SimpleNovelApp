package com.libo.novelapp;  
  
import java.util.ArrayList;


import com.libo.date.FinalDate;
import com.libo.novelapp.BookDatabase;

import android.app.ListActivity;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;  
import android.os.Bundle;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.ArrayAdapter;
import android.widget.Button;  
import android.widget.EditText;  
import android.widget.ListAdapter;
import android.widget.ListView;  
import android.widget.SimpleCursorAdapter;  
  
/**  
 * 通过输入关键字在短信息数据库中查找有关键字的短消息  
 * 需要读短消息的权限<uses-permission android:name="android.permission.READ_SMS" />  
 * @author ZLQ  
 *  
 */  
public class ShelfAearch extends ListActivity {  
    Button bu;//查询按钮  
    EditText et;//输入框  
    ListView lv;//用于显示查询结果的列表  
    private BookDatabase book;
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_shelf_aearch);  
        bu = (Button) findViewById(R.id.button1);  
        et = (EditText) findViewById(R.id.editText1);  
        lv = (ListView) findViewById(R.id.listView1);  
        bu.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) {  
            	SQLiteDatabase db = book.getWritableDatabase();
                String key = et.getText().toString();  
                String col[] = { "path" };
        		Cursor cur = db.query(FinalDate.DATABASE_TABLE, col, "path like ?", new String[] { "%" + key + "%" }, null, null, null);
        		ArrayList<String> arraylist = new ArrayList<String>();
        		


        		while (cur.moveToNext()) {
        			String s = cur.getString(cur.getColumnIndex("path"));
        			arraylist.add(s);
        		} 
        		db.close();
        		cur.close();
            }  
            
        });  
    }  
    public void setListAdapter(ListAdapter adapter) {
    	        
    	             
    	            
    	         }
} 