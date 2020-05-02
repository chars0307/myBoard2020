  
package kr.koreait.myboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.koreait.myboard.vo.BoardVO;

//--------------------------------------------------------------Create (update)
public class BoardDAO {
	public static int insertBoard(BoardVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " INSERT INTO t_board "
				+ " (title, content, i_user) "
				+ " VALUES "
				+ " (?, ?, ?) ";
		
		try {
			con = DbBrifge.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getTitle());
			ps.setString(2, param.getContent());
			ps.setInt(3, param.getI_user());			
			
			result = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DbBrifge.close(con,  ps);			
		}
		
		return result;
	}
	
	//--------------------------------------------------------------Read (update)
	public static BoardVO getBoard(BoardVO param) {
		BoardVO vo = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT "
				+ " A.title, A.hits, A.r_dt, A.m_dt "
				+ " , A.content, A.i_user, B.u_nickname "
				+ " FROM t_board A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " WHERE A.i_board = ? "
				+ " ORDER BY r_dt DESC ";
		
		try {
			con = DbBrifge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());			
			rs = ps.executeQuery();
			
			while(rs.next()) {				
				String title = rs.getString("title");
				String content = rs.getString("content");
				int i_user = rs.getInt("i_user");
				int hits = rs.getInt("hits");
				String r_dt = rs.getString("r_dt");
				String m_dt = rs.getString("m_dt");
				String u_nickname = rs.getString("u_nickname");
								
				vo = new BoardVO();
				vo.setI_board(param.getI_board());
				vo.setTitle(title);
				vo.setContent(content);
				vo.setI_user(i_user);
				vo.setHits(hits);
				vo.setR_dt(r_dt);
				vo.setM_dt(m_dt);
				vo.setU_nickname(u_nickname);				
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbBrifge.close(con, ps, rs);
		}
		
		return vo;
	}
	
	//--------------------------------------------------------------Create (update)
	public static List<BoardVO> getBoardList() {
		List<BoardVO> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT "
				+ " A.i_board, A.title, A.hits, A.r_dt "
				+ " , B.u_nickname "
				+ " FROM t_board A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " ORDER BY r_dt DESC ";
		
		try {
			con = DbBrifge.getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int i_board = rs.getInt("i_board");
				String title = rs.getString("title");
				int hits = rs.getInt("hits");
				String r_dt = rs.getString("r_dt");
				String u_nickname = rs.getString("u_nickname");
				
				BoardVO vo = new BoardVO();
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setHits(hits);
				vo.setR_dt(r_dt);
				vo.setU_nickname(u_nickname);
				
				list.add(vo);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbBrifge.close(con, ps, rs);
		}
		
		return list;
	}
	
	//조회수 수정
	public static int updateBoardHits(BoardVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " UPDATE t_board "
				+ " SET hits = hits + 1"
				+ " WHERE i_board = ? ";		
		
		try {
			con = DbBrifge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());	
		
			result = ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbBrifge.close(con, ps);
		}
		
		return result;
	}
	
	//조회수 수정
		public static int delBoard(BoardVO param) {
			int result = 0;
			Connection con = null;
			PreparedStatement ps = null;
			
			String sql = " DELETE FORM t_board "
					+ " WHERE i_board = ? "
					+ " AND i_user = ? ";		
			
			try {
				con = DbBrifge.getCon();
				ps = con.prepareStatement(sql);
				ps.setInt(1, param.getI_board());	
				ps.setInt(2, param.getI_user());	
			
				result = ps.executeUpdate();
				
			} catch (Exception e) {			
				e.printStackTrace();
			} finally {
				DbBrifge.close(con, ps);
			}
			
			return result;
		}
}