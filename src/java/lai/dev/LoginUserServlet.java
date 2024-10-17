/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package lai.dev;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import lai.dev.data.dao.DatabaseDao;
import lai.dev.data.dao.UserDao;
import lai.dev.data.model.User;
/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginUserServlet", urlPatterns = {"/LoginUserServlet"})
public class LoginUserServlet extends BaseServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = DatabaseDao.getInstance().getUserDao();
        User user = userDao.checkUser(email, password);
        
        HttpSession session = request.getSession();
        
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Đăng nhập</title></head>");
        out.println("<body>");

        if (user == null) {
            response.sendRedirect("LoginServlet");
            session.setAttribute("errors", "Login Failed!!");
        } else if (user.getRole().equals("admin")) {
            response.sendRedirect("DashboardServlet");
            session.setAttribute("user", user);
        } else {
            response.sendRedirect("HomeServlet");
            session.setAttribute("user", user);
        }

        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}