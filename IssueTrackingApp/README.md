The Issue Tracking application is the pure way to show how we can easily implement CQRS & ES patterns using Axon Framework.
It is very good choice for the peoples, Those who want to understand What is CQRS and how to segregate Commands & Query.
Basically here you can see the two applications named issue-tracking & issue-tracking-query. 
issue-tracking app handling command part and issue-tracking-query app handling query part independently. 

Prerequisites for executing the example : 
1. RabbitMQ - Your system must have RabbitMQ installed and running with default port and guest user.
2. MySql - Your system must have MySql installed running on default port.

Calling Services Sample: 
1. Raise Issue - 
  URL - http://localhost:9000/Issue/raiseIssue
  <br>Request Method - POST
  <br>Payload - { "title" : "Text field is not working", "description" : "When I put exact max length chars, The entire page will crash", "severity" : 3, "priority" : "M" }
  
2. Update Issue - 
  <br>URL - http://localhost:9000/Issue/update
  <br>Request Method - POST
  <br>Payload - { "issueId" : "dbac5917-05d5-494f-90f4-a6e79a4d4517", "severity" : 2 }
  <br>[Note : "issueId" : "dbac5917-05d5-494f-90f4-a6e79a4d4517" will vary according to saved issue id]
  
3. All Issue details - 
  <br>URL - http://localhost:9020/Issue/findAll
  <br>Request Method - GET
  
4. Find One Issue - 
  <br>URL - http://localhost:9020/Issue/findById/dbac5917-05d5-494f-90f4-a6e79a4d4517
  <br>Request Method - GET
  <br>[Note : "issueId" : "dbac5917-05d5-494f-90f4-a6e79a4d4517" will vary according to saved issue id]
