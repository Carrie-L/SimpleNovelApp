package com.libo.novelapp;

import com.libo.helper.BookDB;
import android.app.ListActivity;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;  
import android.database.Cursor;  
import android.net.Uri;  
import android.os.Bundle;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.Button;  
import android.widget.EditText;  
import android.widget.ListView;  
import android.widget.SimpleCursorAdapter;  
  
/**  
 * ͨ������ؼ����ڶ���Ϣ���ݿ��в����йؼ��ֵĶ���Ϣ  
 * ��Ҫ������Ϣ��Ȩ��<uses-permission android:name="android.permission.READ_SMS" />  
 * @author ZLQ  
 *  
 */  
public class ShelfSearch extends Activity {  
    Button bu;//��ѯ��ť  
    EditText et;//�����  
    ListView lv;//������ʾ��ѯ������б�  
    private BookDB book;
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_shelf_aearch);  
        bu = (Button) findViewById(R.id.button1);  
        et = (EditText) findViewById(R.id.editText1);  
        lv = (ListView) findViewById(R.id.listView1); 
       
        bu.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) {  

                String key = et.getText().toString(); 
            	SQLiteDatabase db = book.getWritableDatabase();
                String col[] = { "_id","bookname" };
        		Cursor cur = db.query("book", col, "bookname like ?", new String[] { "%" + key + "%" }, null, null, null);
                //SimpleCursorAdapter������  
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(ShelfSearch.this,  
                        android.R.layout.simple_list_item_2, cur, new String[] {  
                                "bookname" }, new int[] {  
                                android.R.id.text1});  
                lv.setAdapter(adapter);  
            }  
        });  
    }  
} 
