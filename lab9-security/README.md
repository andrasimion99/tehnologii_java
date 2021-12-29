# Lab9 - security part

In lab9-security project I refactored the project from the previous lab and added security for its componenets. 

First of all, I added authentication using the JDBC Realm. The configurations are in web.xml where I defined the 2 roles from the application: the admin and the user and the authentication method which will be through a form login. Moreover I added some security constraints:
- all urls containing admin/* will be available only for the users logged in as admin. For this I created a separated folder called admin, where I placed all the files for the admin role.
- all urls containing users/* will be available only for the users logged in as normal user. For this I created a separated folder called users, where I placed all the pages for the normal user role.
- all urls containing public/* will be available for any kind of user. For this I created a separated folder called public, where I placed all the files that should be public.

If a restriction is violated, the status code 403 will be send and the /forbidden.xhtml page will be shown.

I've also secured the DocumentService using annotation like @RolesAllowed("admin") or injecting the @Context and returning 403 in case the role of the user is the expected one.