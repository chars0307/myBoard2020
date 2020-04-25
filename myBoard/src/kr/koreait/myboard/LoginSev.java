//package kr.koreait.myboard;
//
//import java.io.IOException;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//
//import kr.koreait.myboard.db.UserDAO;
//import kr.koreait.myboard.vo.UserVO;
//
//@WebServlet("/login")
//public class LoginSev extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	//이 페이지를 로그인 시도 없이 왔는지
//    	
//    	//로그인 시도 했다가 오류가 떠서 왔는지 구분을 해야한다.
//    	
//    	String error = request.getParameter("error");
//    	//null이 아니었다면..
//    	if(error != null) { //로그인이 실패 했음!!
//    		String errorMsg = "";
//    		switch(error) {
//    		case "0":
//    			errorMsg = "알 수 없는 에러 발생";
//    			break;
//    		case "2":
//    			errorMsg = "아이디를 확인해 주세요";
//    			break;
//    		case "3":
//    			errorMsg = "비밀번호를 확인해 주세요";
//    			break;
//    		}
//    		request.setAttribute("msg", errorMsg);
//    	}
//    	
//    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
//		rd.forward(request, response);
//    }
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String u_id = request.getParameter("u_id");
//		String u_pw = request.getParameter("u_pw");
//		
//		System.out.println("u_id : " + u_id);
//		System.out.println("u_pw : " + u_pw);
//		
//		UserVO param = new UserVO();
//		param.setU_id(u_id);
//		param.setU_pw(u_pw);
//		
//		int result = UserDAO.doLogin(param);
//		if(result ==1 ) {
//			//세션에 값세팅
//			HttpSession hs = request.getSession(); //session 얻는 법 _ 주소값 얻고
//			response.sendRedirect("/boardList");  //send하면 된다.
//			return;
//		}
//
//		response.sendRedirect("result : " + result);			
//		System.out.println("result : " + result);
//		
//	}
//
//}

package kr.koreait.myboard;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import kr.koreait.myboard.db.UserDAO;
import kr.koreait.myboard.vo.UserVO;


@WebServlet("/login")
public class LoginSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//이 페이지를 로그인 시도 없이 왔는지
		
		//로그인 시도 했다가 오류가 떠서 왔는지
		
		String error = request.getParameter("error");
		if(error != null) { //로그인 실패 했음!!
			String errorMsg = "";
			switch(error) {
			case "0":
				errorMsg = "알 수 없는 에러 발생";
				break;
			case "2":
				errorMsg = "아이디를 확인해 주세요";
				break;
			case "3":
				errorMsg = "비밀번호를 확인해 주세요";
				break;
			}
			request.setAttribute("msg", errorMsg);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u_id = request.getParameter("u_id");
		String u_pw = request.getParameter("u_pw");
		
		System.out.println("u_id : " + u_id);
		System.out.println("u_pw : " + u_pw);
		
		UserVO param = new UserVO();
		param.setU_id(u_id);
		param.setU_pw(u_pw);
		
		int result = UserDAO.doLogin(param);
		if(result == 1) {			
			//세션에 값세팅
			HttpSession hs = request.getSession();
			hs.setAttribute("loginUser", param);
			
			response.sendRedirect("/boardList");
			return;
		}
		
		response.sendRedirect("/login?error=" + result);
		
	}

}