# Lab1
I created a jsp file with a form which communicates with a servlet which accepts GET requests. 

When the get request is processed, the server logger will log info about 
the method name, the ip address of the client, the user-agent, the language and each query param with its value. 

After that, depending on the user agent the request will send either a html response either a plain text tesponse.

The response is formed as follows: if mock is true a simple message is displayed, otherwise if mock is false we are taking into consideration the sync value as follows:
if the sync value is true the servlet writes in a text file called repository a line containing the key, repeated value times, along with the timestamp of the request,
and if it's false the writing it's done asynchronous with the help of a channel:AsynchronousFileChannel and a buffer in which we write our content first.

After the writing is done, the content of the file will be displayed and ordered by it's key. This is done by using a TreeSet which is a sortedSet, 
in which we add each line as an element and this way the content will be returned in order everytime.
