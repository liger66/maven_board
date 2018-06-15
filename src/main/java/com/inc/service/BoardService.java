package com.inc.service;

import java.util.List;
import java.util.Map;

import com.inc.vo.Board;

public interface BoardService {
	
	Map<String, Object> list(String option, String text, int page);

	Board selectOne(int id);

	void insert(Board board);

	void delete(int id);

	void update(Board board);

	void updateStep(Board board);

	void comment(Board board);
	
}
