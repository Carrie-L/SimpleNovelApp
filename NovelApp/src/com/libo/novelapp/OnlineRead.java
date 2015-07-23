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
		requestWindowFeature(Window.FEATURE_PROGRESS);// 让进度条显示在标题栏上
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.browser_style);

		mWebView1 = (WebView) findViewById(R.id.myWebView1);
		String strURI = getResources().getString(R.string.url);//指定网址
		// WebView显示网页内容
		mWebView1.loadUrl(strURI);
		// 设置WebViewClient
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
			// 设置网页加载的进度条
			public void onProgressChanged(WebView view, int newProgress) {
				OnlineRead.this.getWindow().setFeatureInt(
						Window.FEATURE_PROGRESS, newProgress * 100);
				super.onProgressChanged(view, newProgress);

			}

			// 设置应用程序的标题title
			public void onReceivedTitle(WebView view, String title) {
				OnlineRead.this.setTitle(title);
				super.onReceivedTitle(view, title);
			}
		});
		// 覆盖默认后退按钮的作用，替换成WebView里的查看历史页面
		mWebView1.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if ((keyCode == KeyEvent.KEYCODE_BACK)
							&& mWebView1.canGoBack()) {
						mWebView1.goBack();// 这里如果不想他一直返回上一页的话，可以直接关闭这个activity
						return true;
					}

				}
				return false;
			}
		});
	}

	// 返回书架
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