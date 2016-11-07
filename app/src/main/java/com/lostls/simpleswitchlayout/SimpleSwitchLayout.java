package com.lostls.simpleswitchlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
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

    /**绑定一组需要切换布局的id
     * @param id 已经添加的布局id
     */
    public void bindView(int ...id){
        for (int i : id){
            View view = findViewById(i);
            if(view != null){
                mSwitchViews.put(i,findViewById(i));
            }
        }
    }

    /**添加需要切换的子布局
     * @param id 与childView绑定的key(每个布局标识的int值不一致即可)
     * @param childView 默认GONE
     */
    public void addView(int id, View childView){
        if(childView == null){
            throw new RuntimeException("childView can't be null!");
        }

        mSwitchViews.put(id,childView);

        if(childView.getParent() == null){
            addView(childView);
        }

        childView.setVisibility(View.GONE);
    }

    public void addView(int id, int layoutId){
        addView(id, LayoutInflater.from(getContext()).inflate(layoutId,this,false));
    }

    /**切换显示的布局,绑定的其他布局都隐藏
     * @param id
     */
    public void showView(int id){
        mCurrentId = id;
        int index  = mSwitchViews.indexOfKey(id);
        for(int i = 0,len = mSwitchViews.size() ; i< len ;i++){
            mSwitchViews.valueAt(i).setVisibility( index == i ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 隐藏所有布局
     */
    public void hideAllViews(){
        for(int i = 0,len = mSwitchViews.size() ; i< len ;i++){
            mSwitchViews.valueAt(i).setVisibility(View.GONE);
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
