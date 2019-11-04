package com.kitcut.helloworld.baserestapi.dto.response.employee;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BasePageResponse<T> {
	
	protected List<T> content;
	protected int number;
	protected int size;
	protected long totalElements;
	protected int totalPages;

//	public BasePageResponse<T> fromPage(Page<T> page){
//		items = page.getContent();
//		totalPages = page.getTotalPages();
//		totalItems = page.getTotalElements();
//		pageSize = page.getSize();
//		currentPage = page.getNumber();
//		return this;
//	}
}
