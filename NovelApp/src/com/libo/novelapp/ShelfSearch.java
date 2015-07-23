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
 * 通过输入关键字在短信息数据库中查找有关键字的短消息  
 * 需要读短消息的权限<uses-permission android:name="android.permission.READ_SMS" />  
 * @author ZLQ  
 *  
 */  
public class ShelfSearch extends Activity {  
    Button bu;//查询按钮  
    EditText et;//输入框  
    ListView lv;//用于显示查询结果的列表  
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
                //SimpleCursorAdapter适配器  
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(ShelfSearch.this,  
                        android.R.layout.simple_list_item_2, cur, new String[] {  
                                "bookname" }, new int[] {  
                                android.R.id.text1});  
                lv.setAdapter(adapter);  
            }  
        });  
    }  
} 
