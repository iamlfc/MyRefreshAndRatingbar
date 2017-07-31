package com.ruanmeng.myrefreshandratingbar.refresh.myhead;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruanmeng.myrefreshandratingbar.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.ProgressDrawable;
import com.scwang.smartrefresh.layout.internal.pathview.PathsView;

public class BaseHeader extends LinearLayout implements RefreshHeader {

    private TextView mHeaderText;//标题文本
    private PathsView mArrowView;//下拉箭头
    private ImageView mProgressView;//刷新动画视图
    private ProgressDrawable mProgressDrawable;//刷新动画


    AnimationDrawable spinner = null;
    private ImageView imgAnimal;

    public BaseHeader(Context context) {
        super(context);
        setGravity(Gravity.CENTER_HORIZONTAL);
      /*  mHeaderText = new TextView(context);
        mProgressDrawable = new ProgressDrawable();
        mArrowView = new PathsView(context);
        mProgressView = new ImageView(context);
        mProgressView.setImageDrawable(mProgressDrawable);
        addView(mHeaderText, lpHeaderText);
        addView(mProgressView);
        addView(mArrowView, lpProgress);*/

        View view = inflate(context, R.layout.lay_beatyhead, null);
        imgAnimal = (ImageView) view.findViewById(R.id.img_animal);
        // 获取ImageView上的动画背景
        spinner = (AnimationDrawable) imgAnimal.getBackground();
        addView(view);
    }

    @NonNull
    public View getView() {
        return this;//真实的视图就是自己，不能返回null
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int headHeight, int extendHeight) {
        spinner.start();//开始动画
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        spinner.stop();//停止动画
       /* if (success) {
            mHeaderText.setText("刷新完成");
        } else {
            mHeaderText.setText("刷新失败");
        }*/
        return 500;//延迟500毫秒之后再弹回
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
       /* switch (newState) {
            case None:
            case PullDownToRefresh:
                mHeaderText.setText("下拉开始刷新");
                mArrowView.setVisibility(VISIBLE);//显示下拉箭头
                mProgressView.setVisibility(GONE);//隐藏动画
                mArrowView.animate().rotation(0);//还原箭头方向
                break;
            case Refreshing:
                mHeaderText.setText("正在刷新");
                mProgressView.setVisibility(VISIBLE);//显示加载动画
                mArrowView.setVisibility(GONE);//隐藏箭头
                break;
            case ReleaseToRefresh:
                mHeaderText.setText("释放立即刷新");
                mArrowView.animate().rotation(180);//显示箭头改为朝上
                break;
        }*/
    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onPullingDown(float percent, int offset, int headHeight, int extendHeight) {
    }

    @Override
    public void onReleasing(float percent, int offset, int headHeight, int extendHeight) {
    }

    @Override
    public void setPrimaryColors(int... colors) {
    }
}