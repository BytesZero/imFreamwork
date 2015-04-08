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
