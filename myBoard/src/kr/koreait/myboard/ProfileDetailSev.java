package kr.koreait.myboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.koreait.myboard.vo.UserVO;

@WebServlet("/profileDetail")
public class ProfileDetailSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		//로그인 체크
   		HttpSession hs = request.getSession();
   		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
   		if(loginUser == null) {
   			response.sendRedirect("/login");
   			return;
   		}
   		
   		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profileDetail.jsp");
   		rd.forward(request, response);
   		
   	}

   	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
   		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
   		
   		String filePath = String.ValueOf(loginUser.getI_user());
		String fileNm = Utils.uploadFile(request, loginUser.getI_user());
		
		
		
		/*
		//		체인기법
		String path = request.getSession().getServletContext().getRealPath("img");
		/*
		HttpSession hs = request.getSession();
		ServeletContext sc = hs.getServeletContext();
		String path = sc.getRealPath("img");
		
		
		int size = 1024 * 1024 * 10; //10mb
		String fileNm = null;
		String originalFileNm = null;
		
		
		try {
			MultipartRequest mr = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
			Enumeration files = mr.getFileNames();
			String str = (String)files.nextElement();
			
			fileNm = mr.getFilesystemName(str);
			originalFileNm = mr.getOriginalFileName(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
		System.out.println("path : " +path);
	}
	*/

}
