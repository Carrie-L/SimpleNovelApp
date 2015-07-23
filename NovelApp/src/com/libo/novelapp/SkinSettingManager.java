package com.libo.novelapp;


import android.app.Activity;
import android.content.SharedPreferences;

/**
 * PadQzone皮肤管理器
 * @author frankiewei
 *
 */
public class SkinSettingManager {


	public final static String SKIN_PREF = "skinSetting";
	
	public SharedPreferences skinSettingPreference;
	
	private int[] skinResources = { R.drawable.bg_pink,
			R.drawable.bg_yellow,R.drawable.bg_blue,R.drawable.bg_green,
			
	};
	
	private Activity mActivity;
	
	
	public SkinSettingManager(Activity activity) {
		this.mActivity = activity;	
		skinSettingPreference = mActivity.getSharedPreferences(SKIN_PREF, 3);
	}
	
	/**
	 * 获取当前程序的皮肤序号
	 * 
	 * @return
	 */
	public int getSkinType() {
		String key = "skin_type";
		return skinSettingPreference.getInt(key, 1);
	}

	/**
	 * 把皮肤序号写到全局设置里去
	 * 
	 * @param j
	 */
	public void setSkinType(int j) {
		SharedPreferences.Editor editor = skinSettingPreference.edit();
		String key  = "skin_type";
		
		editor.putInt(key, j);
		editor.commit();
	}
	
	/**
	 * 获取当前皮肤的背景图资源id
	 * 
	 * @return
	 */
	public int getCurrentSkinRes() {
		int skinLen = skinResources.length;
		int getSkinLen = getSkinType();
		if(getSkinLen >= skinLen){
			getSkinLen = 0;
		}
		
		return skinResources[getSkinLen];
	}
	
	/**
	 * 用于导航栏皮肤按钮切换皮肤
	 */
	public void toggleSkins(){
		
		int skinType = getSkinType();
		if(skinType == skinResources.length - 1){
			skinType = 0;
		}else{			
			skinType ++;
		}
		setSkinType(skinType);
		mActivity.getWindow().setBackgroundDrawable(null);
		try {
			mActivity.getWindow().setBackgroundDrawableResource(getCurrentSkinRes());
		} catch (Throwable e) {
			e.printStackTrace();

		}
		
		
	}
		
	/**
	 * 用于初始化皮肤
	 */
	public void initSkins(){	
		mActivity.getWindow().setBackgroundDrawableResource(getCurrentSkinRes());
	}

}
