package com.github.otr.feature.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/*

 */
public class SlugGeneratorServlet extends HttpServlet {

    @Override
    public void doGet (
            HttpServletRequest request,
            HttpServletResponse response
    )  throws IOException {
//        response.content_type = "plain/text";  // TODO:
        PrintWriter out = response.getWriter();
        out.println("Hello!");
    }

}
