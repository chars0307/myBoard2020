//package kr.koreait.myboard.db;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
//import kr.koreait.myboard.vo.UserVO;
//
//public class UserDAO {
//	
//	//return 0:에러발생, 1:등록이 잘 됐음
//
//	public static int joinUser(UserVO param) {
//		int result = 0;
//		Connection con = null;
//		PreparedStatement ps = null;
////	    위 2가지(
////		Connection con = null;
////		PreparedStatement ps = null;	
////	)는 무조건 필요한거 묻지도 따지지도 말고 필요한거
//		
//		String sql = " insert into t_user "
//			+ "(u_id, u_pw, u_nickname, email, ph, addr, sex, birth) "
//			+ " values "
//			+ " (?, ?, ?, ?, ?, ?, ?, ?) ";
//		
//		try {
//			con = DbBrifge.getCon();
//			ps = con.prepareStatement(sql);
//			ps.setString(1, param.getU_id());
//			ps.setString(2, param.getU_pw());
//			ps.setString(3, param.getU_nickname());
//			ps.setString(4, param.getEmail());
//			ps.setString(5, param.getPh());
//			ps.setString(6, param.getAddr());
//			ps.setInt(7, 0);
//			ps.setString(8, param.getBirth());
//			
//			result = ps.executeUpdate();
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			DbBrifge.close(con, ps);
//		}
//		return result;
//	}
//	
//	// 1 : 로긴 성공
//	// 2 : 아이디 없음
//	// 3 : 비밀번호 틀림
//	public static int doLogin(UserVO param) {
//		int result = 0;
//		Connection con = null;
//		PreparedStatement ps = null;
//		
//		String sql = " SELECT * FROM myboard "
//				+ " (u_id, u_pw)"
//				+ " VALUES"
//				+ " (?, ?) ";		
//				
//		try {
//			con = DbBrifge.getCon();		
//			ps = con.prepareStatement(sql);			
////			ps.execute();
//			ps.setString(1,param.getU_id());
//			
//			rs = ps.executeQuery();
//			if(rs.next()) {
//				String u_pw = rs.getString("u_pw");
//				if(u_pw.equals(param.getU_pw())) { // 로그인 성공
//					result = 1;
//				}
//			}
//			
//		} catch (Exception e) {			
//			e.printStackTrace();
//		} finally {
//			DbBrifge.close(con, ps, null);	
//		}
//		return 0;
//	}
//
//	
//}

package kr.koreait.myboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.koreait.myboard.vo.UserVO;

public class UserDAO {
	
	//return 0:에러 발생, 1:등록이 잘 됐음
	public static int joinUser(UserVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " insert into t_user "
				+ " (u_id, u_pw, u_nickname, email, ph, addr, sex, birth) "
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
			ps.setInt(7, param.getSex());
			ps.setString(8, param.getBirth());
			
			
			result = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DbBrifge.close(con,  ps);			
		}
		
		return result;
	}
	//0:알수없는 에러발생
	//1:로긴 성공
	//2:아이디 없음
	//3:비밀번호 틀림
	public static int doLogin(UserVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_user WHERE u_id = ? ";
		
		try {
			con = DbBrifge.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getU_id());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				String u_pw = rs.getString("u_pw");				
				if(u_pw.equals(param.getU_pw())) { //로그인 성공
					result = 1;
					
					String nickNm = rs.getString("u_nickname");
//				int 추가해줘야한다.    
					param.setU_pw(null);
					param.setU_nickname(nickNm);
					
				} else {
					result = 3;
				}
			} else {
				result = 2;
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbBrifge.close(con, ps, rs);
		}
		
		return result;
	}
	
	
	public static int boardRedMod(UserVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " insert into t_board "
				+ " (i_board, title, content, i-user, hits, r_dt, m_dt) "
				+ " values "
				+ " (?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			con = DbBrifge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			ps.setString(2, param.getTitle());
			ps.setString(3, param.getContent());
			ps.setInt(4, param.getI_user());
			ps.setInt(5, param.gethits());
			ps.setString(6, param.getR_dt());
			ps.setString(7, param.getM_dt());
			
			
			
			result = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DbBrifge.close(con,  ps);			
		}
		
		return result;
	}
	
	
	
}
