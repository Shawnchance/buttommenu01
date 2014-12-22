package com.example.buttommenu01;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button[] mTabs;

	private int index; // 用户点击Tab的index

	private int currentTabIndex; // 当前fragment的index

	private Fragment[] fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		mTabs = new Button[3];
		mTabs[0] = (Button) findViewById(R.id.btn_conversation);
		mTabs[1] = (Button) findViewById(R.id.btn_address_list);
		mTabs[2] = (Button) findViewById(R.id.btn_setting);
		// 把第一个tab设为选中状态
		mTabs[0].setSelected(true);

		Fragment fg1 = new TestFragment("会话");
		Fragment fg2 = new TestFragment("通讯录");
		Fragment fg3 = new TestFragment("设置");
		fragments = new Fragment[] { fg1, fg2, fg3 };

		// 添加显示第一个fragment
		getFragmentManager().beginTransaction()
				.add(R.id.fragment_container, fg1)
				.add(R.id.fragment_container, fg2)
				.add(R.id.fragment_container, fg3).hide(fg2).hide(fg3)
				.show(fg1).commit();
	}

	/**
	 * button点击事件
	 * 
	 * @param view
	 */
	public void onTabClicked(View view) {
		switch (view.getId()) {
		case R.id.btn_conversation:
			index = 0;
			break;
		case R.id.btn_address_list:
			index = 1;
			break;
		case R.id.btn_setting:
			index = 2;
			break;
		}
		if (currentTabIndex != index) {
			FragmentTransaction trx = getFragmentManager().beginTransaction();
			trx.hide(fragments[currentTabIndex]);
//			if (!fragments[index].isAdded()) {
//				trx.add(R.id.fragment_container, fragments[index]);
//			}
			trx.show(fragments[index]).commit();
		}
		mTabs[currentTabIndex].setSelected(false);
		// 把当前tab设为选中状态
		mTabs[index].setSelected(true);
		currentTabIndex = index;
	}
}
