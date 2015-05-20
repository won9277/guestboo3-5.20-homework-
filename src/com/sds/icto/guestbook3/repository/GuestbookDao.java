package com.sds.icto.guestbook3.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.sds.icto.guestbook3.domain.GuestbookVo;
import com.sds.icto.guestbook3.exception.GuestbookDaoException;

@Repository
public class GuestbookDao {

	@Autowired
	SqlMapClientTemplate sqlMapClietTemplate;

	public void insertGuestBook(GuestbookVo vo) {

		sqlMapClietTemplate.insert("guestbook.insert", vo);
	}
	public void deleteGuestBook(GuestbookVo vo) {

		sqlMapClietTemplate.delete("guestbook.delete", vo);
	}

		@SuppressWarnings("unchecked")	
	public List<GuestbookVo> selectGuestBook() {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		list = sqlMapClietTemplate.queryForList("guestbook.list");
		return list;
	}

}