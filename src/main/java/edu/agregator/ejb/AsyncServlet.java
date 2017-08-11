package edu.agregator.ejb;

import us.codecraft.webmagic.Spider;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Future;

@WebServlet(name = "AsyncServlet", urlPatterns = {"/asyncServlet"})
public class AsyncServlet extends HttpServlet {
    @EJB
    private AsyncEJB bean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType( "text/html;charset=windows-1251" );
        PrintWriter out = resp.getWriter();
        out.print("<h1>Анализ данных с сайтов citilink и jast</h1>");
        AsyncEJB AnalisServlet = new AsyncEJB();
        //bean.sayHello();
        AnalisServlet.sayHello();
        Future<String> answer = null;
        if (answer == null) {

            answer = AnalisServlet.sayHello();
            //AnalisServlet.SplitData();
            //out.print(AnalisServlet.ReadDataSourseP().get(2));

        }
        //минимальное значение
        out.print("<hr>Минимальная цена на SSD<br>");
        out.print("<br>В ситилинк<br>");
        out.print(AnalisServlet.dataS.name.get(AnalisServlet.minPriceSSD("citi")));
        out.print("<br>Цена<br>");
        out.print(AnalisServlet.dataS.price.get(AnalisServlet.minPriceSSD("citi")));
        out.print("<br>Описание<br>");
        out.print(AnalisServlet.dataS.disc.get(AnalisServlet.minPriceSSD("citi")));
        out.print("<br>");
//        out.print(AnalisServlet.minPriceSSD("citi"));
//        out.print("<br>");
//        out.print(AnalisServlet.dataS.price.size());
//        out.print("<br>");
//        out.print(AnalisServlet.dataS.name.size());
//        out.print("<br>");
//        out.print(AnalisServlet.dataJ.price.size());
//        out.print("<br>");
//        out.print(AnalisServlet.dataJ.name.size());
//        out.print("<br>");



        out.print("<br>В джаст<br>");
        out.print(AnalisServlet.dataJ.name.get(AnalisServlet.minPriceSSD("jast")));
        out.print("<br>Цена<br>");
        out.print(AnalisServlet.dataJ.price.get(AnalisServlet.minPriceSSD("jast")));
        out.print("<br>Описание<br>");
        out.print(AnalisServlet.dataJ.disc.get(AnalisServlet.minPriceSSD("jast")));
        out.print("<br>");
        //максимальное
        out.print("<hr>Максимальная цена цена на SSD<br>");
        out.print("<br>В ситилинк<br>");
        out.print(AnalisServlet.dataS.name.get(AnalisServlet.maxPriceSSD("citi")));
        out.print("<br>Цена<br>");
        out.print(AnalisServlet.dataS.price.get(AnalisServlet.maxPriceSSD("citi")));
        out.print("<br>Описание<br>");
        out.print(AnalisServlet.dataS.disc.get(AnalisServlet.maxPriceSSD("citi")));
        out.print("<br>");

        out.print("<br>В джаст<br>");
        out.print(AnalisServlet.dataJ.name.get(AnalisServlet.maxPriceSSD("jast")));
        out.print("<br>Цена<br>");
        out.print(AnalisServlet.dataJ.price.get(AnalisServlet.maxPriceSSD("jast")));
        out.print("<br>Описание<br>");
        out.print(AnalisServlet.dataJ.disc.get(AnalisServlet.maxPriceSSD("jast")));
        out.print("<br>");
        //срежднее
        out.print("<hr>Средняя цена на SSD<br>");
        out.print("<br>В ситилинк<br>");
        out.print(AnalisServlet.midPriceSSD("citi"));
        out.print("<br>");

        out.print("<br>В джаст<br>");
        out.print(AnalisServlet.midPriceSSD("jast"));
        out.print("<br>");
    }

}