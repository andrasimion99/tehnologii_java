package com.records.wrapper;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseWrapper extends HttpServletResponseWrapper {
    private final StringWriter output;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response the {@link HttpServletResponse} to be wrapped.
     * @throws IllegalArgumentException if the response is null
     */
    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new StringWriter();
    }
    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(output);
    }
    @Override
    public String toString() {
        return output.toString();
    }
}
