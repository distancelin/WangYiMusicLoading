package com.distancelin.wangyimusicloading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by distancelin on 2017/6/18.
 */

public class WymLoading extends View{
    private final int DEFAULT_WIDTH=50;
    private final int DEFAULT_HEIGHT=30;
    private Context mContext;
    private int DP_DEFAULT_HEIGHT;
    private int DP_DEFAULT_WIDTH;
    private int RECT_HEIGHT;
    private int RECT_WIDTH;
    private Paint mPaint;
    private ValueAnimator mValueAnimator1;
    private ValueAnimator mValueAnimator2;
    private ValueAnimator mValueAnimator3;
    private ValueAnimator mValueAnimator4;
    private int mValue1;
    private int mValus2;
    private int mValue3;
    private int mValue4;
    private int mRectOffest;
    public WymLoading(Context context) {
        this(context,null);
    }

    public WymLoading(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mContext=context;
        DP_DEFAULT_HEIGHT=dp2px(DEFAULT_HEIGHT);
        DP_DEFAULT_WIDTH=dp2px(DEFAULT_WIDTH);
    }
    private int dp2px(int defaultWidth) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (defaultWidth * scale + 0.5f);
    }

    private void initValueAnimators() {
        mValueAnimator1 = ValueAnimator.ofInt(RECT_HEIGHT/3,RECT_HEIGHT*3/4);
        mValueAnimator1.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mValue1= (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator2 =ValueAnimator.ofInt(RECT_HEIGHT/5 ,RECT_HEIGHT*3/4);
        mValueAnimator2.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator2.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mValus2= (int) animation.getAnimatedValue();
            }
        });
        mValueAnimator3 =ValueAnimator.ofInt(RECT_HEIGHT/4,RECT_HEIGHT*4/5);
        mValueAnimator3.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator3.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mValue3= (int) animation.getAnimatedValue();
            }
        });
        mValueAnimator4 =ValueAnimator.ofInt(RECT_HEIGHT/3,RECT_HEIGHT*3/4);
        mValueAnimator4.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator4.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mValue4= (int) animation.getAnimatedValue();
            }
        });
        mValueAnimator1.setDuration(450).start();
        mValueAnimator2.setDuration(320).start();
        mValueAnimator3.setDuration(280).start();
        mValueAnimator4.setDuration(400).start();
    }

    public WymLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
        initValueAnimators();
    }

    private int measureWidth(int widthMeasureSpec) {
        int measuredWidth=0;
        int mode,size;
        mode=MeasureSpec.getMode(widthMeasureSpec);
        size=MeasureSpec.getSize(widthMeasureSpec);
        switch (mode){
            case MeasureSpec.EXACTLY:
                measuredWidth=size;
                break;
            case MeasureSpec.AT_MOST:
            case  MeasureSpec.UNSPECIFIED:
                measuredWidth=DP_DEFAULT_WIDTH;
        }
        return measuredWidth;
    }

    private int measureHeight(int heightMeasureSpec) {
        int measuredHeight=0;
        int mode,size;
        mode=MeasureSpec.getMode(heightMeasureSpec);
        size=MeasureSpec.getSize(heightMeasureSpec);
        switch (mode){
            case MeasureSpec.EXACTLY:
                measuredHeight=size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                measuredHeight=DP_DEFAULT_HEIGHT;
        }
        RECT_HEIGHT=measuredHeight;
        //矩形条的宽度为高度的十二分之一
        RECT_WIDTH=RECT_HEIGHT/12;
        //矩形条之间的距离为宽度的两倍
        mRectOffest=RECT_WIDTH*2;
        return measuredHeight;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int width=getMeasuredWidth();
        mPaint.setColor(Color.RED);
        int startLeft,endBottom,endRight;
        startLeft=(width-(3*mRectOffest+4*RECT_WIDTH))/2;
        endRight=startLeft+RECT_WIDTH;
        endBottom=RECT_HEIGHT;
        canvas.drawRect(startLeft,mValue1,endRight,endBottom,mPaint);
        canvas.drawRect(startLeft+mRectOffest+RECT_WIDTH,mValus2,endRight+mRectOffest+RECT_WIDTH,endBottom,mPaint);
        canvas.drawRect(startLeft+(mRectOffest+RECT_WIDTH)*2,mValue3,endRight+(mRectOffest+RECT_WIDTH)*2,endBottom,mPaint);
        canvas.drawRect(startLeft+(mRectOffest+RECT_WIDTH)*3,mValue4,endRight+(mRectOffest+RECT_WIDTH)*3,endBottom,mPaint);
    }
}
