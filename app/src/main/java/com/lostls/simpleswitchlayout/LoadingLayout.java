package com.lostls.simpleswitchlayout;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author li.zhen
 * @version
 * @類說明  加载数据的容器页面，有加载失败、成功、无数据等状态<br/>
 * 		    除了加载成功的页面需要设置，其他的有默认页面
 **/
public class LoadingLayout extends SimpleSwitchLayout {
	@SuppressWarnings("unused")
	private static final String TAG = "LoadingPagerLayout";
	/** 正在加载  */
	public static final int LOADING = R.id.loading_pager_loading;
	/** 没有数据  */
	public static final int NO_DATA = R.id.loading_pager_no_data;
	/** 加载成功  */
	public static final int SUCCESS = R.id.loading_pager_success;
	/** 加载失败 */
	public static final int ERROR = R.id.loading_pager_error;
	/** 没有网络 */
	public static final int NOT_NET_WORK = R.id.loading_pager_not_net_work;

	public LoadingLayout(Context context) {
		super(context);
	}

	public LoadingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initCommentView();
	}

	private void initCommentView() {
		bindView(LOADING,NO_DATA,SUCCESS,ERROR,NOT_NET_WORK);

		//设置默认布局
		if(getView(LOADING) == null){
			addView(LOADING,R.layout.loading_pager);
		}

		if(getView(NO_DATA) == null){
			addView(NO_DATA,R.layout.loading_empty_pager);
		}

		if(getView(ERROR) == null){
			addView(ERROR,R.layout.loading_error_pager);
		}

		if(getView(NOT_NET_WORK) == null){
			addView(NOT_NET_WORK,R.layout.loading_no_network_pager);
		}
	}

	public void showLoadingView(){
		showView(LOADING);
	}

	public void showNotNetWorkView(){
		showView(NOT_NET_WORK);
	}

	public void showErrorView(){
		showView(ERROR);
	}

	public void showSuccessView(){
		if(getView(SUCCESS) == null){
			throw new RuntimeException("must call setSuccessView()!");
		}
		showView(SUCCESS);
	}

	public void showNoDataView(){
		showView(NO_DATA);
	}
}

