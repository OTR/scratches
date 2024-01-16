package com.github.otr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.script.ScriptEngineManager;

@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Calculator</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Calculator</h1>");
        out.println("<form action=\"calculator\" method=\"post\">");
        out.println("<input type=\"text\" name=\"expression\" placeholder=\"Enter expression\">");
        out.println("<input type=\"submit\" value=\"Calculate\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Calculator</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Calculator</h1>");
        String expression = request.getParameter("expression");
        if (expression != null && !expression.isEmpty()) {
            try {
                double result = evaluateExpression(expression);
                out.println("<h2>Result: " + result + "</h2>");
            } catch (Exception e) {
                out.println("<h2>Error: " + e.getMessage() + "</h2>");
            }
        } else {
            out.println("<h2>Please enter an expression.</h2>");
        }
        out.println("</body>");
        out.println("</html>");
    }

    private double evaluateExpression(String expression) throws java.lang.Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("java.script");
        Object result = engine.eval(expression);
        return (Double) result;
    }

}
