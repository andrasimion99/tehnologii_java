package com.example.demo1;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.*;

@WebServlet(name = "helloServlet", urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {
    private boolean mock;
    private boolean sync;
    private String key;
    private int value;
    private final String filePath = "C:\\work\\facultate\\master\\java\\tehnologii_java\\demo1\\src\\main\\java\\com\\example\\demo1\\repository.txt";
    private static final Logger logger = Logger.getLogger("helloServlet");

    public void init() {
        mock = false;
        sync = false;
        key = "";
        value = 0;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info(req.getMethod());
        logger.info(req.getRemoteAddr());
        logger.info(req.getHeader("USER-AGENT"));
        logger.info(req.getLocale().getLanguage());
        logger.info("mock=" + req.getParameter("mock"));
        logger.info("sync=" + req.getParameter("sync"));
        logger.info("key=" + req.getParameter("key"));
        logger.info("value=" + req.getParameter("value"));
        if(req.getHeader("USER-AGENT").contains("PostmanRuntime"))
        {
            resp.setContentType("text/plain;charset=UTF-8");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
        }
        PrintWriter out = resp.getWriter();
        try {
            mock = Boolean.parseBoolean(req.getParameter("mock"));
            sync = Boolean.parseBoolean(req.getParameter("sync"));
            key = req.getParameter("key");
            value = Integer.parseInt(req.getParameter("value"));
            if(mock)
            {
                out.println("Mock = " + mock + ". So this is a confirmation message");
            } else
            {
                if (sync)
                {
                    WriteInFile(req);
                } else {
                    WriteAsyncInFile(req);
                }
                DisplayContent(out);
            }
        } catch (Exception ex){
            out.println(ex);
            ex.printStackTrace();
        }
    }

    public void destroy() {
    }

    private void WriteInFile(HttpServletRequest req) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        for (int i = 0; i < value; i++) {
            writer.append(key);
            writer.append(" ");
        }
        writer.append(String.valueOf(req.getSession().getLastAccessedTime()));
        writer.append("\n");
        writer.close();
    }

    private void DisplayContent(PrintWriter out) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        SortedSet<String> set = new TreeSet<>();
        String line = bufferedReader.readLine();
        while(line != null) {
            set.add(line);
            line = bufferedReader.readLine();
        }
        for (String s : set) {
            out.println(s);
            out.println("<br/>");
        }
        bufferedReader.close();
    }

    private void WriteAsyncInFile(HttpServletRequest req) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        for (int i = 0; i < value; i++) {
            buffer.put(key.getBytes());
            buffer.put(" ".getBytes());
        }
        buffer.put(String.valueOf(req.getSession().getLastAccessedTime()).getBytes());
        buffer.put("\n".getBytes());
        buffer.flip();

        long bytes = Files.size(Paths.get(filePath));

        AsynchronousFileChannel asyncChannel = AsynchronousFileChannel.open(Paths.get(filePath), StandardOpenOption.WRITE);
        asyncChannel.write(buffer, bytes);

        buffer.clear();
    }
}