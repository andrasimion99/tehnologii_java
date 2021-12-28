package com.example.documents;

import com.example.documents.dao.DocumentDaoImpl;
import com.example.documents.entity.Document;
import io.swagger.annotations.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 *
 */
@Api(value = "/documents")
@Path("/documents")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class DocumentController {
    @Inject
    DocumentDaoImpl documentDaoImpl;


    @GET
    @Path("/{id}")
    @ApiOperation(value = "Finds document by id",
            response = Document.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved document"),
            @ApiResponse(code = 404, message = "Document not found")})
    public Response ViewDocumentService(@PathParam("id") int id) {
        Document doc = documentDaoImpl.get(id);
        if (doc == null)
            return Response.status(404).build();
        return Response.ok(doc).build();
    }

    @GET
    @ApiOperation(value = "Finds all documents",
            response = Document.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all documents")})
    public Response ViewDocumentsService() {
        System.out.println("getting all documents");
        return Response.ok(documentDaoImpl.getAll("Document.findAll")).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA})
    @ApiOperation(value = "Creates a document",
            response = Document.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the document. The location is in headers."),
            @ApiResponse(code = 500, message = "Document couldn't be saved.")})
    public Response AddDocumentService(Document doc) {
        try {
            URI uri = new URI("documents/" + documentDaoImpl.create(doc).getDocId());
            return Response.created(uri).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Updates a document")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully updated the document"),
            @ApiResponse(code = 404, message = "Document not found")})
    public Response UpdateDocumentService(@PathParam("id") int id, Document doc) {
        Document foundDoc = documentDaoImpl.get(id);
        if (foundDoc == null)
            return Response.status(404).build();
        if (doc.getRegistration() == null)
            doc.setRegistration(foundDoc.getRegistration());
        if (doc.getFilename() == null)
            doc.setFilename(foundDoc.getFilename());
        if (doc.getContent() == null)
            doc.setContent(foundDoc.getContent());
        if (doc.getUserId() == null)
            doc.setUserId(foundDoc.getUserId());
        doc.setDocId(id);
        documentDaoImpl.update(doc);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Deletes a document")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted the document"),
            @ApiResponse(code = 404, message = "Document not found")})
    public Response DeleteDocumentService(@PathParam("id") int id) {
        Document foundDoc = documentDaoImpl.get(id);
        if (foundDoc == null)
            return Response.status(404).build();
        documentDaoImpl.delete(id);
        return Response.noContent().build();
    }
}
