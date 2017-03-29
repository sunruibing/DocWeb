package com.util;

public class FrontPage {
	private int everyPage;
	private int totalPage;
	private int totalCount;
	private int currentPage;
	private int beginIndex;
	private boolean hasNextPage;
	private boolean hasPrePage;
	public FrontPage() {
	}
	public FrontPage(int everyPage, int totalPage, int totalCount,
			int currentPage, int beginIndex, boolean hasNextPage,
			boolean hasPrePage) {
		super();
		this.everyPage = everyPage;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.beginIndex = beginIndex;
		this.hasNextPage = hasNextPage;
		this.hasPrePage = hasPrePage;
	}
	public static FrontPage createPage(int everyPage, int totalCount,
			int currentPage) {
		everyPage = getEveryPage(everyPage);
		currentPage = getCurrentPage(currentPage);
		int totalPage = getTotalPage(everyPage, totalCount);
		int beginIndex = getBeginIndex(everyPage, currentPage);
		boolean hasPrePage = hasPrePage(currentPage);
		boolean hasNextPage = hasNextPage(currentPage, totalPage);
		return new FrontPage(everyPage, totalPage, totalCount, currentPage,
				beginIndex, hasNextPage, hasPrePage);
	}
	public static int getEveryPage(int everyPage) {
		return everyPage == 0 ? 15 : everyPage;
	}
	public static int getCurrentPage(int currentPage) {
		return currentPage == 0 ? 1 : currentPage;
	}
	public static int getTotalPage(int everyPage, int totalCount) {
		if (totalCount != 0 && totalCount % everyPage == 0) {
			return totalCount / everyPage;
		} else {
			return totalCount / everyPage + 1;
		}
	}
	public static int getBeginIndex(int everyPage, int currentPage) {
		return (currentPage - 1) * everyPage;
	}
	public static boolean hasPrePage(int currentPage) {
		if (currentPage == 1) {
			return false;
		} else {
			return true;
		}
	}
	public static boolean hasNextPage(int currentPage, int totalPage) {
		if (currentPage == totalPage || totalPage == 0) {
			return false;
		} else {
			return true;
		}
	}
	public int getEveryPage() {
		return everyPage;
	}
	public void setEveryPage(int everyPage) {
		this.everyPage = everyPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getBeginIndex() {
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isHasPrePage() {
		return hasPrePage;
	}
	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}
	@Override
	public String toString() {
		return "FrontPage [everyPage=" + everyPage + ", totalPage=" + totalPage
				+ ", totalCount=" + totalCount + ", currentPage=" + currentPage
				+ ", beginIndex=" + beginIndex + ", hasNextPage=" + hasNextPage
				+ ", hasPrePage=" + hasPrePage + "]";
	}
}
