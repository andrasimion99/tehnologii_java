# Lab5
I refactored the previous lab such that now I use a persistence unit with a non jta data source.
Using the Entity Manager I managed to implement the CRUD operations for the student entity.
I've also created unit tests for these operations on controller and on the dao class.
I only used JPA annotation and JPQL for creating queries to the database.

I also used inheritance mapping suchan that now 2 kinds of exams exists: the writing tests which can can a number of questions 
and a project which can have a number of slides.
I changed the interface such that now I can add a project, a writing exam, see them separtely and in the student section
you will see what kind of exam each student has.