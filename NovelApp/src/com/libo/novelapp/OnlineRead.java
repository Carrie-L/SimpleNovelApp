package com.libo.novelapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.graphics.Bitmap;

public class OnlineRead extends Activity {

	ImageButton mImageButton1;
	EditText mEditText1;
	WebView mWebView1;
	private ProgressBar progressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);// �ý�������ʾ�ڱ�������
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.browser_style);

		mWebView1 = (WebView) findViewById(R.id.myWebView1);
		String strURI = getResources().getString(R.string.url);//ָ����ַ
		// WebView��ʾ��ҳ����
		mWebView1.loadUrl(strURI);
		// ����WebViewClient
		mWebView1.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}

			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}
		});

		mWebView1.setWebChromeClient(new WebChromeClient() {
			// ������ҳ���صĽ�����
			public void onProgressChanged(WebView view, int newProgress) {
				OnlineRead.this.getWindow().setFeatureInt(
						Window.FEATURE_PROGRESS, newProgress * 100);
				super.onProgressChanged(view, newProgress);

			}

			// ����Ӧ�ó���ı���title
			public void onReceivedTitle(WebView view, String title) {
				OnlineRead.this.setTitle(title);
				super.onReceivedTitle(view, title);
			}
		});
		// ����Ĭ�Ϻ��˰�ť�����ã��滻��WebView��Ĳ鿴��ʷҳ��
		mWebView1.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if ((keyCode == KeyEvent.KEYCODE_BACK)
							&& mWebView1.canGoBack()) {
						mWebView1.goBack();// �������������һֱ������һҳ�Ļ�������ֱ�ӹر����activity
						return true;
					}

				}
				return false;
			}
		});
	}

	// �������
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			Intent intent = new Intent();
			intent.setClass(OnlineRead.this, ListActivity.class);
			startActivity(intent);
			finish();
		}
		return true;
	}
}