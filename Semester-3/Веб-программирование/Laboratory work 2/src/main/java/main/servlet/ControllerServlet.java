package main.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.model.AreaData;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedDeque;

@WebServlet(name = "controllerServlet", value = "/controller")
public class ControllerServlet extends HttpServlet {
    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        String forwardPath = getServletContext().getContextPath();

        if (request.getParameter("clear") != null) {
            @SuppressWarnings("unchecked")
            ConcurrentLinkedDeque<AreaData> globalData = (ConcurrentLinkedDeque<AreaData>) getServletContext().getAttribute("globalData");

            // Потокобезопасное добавление данных в список
            synchronized (globalData) {
                globalData.clear();
            }
        }

        if (request.getParameter("x") != null
                && request.getParameter("y") != null
                && request.getParameter("r") != null)
        {
            System.out.println("posting to AreaChecker...");
            forwardPath = this.getServletContext().getContextPath() + "/area-check?x=" + request.getParameter("x")
                    + "&y=" + request.getParameter("y") + "&r=" + request.getParameter("r");
        }

        System.out.println("Posting...");

        response.sendRedirect(forwardPath);
    }
}
