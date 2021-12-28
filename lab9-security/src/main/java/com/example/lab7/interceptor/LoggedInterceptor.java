package com.example.lab7.interceptor;

import com.example.lab7.entity.Document;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;

@Logged
@Interceptor
public class LoggedInterceptor implements Serializable {
    private String path = "../lab7/submissions.txt";

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext) throws Exception {
        String methodName = invocationContext.getMethod().getName();
        if (methodName.equals("create") && invocationContext.getParameters()[0] instanceof Document) {
            Document doc = (Document) invocationContext.getParameters()[0];
            System.out.println(doc);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.append(doc.toString());
            writer.append('\n');
            writer.close();
        }

        return invocationContext.proceed();
    }

}
