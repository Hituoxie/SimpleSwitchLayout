package com.lostls.simpleswitchlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;

/**
 * @author li.zhen
 * @version
 * @類說明 自定义切换布局
 **/
public class SimpleSwitchLayout extends FrameLayout {
    @SuppressWarnings("unused")
    private static final String TAG = "SwitchViewLayout";

    private int mCurrentId;

    private SparseArray<View> mSwitchViews = new SparseArray<>();

    public SimpleSwitchLayout(Context context) {
        super(context);
    }

    public SimpleSwitchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleSwitchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SimpleSwitchLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    protected void initView() {
        for(int i = 0,len = getChildCount(); i< len ; i++){
            View child = getChildAt(i);
            mSwitchViews.put(child.getId(),child);
        }
        hideAllViews();
    }

    /**添加需要切换的子布局
     * @param id 与childView绑定的key(每个布局标识的int值不一致即可)，如果key存在，替换布局
     * @param childView 默认INVISIBLE
     */
    public void setView(int id, View childView){
        if(childView == null || childView.getParent() != null){
            throw new RuntimeException("childView can't be null,and must without parent!");
        }

        View view = mSwitchViews.get(id);
        if(view != null){
            removeView(view);
        }
        mSwitchViews.put(id,childView);

        addView(childView);
        childView.setVisibility(View.INVISIBLE);
    }

    /**切换显示的布局
     * @param id
     */
    public void switchView(int id){
        mCurrentId = id;
        int index  = mSwitchViews.indexOfKey(id);
        for(int i = 0,len = mSwitchViews.size() ; i< len ;i++){
            mSwitchViews.valueAt(i).setVisibility( index == i ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /**
     * 隐藏所有布局
     */
    public void hideAllViews(){
        for(int i = 0,len = mSwitchViews.size() ; i< len ;i++){
            mSwitchViews.valueAt(i).setVisibility(View.INVISIBLE);
        }
    }

    /**
     * @return 当前显示的状态
     */
    public int getCurrentId(){
        return mCurrentId;
    }

    /**
     * @return 当前显示的View
     */
    public View getCurrentView(){
        return mSwitchViews.get(mCurrentId);
    }

    public View getView(int id){
        return mSwitchViews.get(id);
    }

    /**是否包含布局
     * @param id
     * @return
     */
    public boolean containView(int id){
        return getView(id) != null;
    }

}
