package com.lee.animationlist.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;

import com.lee.animationlist.R;

/**
 * Created by lixinxin on 2017/1/1.
 * 邮箱 895330766@qq.com
 */

public class ZoomListView extends ListView {

    private ImageView mImageView;    // 缩放的图片
    private int mImageViewHeigt;      //图片原本的高度

    public ZoomListView(Context context) {
        this(context, null);

    }

    public ZoomListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mImageViewHeigt = context.getResources().getDimensionPixelOffset(R.dimen.size_default_height);
    }

    public void setImageView(ImageView mImageView) {
        this.mImageView = mImageView;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        //下拉过度
        if (deltaY < 0) {
            mImageView.getLayoutParams().height = mImageView.getHeight() - deltaY;
            mImageView.requestLayout();
        } else {
            if (mImageView.getHeight() > mImageViewHeigt) {
                mImageView.getLayoutParams().height = mImageView.getHeight() - deltaY;
                mImageView.requestLayout();
            }

        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    //上升图片缩小
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        View parent = (View) mImageView.getParent();
        int deltaY = parent.getTop();
        if (deltaY < 0 && mImageView.getHeight() > mImageViewHeigt) {
            mImageView.getLayoutParams().height = mImageView.getHeight() + deltaY;
            parent.layout(parent.getLeft(), 0, parent.getRight(), parent.getHeight());
            mImageView.requestLayout();
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
//                mImageView.getLayoutParams().height = mImageViewHeigt;
//                mImageView.requestLayout();

                ResetAnimotion a = new ResetAnimotion(mImageView, mImageViewHeigt);
                a.setDuration(300);
                mImageView.startAnimation(a);
                break;
        }
        return super.onTouchEvent(ev);
    }


    class ResetAnimotion extends Animation {

        private final int extrHeight;
        private int targetHeight;// 原图高度
        private int originHeight; //拉伸后的高度

        public ResetAnimotion(ImageView iv, int targetHeight) {
            this.originHeight = iv.getHeight();
            this.targetHeight = targetHeight;
            this.extrHeight = originHeight - targetHeight;
        }


        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            mImageView.getLayoutParams().height = (int) (originHeight - (extrHeight * interpolatedTime));
            mImageView.requestLayout();
            super.applyTransformation(interpolatedTime, t);
        }
    }

}















