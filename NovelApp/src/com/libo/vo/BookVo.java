package com.libo.vo;

/**
 * 
 * ��¼��ĵ�ַ������״̬
 * 
 * @author
 */
public class BookVo {
	private String path;// �鼮·��
	private int local;// ����״̬

	// ��ַ
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	// ����״̬
	public int getLocal() {
		return local;
	}

	public void setLocal(int local) {
		this.local = local;
	}

	public BookVo(String path, int local) {
		super();
		this.path = path;
		this.local = local;
	}

}
