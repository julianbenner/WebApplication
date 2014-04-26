package main;

import java.util.ArrayList;

public class SearchResult {
	private ArrayList<Book> bookArrayList;
	private int pages;
	private int currentPage;

	public SearchResult() {
	}

	public ArrayList<Book> getBookArrayList() {
		return bookArrayList;
	}

	public void setBookArrayList(ArrayList<Book> bookArrayList) {
		this.bookArrayList = bookArrayList;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
