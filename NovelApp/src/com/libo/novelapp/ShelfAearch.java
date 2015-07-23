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
 * ͨ������ؼ����ڶ���Ϣ���ݿ��в����йؼ��ֵĶ���Ϣ  
 * ��Ҫ������Ϣ��Ȩ��<uses-permission android:name="android.permission.READ_SMS" />  
 * @author ZLQ  
 *  
 */  
public class ShelfAearch extends ListActivity {  
    Button bu;//��ѯ��ť  
    EditText et;//�����  
    ListView lv;//������ʾ��ѯ������б�  
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