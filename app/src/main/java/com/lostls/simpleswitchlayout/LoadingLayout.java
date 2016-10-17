package com.lostls.simpleswitchlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

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
	public final int LOADING = R.id.loading_pager_loading;
	/** 没有数据  */
	public final int NO_DATA = R.id.loading_pager_no_data;
	/** 加载成功  */
	public final int SUCCESS = R.id.loading_pager_success;
	/** 加载失败 */
	public final int ERROR = R.id.loading_pager_error;
	/** 没有网络 */
	public final int NOT_NET_WORK = R.id.loading_pager_not_net_work;

	public LoadingLayout(Context context) {
		super(context);
	}

	public LoadingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void initView() {
		super.initView();

		//设置默认布局
		if(getView(LOADING) == null){
			setLoadingView(View.inflate(getContext(), R.layout.loading_pager, null));
		}

		if(getView(NO_DATA) == null){
			setNoDataView(View.inflate(getContext(), R.layout.loading_empty_pager, null));
		}

		if(getView(ERROR) == null){
			setErrorView(View.inflate(getContext(), R.layout.loading_error_pager, null));
		}

		if(getView(NOT_NET_WORK) == null){
			setNotNetWorkView(View.inflate(getContext(), R.layout.loading_no_network_pager, null));
		}

		showLoadingView();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}

	public void setLoadingView(View view) {
		setView(LOADING,view);
	}

	public void setErrorView(View view) {
		setView(ERROR,view);
	}

	/**设置加载成功的页面
	 * @param view
	 */
	public void setSuccessView(View view) {
		setView(SUCCESS,view);
	}

	public void setNoDataView(View view) {
		setView(NO_DATA,view);
	}

	public void setNotNetWorkView(View view) {
		setView(NOT_NET_WORK,view);
	}

	public View getNoDataView() {
		return getView(NO_DATA);
	}

	public View getSuccessView() {
		return getView(SUCCESS);
	}

	public View getLoadingView() {
		return getView(LOADING);
	}

	public View getErrorView() {
		return getView(ERROR);
	}

	public View getNotNetWorkView() {
		return getView(NOT_NET_WORK);
	}

	public void showLoadingView(){
		switchView(LOADING);
	}

	public void showErrorView(){
		switchView(ERROR);
	}

	public void showNotNetWorkView(){
		switchView(NOT_NET_WORK);
	}

	public void showSuccessView(){
		if(getView(SUCCESS) == null){
			throw new RuntimeException("must call setSuccessView()!");
		}
		switchView(SUCCESS);
	}

	public void showNoDataView(){
		switchView(NO_DATA);
	}
}
