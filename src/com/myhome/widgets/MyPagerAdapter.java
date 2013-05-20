package com.myhome.widgets;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class MyPagerAdapter extends PagerAdapter{
	
	private List<View> viewList;
	//private Context context;
	
	public MyPagerAdapter(Context context,List<View> viewList){
		//this.context=context;
		this.viewList=viewList;
	}
	
	

	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager)container).removeView(viewList.get(position));
	}



	@Override
	public void finishUpdate(View container) {
		// TODO Auto-generated method stub
	}



	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		((ViewPager)container).addView(viewList.get(position), 0);
		return viewList.get(position);
	}



	@Override
	public void startUpdate(View container) {
		// TODO Auto-generated method stub
	}



	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return viewList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==(arg1);
	}
	

}
