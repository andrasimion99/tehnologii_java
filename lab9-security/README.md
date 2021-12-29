# Lab8

For this laboratory I created a restful web service with implements CRUD operations for the document entity. The service is in *DocumentService* class and has the following methods:
- ViewDocumentService which returns the document by a given id
- ViewDocumentsService which takes as paramter and id which represents the id of an user and returns all the documents subitted by that specific user
- ViewDocumentsService which doesn't take any parameters returns the list of all documents
- AddDocumentService which adds a document given in the body
- UpdateDocumentService which updates a document. The id of the document we want to change is given in the path and the properties we want to replace are given in the body
- DeleteDocumentService which deletes a document by a given id.

I've modified the *DocumentController* to use the seb services just created instaed of directly using the dao class. The service works with the dao for implementation. This was I tested that everything was working as before.

Morever, I added support for the documentation using the swagger package. The documentation can be found at http://localhost:8080/lab7-1.0-SNAPSHOT/resources/swagger.json then you can copy the generated json and post it in an online editor like https://editor.swagger.io/ where you will see al the information about the api. I added information about each method, a short description, details about the request and response type so that is more specific and the status codes that should be returned on specific situations.

I also created a filter *CacheDocumentsFilter* which works as a cache for the list of documents. When a request is made, the list from the cache is verified to see if it is empty and if so it will get the response from the request, otherwise it wil directly return the existent list. When a document is created the list becomes null so that next time the list is needed it will be updated with all the documents from the database.