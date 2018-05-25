# HDip in Computer Science - WIT, Web Development - Assignment 2

## A web-based app to periodically track assessments on individual members

### Versioning:

I used SourceTree UI to control my local and remote GitHub Repository.

### Comments:

All requirements met with exception of trend calculation.

| Grade Range | Requirements | Complete |
| ----------- | ------------ | -------- |
| Starter | Test assessments loaded from yaml file consisting of weight, chest, thigh, upper arm, waist, hips; List of all assessments | OK |
| Baseline | Member model: name, email, password, address, gender, height, startingweight. Single member loaded from yaml; Multiple Assessments associated with single user in yaml file. Form to add more assessments in UX; +Current BMI | OK |
| Good | Signup + Login forms allowing new members to singup; Dashboard shows assessments for logged in user; +BMI Category; Deployed | OK | 
| Excellent | Account Settings View allowing user to change details; includes comment field; Trainer Accounts (preloaded from yaml). Trainer logs in and can see member list. Trainer can then see assessments for a user and can comment on an assessment; +ideal Body Weight; basic git repo; utility | OK |
| Outstanding | Members can delete individual assessments; Date/Time for each assessment. Assessment always listed in reverse; Trainers can delete any user; +Trend via simple red/ green label; git repo with version history; utility+ | OK |

### Resources:

- I re-used class design from Programming Assignment where Member and Trainer objects inherit from Person class. I removed unnecessary fields and methods to adapt the code to the requirements. 

- I followed the below example to persist Shared Person parent class properties in a database. The Person class in marked with @MappedSuperclass Annotation
  - https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/mapped-super-class.html

- I reviewed the below document to see how to persist Java Date object in a database
  - http://www.h2database.com/html/datatypes.html

- I am sorting Assessments by Date using lambda expression and Collections.sort
  - https://codereview.stackexchange.com/questions/117757/custom-sorting-of-dates

- I included multiple if else statements inside the templates to display different content e.g. different color for ideal body indicator: green, red or black
  - https://www.playframework.com/documentation/1.4.x/cheatsheet/templates

- I tried to explore and include different types of elements from the Semantic UI, examples:
  - Semantic statistic element to display member details
  - https://semantic-ui.com/views/statistic.html
  - Semantic simple dropdown menu that can open without Javascript
  - https://semantic-ui.com/modules/dropdown.html#simple
  - Semantic list to display all registered members
  - https://semantic-ui.com/elements/list.html

- I installed google-java-format plugin in IntelliJ and reformatted the code to align with Google Java Style Guide.
  - https://google.github.io/styleguide/javaguide.html
  - https://github.com/google/google-java-format


