package com.libo.novelapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
public class MainActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
                /* �½�һ��Intent���� */
                Intent intent = new Intent();
                /* ָ��intentҪ�������� */
                intent.setClass(MainActivity.this, ListActivity.class);
                /* ����һ���µ�Activity */
                startActivity(intent);
                /* �رյ�ǰ��Activity */
                MainActivity.this.finish();
                
                
    
    }
}