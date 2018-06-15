package com.inc.dao;

import org.apache.ibatis.session.SqlSession;

import com.inc.vo.Reply;

public class ReplyDao {
	SqlSession session;
	
	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}

	public void insert(Reply reply) {
		session.insert("reply.insert", reply);
	}
}