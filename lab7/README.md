# Lab7
For this laboratory I created a new JSF application for subbmiting documents. The application has more pages:
- *Register page*, where you setup a username which must be unique, a password and the role of the account. You must choo se a unique username and a password of minimum 4 characters. After registering successfully you will be redirected to the login page.
- *Login page*, where you can login with an existing account. You will see error messages in case the account doesn't exist or in case you entered wrong credentials. After the login is successful you will be redirected to the home page which will render different stuff depending on the type of account you logged in(admin or author)
- *Home page* has 2 different forms:
    - *admin view*, the admin can see all submitted documents in a table
    - *author view*, the author sees a button on which he can choose a file from his computer and a button to submit it. If the submission is done outside the registration hours, a message will be displayed and the submission will not be saved.

After the user is logged in, he wil not see the login and register buttons anymore, instead he will see a logout button. When the user logs in a cookie with his account data is saved in the client side and when he presses the logout button, the cookie will be removed.

For implementing the described application I used multiple beans:
- *UserDaoImpl* and *DocumentDaoImpl* which represent Transactional classes which comunicate with the db extending the generic implementation from *GenericDaoImpl<>* 
- *UserController* and *DocumentController* which represent session beans which make use of the dao classes injecting them with the @Inject annotation and expose methods to be used in the jsf pages.
- *UserBean* and *DocumentBean* representing a request scoped bean also used to help communication between view pages and the backend entities *User* and *Document*. 

I used a @Produces annotation in RegistrationGenerator bean for automatically generating a registration number for each submitted document. Then I used it using the @Inject annotation.


I used @Interceptor annotation for writing into a submissions.txt file into lab7 folder form the gassfish server, all the documents that have been submitted. Firstly, I created a custom annotation @Logged which binds the interceptor LoggedInterceptor with the method or class we want to intercept. Then, I used this interceptor on the create method from the DocumentDao class so that each time a document is created it will be written into the file.

I used a @Decorator annotation for defining the business logic of creating a document within a specified time frame. The TimeLimitDecorator is executed right before the actual save into the database and if the condition is not pass, it will throw an exception.

I used the @Observes annotation so that we used an event based communication when the list of documents needs to be updated. I used it in DocumentController class and I fired the event when
a document is saved so that we initilize the documents field with null. Then when we need to access the field it will be updated with all documents from the database.

I used bean validations in the UserBean class on the username, when the user registers or logs in, it shouldn't be null and it should be maximum 50 characters and on the password field which should have at least 4 characters. I also set personalised messages which will be displayed in the interface when this conditions are not passed.