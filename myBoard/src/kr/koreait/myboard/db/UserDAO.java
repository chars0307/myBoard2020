package kr.koreait.myboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.koreait.myboard.vo.UserVO;

public class UserDAO {
	
	//return 0:에러발생, 1:등록이 잘 됐음

	public static int joinUser(UserVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
//	    위 2가지(
//		Connection con = null;
//		PreparedStatement ps = null;	
//	)는 무조건 필요한거 묻지도 따지지도 말고 필요한거
		
		String sql = " insert into t_user "
			+ "(u_id, u_pw, u_nickname, email, ph, addr, sex, birth) "
			+ " values "
			+ " (?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			con = DbBrifge.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getU_id());
			ps.setString(2, param.getU_pw());
			ps.setString(3, param.getU_nickname());
			ps.setString(4, param.getEmail());
			ps.setString(5, param.getPh());
			ps.setString(6, param.getAddr());
			ps.setInt(7, 0);
			ps.setString(8, param.getBirth());
			
			result = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DbBrifge.close(con, ps);
		}
		return result;
	}
	
	// 1 : 로긴 성공
	// 2 : 아이디 없음
	// 3 : 비밀번호 틀림
	public static int doLogin(UserVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " SELECT * FROM myboard "
				+ " (u_id, u_pw)"
				+ " VALUES"
				+ " (?, ?) ";		
				
		try {
			con = DbBrifge.getCon();		
			ps = con.prepareStatement(sql);			
			ps.execute();
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbBrifge.close(con, ps, null);	
		}
		return 0;
	}

	
}
