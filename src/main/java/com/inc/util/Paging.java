package com.inc.util;

public class Paging {
	
	public String getPaging(String url,  int nowPage, int totalCount, int numberOfList,
			                int numberOfPage, String searchParam) {
		StringBuffer sb = new StringBuffer();
		
		//전체 페이지 수
		int totalPage;
		//시작페이지
		int startPage;
		//마지막페이지
		int endPage;
		//다음페이지 존재여부
		boolean isNextPage;
		//이전페이지 존재여부
		boolean isPrevPage;
		
		//전체페이지 구하기
		if(totalCount % numberOfList == 0) {
			totalPage = totalCount/numberOfList;
		}else {
			totalPage = totalCount/numberOfList + 1;
		}
		
		//시작페이지 구하기
		startPage = (nowPage - 1)/numberOfPage * numberOfPage + 1;
		//마지막페이지 구하기
		endPage = startPage + numberOfPage - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		//다음페이지 존재여부
		if(totalPage >= startPage + numberOfPage) {
			isNextPage = true;
		}else {
			isNextPage = false;
		}
		
		//이전페이지 존재 여부
		if(nowPage  > numberOfPage) {
			isPrevPage = true;
		}else {
			isPrevPage = false;
		}
		
		//이전페이지 작성
		if(isPrevPage) {
			sb.append("<li><a href='"+url+"?page=");
			sb.append(nowPage-numberOfPage+searchParam);
			sb.append("'><span>◀</span></a></li>");
		}else {
			sb.append("<li class='disabled'><span>◁</span></li>");
		}
		//페이지 목록
		for(int i = startPage; i <= endPage; i++) {
			if(i == nowPage) {
				sb.append("<li class='active'><a href=''>"+i+"</a></li>");
			}else {
				sb.append("<li><a href='"+url+"?page=");
				sb.append(i+searchParam+"'>");
				sb.append(i+"</a></li>");
			}
			
		}
		//다음페이지 작성
		if(isNextPage) {
			sb.append("<li><a href='"+url+"?page=");
			if(nowPage + numberOfPage > totalPage) {
				sb.append(totalPage);
			}else {
				sb.append(nowPage+numberOfPage);
			}
			sb.append(searchParam);
			sb.append("'><span>▶</span></a></li>");
		}else {
			sb.append("<li class='disabled'><span>▷</span></li>");
		}
		
		return sb.toString();
	}
}