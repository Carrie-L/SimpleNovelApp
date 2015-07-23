package com.libo.vo;

/**
 * 
 * 记录书的地址及导入状态
 * 
 * @author
 */
public class BookVo {
	private String path;// 书籍路径
	private int local;// 导入状态

	// 地址
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	// 导入状态
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
