package com.libo.novelapp;


import android.app.Activity;
import android.content.SharedPreferences;

/**
 * PadQzoneƤ��������
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
	 * ��ȡ��ǰ�����Ƥ�����
	 * 
	 * @return
	 */
	public int getSkinType() {
		String key = "skin_type";
		return skinSettingPreference.getInt(key, 1);
	}

	/**
	 * ��Ƥ�����д��ȫ��������ȥ
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
	 * ��ȡ��ǰƤ���ı���ͼ��Դid
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
	 * ���ڵ�����Ƥ����ť�л�Ƥ��
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
	 * ���ڳ�ʼ��Ƥ��
	 */
	public void initSkins(){	
		mActivity.getWindow().setBackgroundDrawableResource(getCurrentSkinRes());
	}

}
