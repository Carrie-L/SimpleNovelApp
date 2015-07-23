package com.libo.novelapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
public class MainActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
                /* 新建一个Intent对象 */
                Intent intent = new Intent();
                /* 指定intent要启动的类 */
                intent.setClass(MainActivity.this, ListActivity.class);
                /* 启动一个新的Activity */
                startActivity(intent);
                /* 关闭当前的Activity */
                MainActivity.this.finish();
                
                
    
    }
}