package com.inc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.inc.dao.ReplyDao;
import com.inc.service.ReplyService;
import com.inc.vo.Reply;

@Controller
public class ReplyController {
	private ReplyService replyService;
	
	public ReplyService getReplyService() {
		return replyService;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@RequestMapping (value="/reply/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute Reply reply) {
		replyService.insert(reply);
		return "redirect:/board/view?id=" + reply.getB_id();
	}
	/*public String insert (@RequestParam(required=false) int b_id,
						  @RequestParam(required=false) String name,
						  @RequestParam(required=false) String content, Model model) {
		Reply reply = new Reply();
		
		reply.setB_id(b_id);
		reply.setName(name);
		reply.setContent(content);
		
		replyService.insert(reply);
		
		board.
		
		return "/board/view.jsp";
	}
	*/
}
