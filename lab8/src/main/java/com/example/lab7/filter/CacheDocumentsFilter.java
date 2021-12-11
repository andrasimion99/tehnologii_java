package com.example.lab7.filter;

import com.example.lab7.entity.Document;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.primefaces.shaded.json.JSONArray;
//import org.primefaces.shaded.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.faces.bean.ApplicationScoped;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Provider
@ApplicationScoped
public class CacheDocumentsFilter implements ClientResponseFilter {
    private List<Document> documentList = new ArrayList<>();

    @Override
    public void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) throws IOException {
        System.out.println(documentList.isEmpty());
        if (clientRequestContext.getMethod().equalsIgnoreCase("GET")) {
            System.out.println("filter pt GET");
            if (documentList.isEmpty()) {
                InputStream entityStream = clientResponseContext.getEntityStream();
                if (entityStream != null) {

                    byte[] bytes = new byte[entityStream.available()];
                    entityStream.read(bytes);
                    final ObjectMapper objectMapper = new ObjectMapper();
                    documentList = objectMapper.readValue(new String(bytes), new TypeReference<List<Document>>() {
                    });
                    System.out.println(documentList.isEmpty());
                }
                return;
            }
            Response response = Response.ok(documentList).build();
            clientRequestContext.abortWith(response);
        }
        if (clientRequestContext.getMethod().equalsIgnoreCase("POST")){
            System.out.println("filter pt POST");
            documentList.clear();
        }
    }
}
