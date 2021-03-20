# TODOList
# Refer the "OnlineTODOList_application_manual.doc" document placed in the repository.
TODOList web application 
1.	Online TODO List web application

•	This application was developed using Spring boot framework with built in H2 database and Tomcat server.
•	To login into the application, there are few predefined users created and user/password are mentioned below.
User1/User1
User2/User2
User3/User3
Password is hardcoded with encrypted format using BCryptPasswordEncoder .
Example is in the source code (SecuredPasswordGenerator.java)
•	User can add/delete their task. 
•	User can able to track his/her tasks along with latest changes with date updated.
•	Also, user can mark their tasks. 

2.	Application functionality

•	Home screen: (http://localhost:8080/)
 

•	Login screen: (http://localhost:8080/login)

•	Welcome screen: (http://localhost:8080/welcome)
 
•	   Get All tasks: By clicking the Get All Tasks button.

If there are no tasks created an empty task list will be displayed.
            
•	   Add Task : By clicking the Add Task link

 
•	User can Edit and Delete the task.
•	User can also mark their task by clicking check and uncheck box. Marking check and uncheck are auto saved. 

 
•	Logout : User can logout by clicking logout button.

 

3.	Test cases
•	Unit test cases implemented for both the Service and Controller.


 
