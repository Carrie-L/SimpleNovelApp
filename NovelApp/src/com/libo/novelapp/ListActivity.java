package com.libo.novelapp;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.libo.helper.BookDB;
import com.libo.helper.MarkHelper;


public class ListActivity extends Activity implements BaseActivity{
	private static final String TAG = "ListActivity";
	private Intent it;
	private ArrayList<HashMap<String, Object>> listItem = null;
	private BookDB localbook;
	private HashMap<String, Object> map = null;
	private Map<String, Integer[]> map2;// ��ű����Ƽ�Ŀ¼��С����ͼƬ����
	protected ProgressDialog mDialog = null;
	View menuView;
	ShlefAdapter adapter;
	private SharedPreferences sp;
	private GridView toolbarGrid;
	private MarkHelper markhelper;
	private SharedPreferences.Editor editor;
	private int post;
	private long exitTime = 0;
	
	private PopupWindow menuPop, titlePopupWindow;
	View menuPopView, titlePop;
	int screenWidth = 720;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		toolbarGrid=(GridView) findViewById(R.id.bookShelf);
		localbook = new BookDB(this,"book");
		getDate();
		sp=getSharedPreferences("book", Context.MODE_PRIVATE);
		
		toolbarGrid.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Log.i("hck", "longclick ");
				post=arg2;
	   new AlertDialogs(ListActivity.this,ListActivity.this)
	   .alertDialog("ȷ��ɾ����", "", "ɾ��", "ȡ��", "delete");
				return true;
			}
		});
		
		toolbarGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				try {
					
					// �޸����ݿ���ͼ�������Ķ�״̬Ϊ1
					String s = (String) listItem.get(arg2).get("path");
					SQLiteDatabase db = localbook.getWritableDatabase();

					File f = new File(s);
					if (f.length() == 0) {
						Toast.makeText(ListActivity.this, "���ļ�Ϊ���ļ�", Toast.LENGTH_SHORT).show();
						
					} else {
						ContentValues values = new ContentValues();
						values.put("type", 1);// keyΪ�ֶ�����valueΪֵ
						db.update("book", values, "path=?", new String[] { s });// �޸�״̬Ϊͼ�鱻�ѱ�����
						db.close();
						String path = (String) listItem.get(arg2).get("path");
						it = new Intent();
						it.setClass(ListActivity.this, Read.class);
						it.putExtra("path", path);
						startActivity(it);
					}
				} catch (SQLException e) {
					Log.e("hck", "list.setOnItemClickListener-> SQLException error", e);
				} catch (Exception e) {
					Log.e("hck", "list.setOnItemClickListener Exception", e);
				}
			}				
		});
	}
	
	
	public void onlineRead(View view)
	{
		Intent intent = new Intent();
		intent.setClass(ListActivity.this,OnlineRead.class);
		startActivity(intent);
		this.finish();
	}
	//����
	public void addText(View view)
	{
		Intent intent = new Intent();
		intent.setClass(ListActivity.this, ImportFiles.class);
		startActivity(intent);
		this.finish();
	}
	
	
	/**
	 * ��ȡSD����Ŀ¼
	 * 
	 * @return
	 */
	public String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); // �ж�sd���Ƿ����
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// ��ȡ��Ŀ¼
		}
		return sdDir.toString();
	}

	/**
	 * �����������
	 */
	public void getDate() {
		SQLiteDatabase db = localbook.getReadableDatabase();
		String col[] = { "path" };
		Cursor cur = db.query("book", col, "type=1", null, null, null, null);
		Cursor cur1 = db.query("book", col, "type=2", null, null, null, null);
		Integer num = cur.getCount();
		Integer num1 = cur1.getCount();
		Log.i("lb", "listActivity :"+num);
		Log.i("lb", "listActivity :"+num1);
		ArrayList<String> arraylist = new ArrayList<String>();
		while (cur1.moveToNext()) {
			String s = cur1.getString(cur1.getColumnIndex("path"));
			arraylist.add(s);
		}
		while (cur.moveToNext()) {
			String s = cur.getString(cur.getColumnIndex("path"));
			arraylist.add(s);
		}
		db.close();
		cur.close();
		cur1.close();
		if (listItem == null)
			listItem = new ArrayList<HashMap<String, Object>>();
		listItem.clear();
		
	
		Map<String, String[]> maps = new HashMap<String, String[]>();
		
		for (int i = 0; i < num ; i++) {
			if (i < num1) {
				File file1 = new File(arraylist.get(i));
				String m = file1.getName().substring(0, file1.getName().length() - 4);
				
					m = m.substring(0, m.length());
				
				String id = arraylist.get(i).substring(arraylist.get(i).lastIndexOf("/") + 1);
				String[] array = maps.get(id);
				String name = array[0] == null ? m : array[0];
				map = new HashMap<String, Object>();
		
				map.put("BookName", name == null ? m : name);
				map.put("path", file1.getPath());
				map.put("com", 0 + file1.getName());// ������������
				listItem.add(map);
				Log.i("LB","����"+ listItem.size()+"size");
			} else {
				map = new HashMap<String, Object>();

				File file1 = new File(arraylist.get(i));
				String m = file1.getName().substring(0, file1.getName().length() - 4);
				if (m.length() > 8) {
					m = m.substring(0,
							m.length());
				}
				
				map.put("BookName", m);
			    map.put("id", i);
				map.put("path", file1.getPath());
				map.put("com", "1");
				listItem.add(map);
				
			}
		}
		
	
			adapter = new ShlefAdapter();
			toolbarGrid.setAdapter(adapter);
	
	}



	
	 class ShlefAdapter extends BaseAdapter{

			@Override
			public int getCount() {
				return listItem.size();
			}

			@Override
			public Object getItem(int arg0) {
				return arg0;
			}

			@Override
			public long getItemId(int arg0) {
				return arg0;
			}

			@Override
			public View getView(int position, View contentView, ViewGroup arg2) {
				
				contentView=LayoutInflater.from(getApplicationContext()).inflate(R.layout.item1, null);
				TextView view=(TextView) contentView.findViewById(R.id.imageView1);
                view.setText(listItem.get(position).get("BookName").toString());
       			return contentView;
			}
	    	
	    }
	

	@Override
	protected void onDestroy() {

		super.onDestroy();	
		
		MyApplication.bookDB=null;
		finish();
		System.gc();
	}

	
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	public void init() {
		
	}
	@Override
	public void refresh(Object... params) {
		
	}
	@Override
	public void server() {
		deleteFile(post);
	}
	
	//ɾ�����ݿ��е���ǩ
	private void deleteFile(int post)
	{
		HashMap<String, Object> imap = listItem.get(post);
		String path0 = (String) imap.get("path");
		String _id = (String) imap.get("_id");
		SQLiteDatabase db = localbook.getWritableDatabase();
		try {
			ContentValues values = new ContentValues();
			values.put("id", 1);// keyΪ�ֶ�����valueΪֵ
			values.put("type", 0);
			
			
			db.delete("book", "path='" + path0 + "'", null);
			db.update("book", values, "path=? and type=1", new String[] { path0 });// �޸�״̬Ϊͼ�鱻�ѱ�����
			// ��նԱ���ļ�¼
			editor = sp.edit();
			editor.remove(path0 + "jumpPage");
			editor.remove(path0 + "count");
			editor.remove(path0 + "begin");
			editor.commit();
			markhelper = new MarkHelper(this);
			// ɾ�����ݿ���ǩ��¼
			SQLiteDatabase db2 = markhelper.getWritableDatabase();
			db2.delete("markhelper", "path='" + path0 + "'", null);
			db2.delete("markhelper", "_id='" + _id + "'", null);
			db2.close();
		} catch (SQLException e) {
			Log.e(TAG, "onContextItemSelected-> SQLException error", e);
		} catch (Exception e) {
			Log.e(TAG, "onContextItemSelected-> Exception error", e);
		}
		db.close();
		// ��������ҳ��
		getDate();
	}
	
	// �����ҳ��Ļ���ͼ�� ����popwindow

		public void menuPop(View v) {
			
			menuPopView = getLayoutInflater().inflate(R.layout.menupop,
					null);

			menuPop = new PopupWindow(menuPopView, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			// popwindow��ʧ
			menuPop.setBackgroundDrawable(new BitmapDrawable());
			menuPop.showAtLocation(v, Gravity.LEFT, 0, screenWidth * 45 / 320);

			menuPop.setFocusable(true);
			menuPop.setOutsideTouchable(true);
			menuPop.update();

		}



		// �˵��в�popwindow�а�ť����
		public void Booklogin(View v) {
			titlePopupWindow.dismiss();
			
		}
	
		public void shelfSearch(View v){
			menuPop.dismiss();
			Intent in = new Intent(ListActivity.this, ShelfSearch.class);
			 startActivity(in);
		}
		public void changeStyle(View v){
			menuPop.dismiss();
			Intent in = new Intent(ListActivity.this, AboutActivity.class);//����
	        startActivity(in);
		}
		// popwindow�Ĺ��ڰ�ť��ת
		public void onAbout(View v) {
			menuPop.dismiss();
			Intent in = new Intent(ListActivity.this,ImportFiles.class);//����
	        startActivity(in);
		}
        public void feedback(View v){
			
		}
        public void disappear(View v) {
    		menuPop.dismiss();
    	}
        
        
	//�˳�����
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	            finish();
	            StartActivity.start.finish();
	            System.exit(0);
	        }
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
	}

}