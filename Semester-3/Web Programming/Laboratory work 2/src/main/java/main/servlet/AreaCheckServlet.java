package main.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.model.AreaData;
import main.model.UserAreaDatas;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.SynchronousQueue;

public class AreaCheckServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        // Инициализация общего списка в контексте приложения при старте сервлета
        getServletContext().setAttribute("globalData", new ConcurrentLinkedDeque<AreaData>());
        getServletContext().setAttribute("last", new ConcurrentLinkedDeque<AreaData>());
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        final long startExec = System.nanoTime();

        final String x = request.getParameter("x");
        final String y = request.getParameter("y");
        final String r = request.getParameter("r");

        final double dx;
        final double dy;
        final double dr;

        try {
            dx = Double.parseDouble(x);
            dy = Double.parseDouble(y);
            dr = Double.parseDouble(r);
        } catch (NumberFormatException | NullPointerException e) {
            response.sendError(400);
            return;
        }

        final boolean result = checkArea(dx, dy, dr);

        final long endExec = System.nanoTime();
        final long executionTime = endExec - startExec;
        final LocalDateTime executedAt = LocalDateTime.now();

        final AreaData data = new AreaData();
        data.setX(dx);
        data.setY(dy);
        data.setR(dr);
        data.setResult(result);
        data.setCalculationTime(executionTime);
        data.setCalculatedAt(executedAt);

        // Доступ к общему списку в контексте приложения
        @SuppressWarnings("unchecked")
        ConcurrentLinkedDeque<AreaData> globalData = (ConcurrentLinkedDeque<AreaData>) getServletContext().getAttribute("globalData");

        @SuppressWarnings("unchecked")
        ConcurrentLinkedDeque<AreaData> last = (ConcurrentLinkedDeque<AreaData>) getServletContext().getAttribute("last");

        // Потокобезопасное добавление данных в список
        synchronized (globalData) {
            globalData.addFirst(data);
        }

        synchronized (last){
            last.clear();

            last.addFirst(data);
        }

        System.out.println("Global Data Updated!");
        response.setContentType("text/html;charset=UTF-8");

        // Перенаправление на страницу отображения результатов
        response.sendRedirect("table.jsp");
    }

    private boolean checkArea(final double x, final double y, final double r) {
        if (x <= 0 && y <= 0 && x >= (-r / 2) && y >= (-r / 2)) {
            double newX = -r / 2 - x;
            if (y >= newX) {
                return true;
            }
        }else if(x > 0 && y > 0){
            return false;
        }
        else if (x <= 0 && y >= 0) {
            return (x * x) + (y * y) <= ((r / 2) * (r / 2));
        } else if (x >= 0 && y <= 0 && y >= (-r / 2)) {
            return true;
        }
        return false;
    }
}
