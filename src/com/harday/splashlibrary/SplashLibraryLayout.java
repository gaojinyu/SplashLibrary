package com.harday.splashlibrary;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.haydar.splashlibrary.R;

/**
 * 欢迎界面（viewpager）
 * 
 * @author gjy
 * 
 */
public class SplashLibraryLayout extends RelativeLayout implements
		OnPageChangeListener {
	private List<View> mViewList; // view视图list
	private ViewPager mSplashLibraryViewPager;
	private List<ImageView> mImageViewList;

	public SplashLibraryLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.library_splash_layout,
				this);
		mSplashLibraryViewPager = (ViewPager) findViewById(R.id.library_splash_viewpager);
		mViewList = new ArrayList<View>();
		mViewList.add(LayoutInflater.from(context).inflate(
				R.layout.library_splash_zero, null));
		mViewList.add(LayoutInflater.from(context).inflate(
				R.layout.library_splash_one, null));
		mViewList.add(LayoutInflater.from(context).inflate(
				R.layout.library_splash_two, null));
		mViewList.add(LayoutInflater.from(context).inflate(
				R.layout.library_splash_three, null));
		mViewList.add(LayoutInflater.from(context).inflate(
				R.layout.library_splash_four, null));
		mImageViewList = new ArrayList<ImageView>();
		mImageViewList
				.add((ImageView) findViewById(R.id.library_splash_index_zero));
		mImageViewList
				.add((ImageView) findViewById(R.id.library_splash_index_one));
		mImageViewList
				.add((ImageView) findViewById(R.id.library_splash_index_two));
		mImageViewList
				.add((ImageView) findViewById(R.id.library_splash_index_three));
		mImageViewList
				.add((ImageView) findViewById(R.id.library_splash_index_four));
		mSplashLibraryViewPager.setAdapter(new ViewPagerAdater(mViewList));
		mSplashLibraryViewPager.setPageTransformer(true,
				new SplashPageTransformer());
		mSplashLibraryViewPager.setCurrentItem(0);
		mImageViewList.get(0).setImageResource(
				R.drawable.library_splash_white_dot);
		mSplashLibraryViewPager.setOnPageChangeListener(this);

		mSplashLibraryViewPager.setOnTouchListener(new OnTouchListener() {
			float touchX = 0;

			@SuppressWarnings("static-access")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					touchX = event.getX();
				}
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					if ((event.getX() - touchX) > 0.0f) {
						mSplashLibraryViewPager
								.setBackgroundColor(new Color().TRANSPARENT);
					} else {
						mSplashLibraryViewPager
								.setBackgroundColor(new Color().WHITE);
					}
				}
				return false;
			}
		});
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		mSplashLibraryViewPager.setCurrentItem(arg0);
		for (int i = 0; i < mImageViewList.size(); i++) {
			mImageViewList.get(i).setImageResource(
					R.drawable.library_splash_dark_dot);
			if (arg0 == i) {
				mImageViewList.get(i).setImageResource(
						R.drawable.library_splash_white_dot);
			}
		}
	}
}
