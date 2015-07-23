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

	private int mScreenWidth = 720; // ��Ļ��
	private int mScreenHeight = 1280; // ��Ļ��

	private Path mPath0;
	Bitmap mCurPageBitmap = null; // ��ǰҳ
	Bitmap mNextPageBitmap = null;
	PointF mTouch = new PointF(); // ��ק��
	boolean mIsRTandLB; // �Ƿ�������������
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
		mTouch.x = 0.01f; // ����x,yΪ0,�����ڵ����ʱ��������
		mTouch.y = 0.01f;
		mMiddleX = mScreenWidth / 2;
		mMiddleY = mScreenHeight / 2;
	}

	/**
	 * ������Ӧ�Ľ�,�ж�����߻����ұ�
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

	// Touch�¼�
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
		// dx ˮƽ���򻬶��ľ��룬��ֵ��ʹ�����������
		// dy ��ֱ���򻬶��ľ��룬��ֵ��ʹ�������Ϲ���
		if (mTouch.x > 0) {
			dx = -(int) (mScreenWidth + mTouch.x);
		} else {
			dx = (int) (mScreenWidth - mTouch.x + mScreenWidth);
		}
		if (mTouch.y > 0) {
			dy = (int) (mScreenHeight - mTouch.y);
		} else {
			dy = (int) (1 - mTouch.y); // ��ֹmTouch.y���ձ�Ϊ0
		}
	}

	/**
	 * �Ƿ����߷����ұ�
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
