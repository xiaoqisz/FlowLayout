package org.itheima15.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  FlowLayout 
 *  @包名：    org.itheima15.library
 *  @文件名:   FlowLayout
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/26 11:13
 *  @描述：    TODO
 */
public class FlowLayout
        extends ViewGroup
{

    private List<Line> mLines = new ArrayList<>();//布局管理的行

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //TODO:
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //TODO:
    }


    private class Line {
        //用来记录每行中的控件和相关的属性
        //1.属性:
        //2.方法
        //3.构造

        //用来记录行中的子控件的
        private List<View> mViews = new ArrayList<>();

        private int mLineUsedWidth;//行已经使用的宽度
        private int mLineMaxWidth;//行最大的宽度
        private int mLineHeight;//行的高度
        private int mSpace;//控件间的间隙

        public Line(int maxWidth, int space) {
            this.mLineMaxWidth = maxWidth;
            this.mSpace = space;
        }

        /**
         * 用来判断是否可以添加孩子
         * @param view
         * @return
         */
        public boolean canAdd(View view) {

            //如果一个孩子都没有时候，都可以加入
            if (mViews.size() == 0) {
                return true;
            }

            int childWidth = view.getMeasuredWidth();

            if (mLineUsedWidth + mSpace + childWidth <= mLineMaxWidth) {
                //可以添加
                return true;
            }
            return false;
        }


        /**
         * 添加子控件,调用addView方法前，先校验是否可以添加当前line中,通过canAdd
         * @param view
         */
        public void addChild(View view) {

            int childWidth  = view.getMeasuredWidth();
            int childHeight = view.getMeasuredHeight();

            if (mViews.size() == 0) {
                //如果一个孩子都没有
                //计算宽
                mLineUsedWidth = childWidth;

                //计算行的高度
                mLineHeight = childHeight;
            } else {
                //多个孩子时
                //计算宽
                mLineUsedWidth += childWidth + mSpace;

                //计算行的高度(就大不就小)
                mLineHeight = mLineHeight > childHeight
                              ? mLineHeight
                              : childHeight;
            }

            //记录views
            mViews.add(view);
        }

    }

}
