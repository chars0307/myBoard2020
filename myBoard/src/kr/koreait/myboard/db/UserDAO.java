package kr.koreait.myboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.koreait.myboard.vo.UserImgVO;
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
	
	// 파일생성 메소드 _ 메소드명 : regUserImg
			public static int regUserImg(UserImgVO param) {
				int result = 0;
				Connection con = null;
				PreparedStatement ps = null;
				String sql = " insert into t_user_img "
						+ " (i_user, seq, img) "
						+ " VALUES "
						+ " (?, 1, ?) ";
				
				try {   
					con = DbBrifge.getCon();
					ps = con.prepareStatement(sql);
					ps.setInt(1, param.getI_user()); // 첫번째 물음표
					ps.setString(2, param.getImg()); 	 // 두번째 물음표
							
					result = ps.executeUpdate();
					
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					DbBrifge.close(con,  ps);			
				}
				
				return result;
			}
			
//			메소드 생성 _ 메소드명 : getProfileImg
			public static String getProfileImg(int i_user) {
				String img = null;
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				
				String sql = " SELECT img FROM t_user_img " + 
							" WHERE i_user = ? " + 
							" AND seq = 1 ";
				
				try {
					con = DbBrifge.getCon();
					ps = con.prepareStatement(sql);
	
					ps.setInt(1, i_user); // 첫번째 물음표		 
						
					rs = ps.executeQuery();
					while(rs.next()) {
						img = rs.getString("img");
					}
					
//					result = ps.executeUpdate();
					
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					DbBrifge.close(con,  ps);			
				}
				
				return img;
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
					int i_user = rs.getInt("i_user");
					
					param.setU_pw(null);
					param.setU_nickname(nickNm);
					param.setI_user(i_user);
					
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
		
		public static void upUserImgAddSeq(UserImgVO param) {
			Connection con = null;
			PreparedStatement ps = null;
		
			String sql = " UPDATE t_user_img "
					+ " SET seq = seq + 1 "
					+ " WHERE i_user =? "
					+ " ORDER BY seq DESC ";
			
			try {   
				con = DbBrifge.getCon();
				ps = con.prepareStatement(sql);
				ps.setInt(1, param.getI_user()); // 첫번째 물음표
				ps.executeUpdate(); 	 // 두번째 물음표
							
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DbBrifge.close(con,  ps);			
			}
			
		}
		
		
		
	}
	
	
	
