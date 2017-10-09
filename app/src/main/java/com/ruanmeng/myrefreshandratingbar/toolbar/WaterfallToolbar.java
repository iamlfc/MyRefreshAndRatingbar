package com.ruanmeng.myrefreshandratingbar.toolbar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.ruanmeng.myrefreshandratingbar.toolbar.annotation.Dp;
import com.ruanmeng.myrefreshandratingbar.toolbar.annotation.Percentage;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


/**
 * Created by Hugo Castelani
 * Date: 19/09/17
 * Time: 19:30
 */

public class WaterfallToolbar extends CardView {
    private RecyclerView mRecyclerView;
    private ScrollView mScrollView;
    private SmartRefreshLayout refreshLayout;


    private int mInitialElevation;
    private int mFinalElevation;
    private int mScrollFinalPosition;

    public static final int DEFAULT_INITIAL_ELEVATION = 1;
    public static final int DEFAULT_FINAL_ELEVATION = 6;
    public static final int DEFAULT_SCROLL_FINAL_ELEVATION = 6;

    public WaterfallToolbar(Context context) {
        super(context);
        init();
    }

    public WaterfallToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaterfallToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setRadius(0);    // same as cardCornerRadius

        setInitialElevation(DEFAULT_INITIAL_ELEVATION);
        setFinalElevation(DEFAULT_FINAL_ELEVATION);
        setScrollFinalPosition(DEFAULT_SCROLL_FINAL_ELEVATION);
    }

    /**
     * @param value The recycler view whose scroll is going to be listened
     * @return Own object
     */
    public WaterfallToolbar addRecyclerView(@NonNull final RecyclerView value) {
        mRecyclerView = value;
        addRecyclerViewScrollListener();
        return this;
    }

    public WaterfallToolbar addMyRefreshLayout(@NonNull final SmartRefreshLayout value) {
        refreshLayout = value;
        addMyRefreshScrollListener();
        return this;
    }


    /**
     * @param value The scroll view whose scroll is going to be listened
     * @return Own object
     */
    public WaterfallToolbar addScrollView(@NonNull final ScrollView value) {
        mScrollView = value;
        addScrollViewScrollListener();
        return this;
    }

    /**
     * @param value The elevation with which the toolbar starts
     * @return Own object
     */
    public WaterfallToolbar setInitialElevation(@Dp final int value) {
        setCardElevation(dp2px(value));
        mInitialElevation = dp2px(value);
        return this;
    }

    /**
     * @param value The elevation the toolbar gets when it reaches final scroll elevation
     * @return Own object
     */
    public WaterfallToolbar setFinalElevation(@Dp final int value) {
        mFinalElevation = dp2px(value);
        return this;
    }

    /**
     * @param value The percentage of the screen's height that is
     *              going to be scrolled to reach the final elevation
     * @return Own object
     */
    public WaterfallToolbar setScrollFinalPosition(@Percentage final int value) {
        final int screenHeight = getResources().getDisplayMetrics().heightPixels;

        mScrollFinalPosition = dp2px((int) (screenHeight * (value / 100.0)));
        return this;
    }

    // this represents current recycler/list/scroll view's position
    private int mPosition = 0;

    private void addRecyclerViewScrollListener() {
        if (mRecyclerView != null) {
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    mPosition += dy;
                    onScroll();
                }
            });
        } else {
            throw new NullPointerException("RecyclerView cannot be null.");
        }
    }

    private void addScrollViewScrollListener() {
        if (mScrollView != null) {
            mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    mPosition = mScrollView.getScrollY();
                    onScroll();
                }
            });
        } else {
            throw new NullPointerException("ScrollView cannot be null.");
        }
    }

    /**
     * 添加 smartrefreshlayout 的 滑动事件
     */
    private void addMyRefreshScrollListener() {
        if (refreshLayout != null) {
            refreshLayout.setOnScrollChangeListener(new OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                    mPosition += scrollY;
                    mPosition = refreshLayout.getScrollY();
                    Log.e("--lfc", " mPosition " + mPosition);
                    onScroll();
                }
            });
        }


    }

    private void onScroll() {
        // shadow shall not increase if current mPosition
        // is higher than scroll's final mPosition
        if (mPosition <= mScrollFinalPosition) {

            setCardElevation(calculateElevation());
            Log.e("--lfc", " 1 " + calculateElevation());

        } else {

            // thread below fixes issue #1, avoiding elevation
            // setting problems when fast scrolling
            final int mPositionBackup = mPosition;
            mPosition = mScrollFinalPosition;
            final int properElevation = calculateElevation();
            mPosition = mPositionBackup;

            if (getCardElevation() != properElevation) {
                setCardElevation(properElevation);
                Log.e("--lfc", " 2 " + properElevation);

            }
        }
    }

    private int calculateElevation() {
        // getting back to rule of three:
        // mFinalElevation (px) = mScrollFinalPosition (px)
        // newElevation    (px) = mPosition            (px)
        int newElevation = (mFinalElevation * mPosition) / mScrollFinalPosition;

        // avoid values under minimum value
        if (newElevation < mInitialElevation)
            newElevation = mInitialElevation;

        return newElevation;
    }

    /**
     * <pre>
     *     author: Blankj
     *     blog  : http://blankj.com
     *     time  : 2016/08/13
     *     desc  : 转换相关工具类
     * </pre>
     * <p>
     * Method got from:
     * https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/src/main/java/com/blankj/utilcode/util/ConvertUtils.java
     */
    private int dp2px(final int dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}