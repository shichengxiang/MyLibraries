package com.scx.mylibs.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.BatteryManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 项目名称：live-android-native
 * 类描述：
 * 创建人：shichengxiang
 * 创建时间：2019/4/29 16:49
 */
public class BatteryView extends View {
    private int mMargin = 2;    //电池内芯与边框的距离 5
    private int mBoder = 1;     //电池外框的宽带4
    private int mWidth = 22;    //总长 70
    private int mHeight = 12;   //总高 40
    private int mHeadWidth = 3;// 6
    private int mHeadHeight = 5; // 10

    private RectF mMainRect;
    private RectF mHeadRect;
    private float mRadius = 2f;   //圆角
    private float mPower;

    private boolean mIsCharging;    //是否在充电


    public BatteryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public BatteryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BatteryView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        mMargin= px2px(mMargin);
        mBoder= px2px(mBoder);
        mWidth= px2px(mWidth);
        mHeight= px2px(mHeight);
        mHeadWidth= px2px(mHeadWidth);
        mHeadHeight= px2px(mHeadHeight);
        mRadius= px2px((int) mRadius);
        mHeadRect = new RectF(0, (mHeight - mHeadHeight)/2, mHeadWidth, (mHeight + mHeadHeight)/2);

        float left = mHeadRect.width();
        float top = mBoder;
        float right = mWidth-mBoder;
        float bottom = mHeight-mBoder;
        mMainRect = new RectF(left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint1 = new Paint();

        //画电池头
        paint1.setStyle(Paint.Style.FILL);  //实心
        paint1.setColor(Color.WHITE);
        canvas.drawRect(mHeadRect, paint1);

        //画外框
        paint1.setStyle(Paint.Style.STROKE);    //设置空心矩形
        paint1.setStrokeWidth(mBoder);          //设置边框宽度
        paint1.setColor(Color.WHITE);
        canvas.drawRoundRect(mMainRect, mRadius, mRadius, paint1);

        //画电池芯
        Paint paint = new Paint();
        if (mIsCharging) {
            paint.setColor(Color.GREEN);
        } else {
            if (mPower < 0.1) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.WHITE);
            }
        }

        int width   = (int) (mPower * (mMainRect.width() - mMargin*2));
        int left    = (int) (mMainRect.right - mMargin - width);
        int right   = (int) (mMainRect.right - mMargin);
        int top     = (int) (mMainRect.top + mMargin);
        int bottom  = (int) (mMainRect.bottom - mMargin);
        Rect rect = new Rect(left,top,right, bottom);
        canvas.drawRect(rect, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth, mHeight);
    }

    private void setPower(float power) {
        mPower = power;
        invalidate();
    }

    private BroadcastReceiver mPowerConnectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            mIsCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            setPower(((float) level)/scale);
        }
    };

    @Override
    protected void onAttachedToWindow() {
        getContext().registerReceiver(mPowerConnectionReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        getContext().unregisterReceiver(mPowerConnectionReceiver);
        super.onDetachedFromWindow();
    }
    private int px2px(int px){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,getResources().getDisplayMetrics());
    }
}
