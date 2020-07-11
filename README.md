### Practical Project Specification: Hobby Web Application (HWA)

The application is a Daily Planner where a user is able to: 

* Save their username and password to the database
* Create a new task by entering a task date, time, name and location
* View all the tasks
* Edit a task
* Remove a task

##### The deliverable:

1. I created a Jira board with full expansion on user stories and tasks needed to complete the project.
2. I set up a remote database on MySQL Workbench and made sure the ERD for the project was present in the root of the project repository.
3. I then also hard coded SQL CRUD statements for tasks and users and made sure they worked as expected.
4. Sensible package structure in the front and back-end
5. Added a method to take in user input and send the information to the database.
6. CRUD functionality following the Enterprise Architecture Model.
7. Adherence to best practice (e.g. OOP principles, SOLID, refactoring)
8. Unit and integration testing for the project back-end.
9. Integration testing for the project front-end.
10. I have reports for tests in the root of the project repository.

Finally I have a running, working and tested application which takes in user input and can create, read, update and delete tasks in the Daily Planner. 

### Prerequisites

```
Version Control System: Git
Source Code Management: GitHub
Kanban Board: Jira 
Database Management System: GCP instance of MySQL Server
Back-End Programming Language: Java
API Development Platform: Spring 
Front-End Web Technologies: HTML, CSS, JavaScript
Build Tool: Maven
Unit Testing: JUnit, Mockito
Static Analysis: SonarQube
Test Reporting: Surefire

```
### Installing

```
git clone git@github.com:misstaevere/DailyPlannerProject.git
cd DailyPlannerProject/
mvn package
java -jar Kat-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## Authors

* **Kärt Taevere** - *Initial work* - [misstaevere](https://github.com/misstaevere)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Thanks to our very patient QA trainer Jordan Harrison, sorry you had to push back your book week because of us!
* Yay for all the fabulous girls in our cohort, GIRL POWER!