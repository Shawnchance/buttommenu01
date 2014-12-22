package com.example.buttommenu01;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button[] mTabs;

	private int index; // �û����Tab��index

	private int currentTabIndex; // ��ǰfragment��index

	private Fragment[] fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	/**
	 * ��ʼ�����
	 */
	private void initView() {
		mTabs = new Button[3];
		mTabs[0] = (Button) findViewById(R.id.btn_conversation);
		mTabs[1] = (Button) findViewById(R.id.btn_address_list);
		mTabs[2] = (Button) findViewById(R.id.btn_setting);
		// �ѵ�һ��tab��Ϊѡ��״̬
		mTabs[0].setSelected(true);

		Fragment fg1 = new TestFragment("�Ự");
		Fragment fg2 = new TestFragment("ͨѶ¼");
		Fragment fg3 = new TestFragment("����");
		fragments = new Fragment[] { fg1, fg2, fg3 };

		// �����ʾ��һ��fragment
		getFragmentManager().beginTransaction()
				.add(R.id.fragment_container, fg1)
				.add(R.id.fragment_container, fg2)
				.add(R.id.fragment_container, fg3).hide(fg2).hide(fg3)
				.show(fg1).commit();
	}

	/**
	 * button����¼�
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
		// �ѵ�ǰtab��Ϊѡ��״̬
		mTabs[index].setSelected(true);
		currentTabIndex = index;
	}
}
