# Lab6

For this laboratory, I rewrited the data access layer so that now we use a JTA data source. I created an AbstractDao class with a generic type which implements the CRUD operations. The enititymanager is injected in the dao using the @PersistenceContext annotation and it is used without needing to call explicity the begin and end of the transaction.

Moreover, I added a new table for resources. A resource can be of 2 types: ROOM or VIDEOPROJECTOR and have a name. An exam can have one or more resources allocated. A resource cannot be allocated to 2 exams that happen simultaneously. I changed the interface so that now you can see all the exams, and all the resource and you can make a reservation for an exam to use some resources. In case the resource is already booked for another exam in the same period of time, the reservation will not take place.

For this logic I created the following beans:
- *ResourceDao* a stateless bean that extends the AbstractDao and implements method for the availability of a resource.
- *ReservationController* a stateful bean which make uses of the dao which is injected with the @EJB annotation. The bean exposes methods with which you can assign some resources to an exam. The assignment is transactional, that means that either all resources are added to the exam, or no resource is added.
- *AssignmentController* a singleton bean that keeps in memory a map with each exam and its resources. The map is instantiated on Startup and updated each time a new reservation is made.