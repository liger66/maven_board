package com.inc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.inc.service.BoardService;
import com.inc.vo.Board;

@Controller
public class BoardController {
	private BoardService boardService;

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public String list(@RequestParam(required=false) String option,
					   @RequestParam(required=false) String text,
					   @RequestParam(defaultValue="1") int page, Model model) {
		Map<String, Object> bList = (Map<String, Object>) boardService.list(option, text, page);
		
		model.addAttribute("boardList", bList.get("boardList"));
		model.addAttribute("paging", bList.get("paging"));
		
		return "/board/list.jsp";
	}
	
	@RequestMapping(value="/board/view", method=RequestMethod.GET)
	public String view(@RequestParam(required=false) int id, Model model) {
		Board board = boardService.selectOne(id);
		
		model.addAttribute("board", board);
		return "/board/view.jsp";
	}
	
	@RequestMapping(value="/board/insert", method=RequestMethod.GET)
	public String insert() {
		return "/board/insert.jsp";
	}
	
	@RequestMapping(value="/board/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute Board board, HttpServletRequest request) {
		
		board.setIp(request.getRemoteAddr());
		
		boardService.insert(board);
		
		return "redirect:/board/list";
	}
	
	/*@RequestMapping(value="/board/insert", method=RequestMethod.POST)
	public String insert(@RequestParam(required=false) String title,
						 @RequestParam(required=false) String name,
						 @RequestParam(required=false) String content,
						 HttpServletRequest request, Model model) {
		Board board = new Board();
		board.setTitle(title);
		board.setName(name);
		board.setContent(content);
		board.setIp(request.getRemoteAddr());
		
		boardService.insert(board);
		
		return "redirect:/board/list";
	}*/
	
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public String delete(@RequestParam(required=false) int id) {
		
		boardService.delete(id);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/board/update", method=RequestMethod.GET)
	public String update(@RequestParam(required=false) int id, Model model) {
		
		Board board = boardService.selectOne(id);
		model.addAttribute("board", board);
		
		return "/board/update.jsp";
	}
	
	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String update(@ModelAttribute Board board, HttpServletRequest request) {
		
		board.setIp(request.getRemoteAddr());
		
		boardService.update(board);
		
		return "redirect:/board/list";
	}
	
	/*@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String update(@RequestParam(required=false) int id,
						 @RequestParam(required=false) String title,
						 @RequestParam(required=false) String name,
						 @RequestParam(required=false) String content,
						 HttpServletRequest request, Model model) {
		Board board = new Board();
		board.setId(id);
		board.setTitle(title);
		board.setName(name);
		board.setContent(content);
		board.setIp(request.getRemoteAddr());
		
		boardService.update(board);
		
		return "redirect:/board/list";
	}*/
	
	@RequestMapping(value="/board/comment", method=RequestMethod.GET)
	public String comment(@RequestParam(required=false) int id, Model model) {
		
		Board board = boardService.selectOne(id);
		model.addAttribute("board", board);
		
		return "/board/comment.jsp";
	}
	
	@RequestMapping(value="/board/comment", method=RequestMethod.POST)
	public String comment(@RequestParam(required=false) int id,
						 @RequestParam(required=false) String title,
						 @RequestParam(required=false) String name,
						 @RequestParam(required=false) String content,
						 HttpServletRequest request, Model model) {
				
		Board oriBoard = boardService.selectOne(id);
		
		int ref = oriBoard.getRef();
		int step = oriBoard.getStep() + 1;
		int depth = oriBoard.getDepth() + 1;
		
		//답글 생성
		Board board = new Board();
		board.setTitle(title);
		board.setName(name);
		board.setContent(content);
		board.setRef(ref);
		board.setStep(step);
		board.setDepth(depth);
		board.setIp(request.getRemoteAddr());
		
		//step변경
		boardService.updateStep(board);
		
		//답글 추가
		boardService.comment(board);
		
		return "redirect:/board/list";
	}
}
