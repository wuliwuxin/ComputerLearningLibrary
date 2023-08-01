package com.asus.evlessleeping.wiget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.asus.evlessleeping.R;
import com.asus.evlessleeping.wifi.WifiUtil;

public class SetApPwdDialog extends DialogFragment {
	
	public SetApPwdDialog() {
		// TODO Auto-generated constructor stub
	};
	
	FragmentActivity mActivity;
	
	public interface IConnectWifi{
		
		public void onConnectClick(String SSID, String pwd, WifiUtil.WifiCipherType mType);
	}
	
	IConnectWifi mConnectWifi = null;
	
	String SSID ; String pwd ; WifiUtil.WifiCipherType mType;
	 
	public static SetApPwdDialog newInstance(IConnectWifi mConnectWifi , String SSID , WifiUtil.WifiCipherType mType){
		
		SetApPwdDialog mFragment = new SetApPwdDialog();
		
		mFragment.SSID = SSID;
		
		mFragment.mType = mType;
		
		mFragment.mConnectWifi = mConnectWifi;
		
		return mFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		
		try {
			
			mActivity = (FragmentActivity) activity;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		View view = LayoutInflater.from(mActivity).inflate(R.layout.resetpwd, null);
		
		final EditText et = (EditText) view.findViewById(R.id.editText1);
		
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
		
		mBuilder.setView(view)
		.setTitle("请输入密码")
		.setNegativeButton("返回", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		})
		.setPositiveButton("连接", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String pass = et.getText().toString().trim();
				
				if(mConnectWifi != null){
					mConnectWifi.onConnectClick(SSID, pass, mType);
				}
			}
		});
		
		return mBuilder.create();
	}
	
	public static void show(FragmentActivity mActivity,IConnectWifi mConnectWifi , String SSID , WifiUtil.WifiCipherType mType){
		
		FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
		
		Fragment mBefore = mActivity.getSupportFragmentManager().findFragmentByTag(SetApPwdDialog.class.getSimpleName());
		
		if(mBefore != null){
			
			((DialogFragment)mBefore).dismiss();
			
			ft.remove(mBefore);
		}
		ft.addToBackStack(null);
		
		DialogFragment mNow =  SetApPwdDialog.newInstance(mConnectWifi , SSID ,  mType);
		
		mNow.show(ft, SetApPwdDialog.class.getSimpleName());
	}
}
