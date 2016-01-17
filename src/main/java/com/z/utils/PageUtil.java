package com.z.utils;

public class PageUtil {

	public static Page getPage(int nowPageNumber, int allSize, int sizePerPage) {
		Page ipage = new Page(nowPageNumber, allSize, sizePerPage);
		return ipage;
	}

	public static Page getPage(int nowPageNumber, int allSize, int sizePerPage, int pageNumListSize) {
		Page ipage = new Page(nowPageNumber, allSize, sizePerPage, pageNumListSize);
		return ipage;
	}

	/**
	 * 得到分页的类
	 * @param nowStartNumber 当前的页数
	 * @param sizePerPage 每页显示的个数
	 * @return
	 */
	public static Page getPage(int nowStartNumber, int sizePerPage) {
		nowStartNumber = (nowStartNumber < 0) ? 0:nowStartNumber;
		Page ipage = new Page(nowStartNumber, sizePerPage);
		return ipage;
	}

}
