# Lab4

Initially, for lab 3, I created 2 pages: students.xhtml and exams.xhtml where you could add students/exams and see all students and all exams.

For the lab 4, I refactored a bit and now the main pages are the examsMain.xhtml and studentsMain.xhtml which only use the template="page.xhtml"
and send different params depending on what we what to see: students or exams.

I used a poll in order to display at the top of the page all the subject the students have exams on.
Each item is displayed in page at 1 sec interval.

I created a composite component autoComplete.xhtml where you can select an exam with the autocomplete from Primefaces.

The page.xhtml template includes more components: 

- the header(header.xhtml) which contains:
	- a title of the page: Students or Exams
	- an option where you can choose the language of the page: English or Italian
	- a menu bar(menuBar.xhtml) which which we can select the page we want to see: the students or exams page

- the content which is made out of 2 other components:
	- dataView.xhtml file which contains a generic datatable which receives the items he has to iterate through together with the displayed headers.
		For an exam you can see the id, name, starting tie and duration of the exam and for the student you cna see the id, name and the list of exams he has to take.
	- dataEdit.xhtml file contains a button which opens a dialog with a generic form where you can add a student/exam depending on what parameters you get
		For adding an exam you must enter a name, the duration in minutes of the exam and the starting time in a specific format
		(yyyy/MM/dd HH:mm:ss - validations were made) and for adding a student you must enter his name and a list of exams he has to take(the ids of the exam: 1,2,3)

- the footer(footer.xhtml) where we can see copy right details

For ui elements I used the Primefaces components together with primefaces-extentions and basic facelet and html elements.

For connecting to the database, I used the MySQL Database and I configured a connection pool and a JDBC resource directly on the Glassfish portal.
I also set a jndi in the configuration and used the DataSource in the coude for searching the connection.