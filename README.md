# imFreamwork
im的主界面框架


![](https://github.com/yy1300326388/imFreamwork/blob/master/image/device-2015-04-08-141210.png)

- xml布局

```java
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <android.support.v4.view.ViewPager
        android:id="@+id/main_viewpager"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:background="#FFFFFF" >
    </android.support.v4.view.ViewPager>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:orientation="horizontal" >
        
        <ImageView
            android:id="@+id/tab_img_message"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:src="@drawable/tab_message"
            android:padding="@dimen/tab_padding"/>
        <ImageView
            android:id="@+id/tab_img_contacts"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:src="@drawable/tab_contacts"
            android:padding="@dimen/tab_padding"/>
        <ImageView
            android:id="@+id/tab_img_detail"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:src="@drawable/tab_detail"
            android:padding="@dimen/tab_padding"/>
        
    </LinearLayout>

</LinearLayout>
```

> 主要布局就是ViewPager+LinearLayout，支持了左右滑动切换以及点击切换

- MainActivity

```java
package com.example.viewpager.main;


import java.util.ArrayList;
import java.util.List;
import com.example.viewpager.ContactActivity;
import com.example.viewpager.DetailActivity;
import com.example.viewpager.MessageActivity;
import com.example.viewpager.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements OnClickListener,OnPageChangeListener {
	//initView
	private ViewPager mViewPager;
	private ImageView tab_img_message,tab_img_contacts,tab_img_detail;
	//initData
	ImageView[] tabs;
	List<Fragment> fTabs = new ArrayList<Fragment>();
	private FragmentPagerAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}
	
	/**
	 * 初始化界面
	 */
	void initView(){
		mViewPager=(ViewPager) findViewById(R.id.main_viewpager);
		mViewPager.setOnPageChangeListener(this);
		initTab();
	};
	/**
	 * 初始化界面
	 */
	void initTab(){
		tab_img_message=(ImageView) findViewById(R.id.tab_img_message);
		tab_img_message.setOnClickListener(this);
		tab_img_contacts=(ImageView) findViewById(R.id.tab_img_contacts);
		tab_img_contacts.setOnClickListener(this);
		tab_img_detail=(ImageView) findViewById(R.id.tab_img_detail);
		tab_img_detail.setOnClickListener(this);
		tabs=new ImageView[3];
		tabs[0]=tab_img_message;
		tabs[1]=tab_img_contacts;
		tabs[2]=tab_img_detail;
		tabs[0].setSelected(true);
	}
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		// TODO Auto-generated method stub
		
		//设置三个界面
		fTabs.add(new MessageActivity());
		fTabs.add(new ContactActivity());
		fTabs.add(new DetailActivity());
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{

			@Override
			public int getCount()
			{
				return fTabs.size();
			}

			@Override
			public Fragment getItem(int position)
			{
				return fTabs.get(position);
			}
		};
		mViewPager.setAdapter(mAdapter);
		
	}

	//tab的点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int vid=v.getId();
		switch (vid) {
		case R.id.tab_img_message:
		{
			selectedTab(0);
			break;
		}
		case R.id.tab_img_contacts:
		{
			selectedTab(1);
			break;
		}
		case R.id.tab_img_detail:
		{
			selectedTab(2);
			break;
		}
		default:
			
			break;
		}
	}
	/**
	 * 选择了那一项
	 * @param select
	 */
	void selectedTab(int select){
		for (int i = 0; i < 3; i++) {
			if(i==select){
				tabs[i].setSelected(true);
				mViewPager.setCurrentItem(select, false);
			}else{
				tabs[i].setSelected(false);
			}
		}
	}
	
	//viewpager 界面改变
	@Override
	public void onPageScrollStateChanged(int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		//界面发生改变时选择当前页面
		selectedTab(position);
	}
	
	
	
}

```

> 这里一定要继承自FragmentActivity

- 子项Activity

```java
package com.example.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;
import android.widget.Toast;


public class MessageActivity extends Fragment {
	TextView tv_message;

	//创建view
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.activity_message, container, false);
	}
	
	//创建布局
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		tv_message=(TextView) getView().findViewById(R.id.message_tv_message);
		tv_message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "点击了message", Toast.LENGTH_SHORT).show();
			}
		});
	}

}

```
> 这里一定要继承自Fragment，并且原来onCreate(Bundle savedInstanceState)中的代码在onActivityCreated(Bundle savedInstanceState)中写，用法完全一样




