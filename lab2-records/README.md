# Lab2

The application allows you to added new records to the database and see all the records. It is integrated with a mysql database, using JPA.

The structure of the project is as follows: in the com.records package we have more packages for each component:

- controller: which knows which page to display to the user and controls the flow, knowing which service to call

- dao: which is responsible with communicating directly with the database through the EntityManager

- entity:  which gives shape to the actual table from the db

- filter: which helps with manipulating the requests and responses, before and after they have been proccessed. Here we have 2 filters, 
one that logs all requests to the /InputServlet which redirects you to the input.jsp page 
and one which modifies the html page so that it adds a message in the beginging and at the end of it.

- listener: which helps us initialize the application context. I created a listener that initialize an attribute with application scope
which is used for setting a default category.

- service: which exposes the dao functionalities (doesn't communicate directly with the db)

- wrapper: which helps us catching the response so that we can manipulate it

Also, in the webapp, I created 3 jsp pages:

1. input.jsp - the page where you can set the category, key and value and add a new record to the database. The categories are dinamically read from a json file from the resource folder.

2. result.jsp - the page where you can see all records added until then

3. createRecord.jsp - the page where you are redirected after you added a record

Also, a cookie is set whenever a new records is added so that when the user wants to add a new record the default category shown 
will be the one restored from the cookie. 