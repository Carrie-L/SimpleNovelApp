package com.libo.novelapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PageWidget extends View {

	private int mScreenWidth = 720; // 屏幕宽
	private int mScreenHeight = 1280; // 屏幕高

	private Path mPath0;
	Bitmap mCurPageBitmap = null; // 当前页
	Bitmap mNextPageBitmap = null;
	PointF mTouch = new PointF(); // 拖拽点
	boolean mIsRTandLB; // 是否属于右上左下
	Paint mPaint;
	float mMiddleX;
	float mMiddleY;

	public PageWidget(Context context, int width, int height) {
		super(context);
		mPath0 = new Path();
		mScreenWidth = width;
		mScreenHeight = height;
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL);
		mTouch.x = 0.01f; // 不让x,y为0,否则在点计算时会有问题
		mTouch.y = 0.01f;
		mMiddleX = mScreenWidth / 2;
		mMiddleY = mScreenHeight / 2;
	}

	/**
	 * 计算点对应的脚,判断在左边还是右边
	 * 
	 * @param x
	 * @param y
	 */
	public void calcCornerXY(float x, float y) {
		Log.i("lb", "PageWidget x:" + x + "      y" + y);
		if (x <= mMiddleX / 2)
			mTouch.x = 0;
		else
			mTouch.x = mScreenWidth;
		if (y <= mMiddleY / 2)
			mTouch.y = 0;
		else
			mTouch.y = mScreenHeight;
		

		if ((mTouch.x == 0 && mTouch.y == mScreenHeight)
				|| (mTouch.x == mScreenWidth && mTouch.y == 0))
			mIsRTandLB = true;
		else
		
			mIsRTandLB = false;
	}

	// Touch事件
	public boolean doTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			mTouch.x = event.getX();
			mTouch.y = event.getY();
			this.postInvalidate();

		}
		if (event.getAction() == MotionEvent.ACTION_MOVE
				&& event.getMetaState() == 1) {
			mTouch.x = event.getX();
			mTouch.y = event.getY();
			this.postInvalidate();
			startAnimation(1200);
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			mTouch.x = event.getX();
			mTouch.y = event.getY();

		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			startAnimation(1200);
			this.postInvalidate();
		}
		return true;
	}

	private void drawCurrentPageArea(Canvas canvas, Bitmap bitmap, Path path) {
		mPath0.reset();
		mPath0.moveTo(mTouch.x, mTouch.y);
		mPath0.quadTo(mTouch.x, mTouch.y, mTouch.x, mTouch.y);
		mPath0.lineTo(mTouch.x, mTouch.y);
		mPath0.close();

		canvas.save();
		canvas.clipPath(path, Region.Op.XOR);
		canvas.drawBitmap(bitmap, 0, 0, null);
		try {
			canvas.restore();
		} catch (Exception e) {

		}

	}

	public void setBitmaps(Bitmap bm1, Bitmap bm2) {
		mCurPageBitmap = bm1;
		mNextPageBitmap = bm2;
	}

	public void setScreen(int w, int h) {
		mScreenWidth = w;
		mScreenHeight = h;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		drawCurrentPageArea(canvas, mCurPageBitmap, mPath0);

	}

	private void startAnimation(int delayMillis) {
		int dx, dy;
		// dx 水平方向滑动的距离，负值会使滚动向左滚动
		// dy 垂直方向滑动的距离，负值会使滚动向上滚动
		if (mTouch.x > 0) {
			dx = -(int) (mScreenWidth + mTouch.x);
		} else {
			dx = (int) (mScreenWidth - mTouch.x + mScreenWidth);
		}
		if (mTouch.y > 0) {
			dy = (int) (mScreenHeight - mTouch.y);
		} else {
			dy = (int) (1 - mTouch.y); // 防止mTouch.y最终变为0
		}
	}

	/**
	 * 是否从左边翻向右边
	 * 
	 * @return
	 */
	public boolean DragToRight() {
		if (mTouch.x ==0) 
			return true;
		
		return true;
	}

	public boolean right() {
		if (mTouch.x > -4)
			return false;
		return true;
	}

}
