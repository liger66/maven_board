package com.inc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inc.dao.BoardDao;
import com.inc.util.Paging;
import com.inc.vo.Board;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao;
	private Paging pager;

	public void setPaging(Paging paging) {
		this.pager = paging;
	}

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public Map<String, Object> list(String option, String text, int page) {
		String searchParam = "";
		if(option != null && !option.equals("all")) {
			searchParam = "&option="+option+"&text="+text;
		}
		int numberOfList = 5;
		int numberOfPage = 3;
		
		int start = (page - 1) * numberOfList + 1;
		int end = start + numberOfList - 1;
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("start", start);
		searchMap.put("end", end);
		searchMap.put("option", option);
		searchMap.put("text", text);
		
		List<Board> boardList = boardDao.selectList(searchMap);
		
		Map<String, Object> bList = new HashMap<>();
		
		bList.put ("boardList", boardList);
		
		int totalCount = boardDao.totalCount(searchMap);
		
		String paging = pager.getPaging("/board/list", page, totalCount, numberOfList, numberOfPage, searchParam);
		bList.put ("paging", paging);
		
		return bList;
	}

	@Override
	public Board selectOne(int id) {
		
		boardDao.plusHit(id);
		
		Board borad = boardDao.selectOne(id);
		return borad;
	}

	@Override
	public void insert(Board board) {
		boardDao.insert(board);
	}

	@Override
	public void delete(int id) {
		boardDao.delete(id);
	}

	@Override
	public void update(Board board) {
		boardDao.update(board);
	}

	@Override
	public void updateStep(Board board) {
		boardDao.updateStep(board);
	}

	@Override
	public void comment(Board board) {
		boardDao.comment(board);		
	}
}
