/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package lai.dev;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lai.dev.data.dao.CategoryDao;
import lai.dev.data.dao.Database;
import lai.dev.data.dao.DatabaseDao;
import lai.dev.data.model.Category;

/**
 *
 * @author tranq
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        DatabaseDao.init(new Database());
//        
//        String name = request.getParameter("name");
//        String description = request.getParameter("description");
//        
//        Category category = new Category(name, description);
//        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
//        categoryDao.delete(1);
        HttpSession session = request.getSession();
        if (session.getAttribute("a") == null) {
            int a = 0;
            session.setAttribute("a", a);
        } else {
            int a = (int) session.getAttribute("a");
            a++;
            session.setAttribute("a", a);
            PrintWriter out = response.getWriter();
            out.print(a);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
