# Lab2

The structure of the project is as follows: in the com.records package we have more packages for each component:

- controller: which knows which page to display to the user and controls the flow, knowing which service to call

- dao: which is responsible with communicating directly with the database through the EntityManager

- entity:  which gives shape to the actual table from the db

- filter: which helps with manipulating the requests and responses, before and after they have been proccessed

- listener: which helps us initialize the application context

- service: which exposes the dao functionalities (doesn't communicate directly with the db)

- wrapper: which helps us catching the response so that we can manipulate it

I created 3 jsp pages:

1. input.jsp - the page where you can set the category, key and value and add a new record to the database.

2. result.jsp - the page where you can see all records added until then

3. createRecord.jsp - the page where you are redirected after you added a record