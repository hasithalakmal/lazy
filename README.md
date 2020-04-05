# What is lazy
Lazy is a tool based on meta-modeling to implement API testing. In version 1.0.0 include only java code base API testing framework
. API test suite can be initiated within a few minutes using the lazy framework. 

# Why Lazy is Special from other frameworks
1. Hierarchical approach
2. Motivates for code reusability and modularization
3. Empower with template engine facility
4. Simple configurations
5. Improve test case implementation efficiency
6. Powerful assertion methods
7. Empower with pre-actions and post-action
8. Powerful environment and global variable defining
9. Enable/disable/override assertion executions within the hierarchical approach
10. Comprehensive result analysis

# Hierarchical approach
This is a main feature that Lazy framework has facilitated. We can create a Lazy suite and we can add multiple test suites to the lazy suite. Then
 we can define multiple test scenarios for each test suites. Then we can define different test cases for each test scenarios. Then we can add any
  number of API calls for each test case. 
  
It starts execution from the lazy suite and it select first test suite. Then it execute that test suite
   by executing first test scenario. Then it select first test case and execute it, then it executes api calls sequentially which are attached to
    that API call. Like wise it executes top to bottom manner. 
    
**The impotency of the above hierarchical manner is we can define any value or action at any level and it will apply for it's sub tree.** 

Please refer the following diagram for the explanation. I'm using very simple student management system to explain the scenarios furthermore. 
 
 <img src="docs/images/test-hierarchy.png" width="100%"  alt="Hierarchy"/>
 
Let's assume all APIs are working with HTTPS and host is smile24es.com. Then we can define protocol as HTTPS and host as localhost in the lazy site
 level. Then it will use for all API calls. Like wise let's assume class API context path is class API. Then we can define it on the class-API test
  suite level. Then it will apply only to the class API test suite subtree. Like that we can configure student-api context path on the student api
   test suite. **According to your requirement you can configure values on any level of your lazy suite and it will be automatically apply to it's
    sub tree.** 
   
Let's assume we need to implement a test scenario for assign student to class. Then inside the student API we need to create a class as well. Then
 we have to use different context path to create class. On that scenario can we define student-api context path on the student API test suite level
 ? Yes. **Lazy provide facility to override values at any point.** Then you can change create class API call context path on the API call level. 
 
## What can define using hierarchical approach


# Up coming features
1. Architectural documentation
1. Application skeleton modeler
1. Swagger annotation modeler
1. Unit test generation framework

# Standards
1. Using cutting edge technologies
1. Java Doc
1. Modularized implementation with suitable design patterns
1. Well designed extensible and pluggable architecture 
1. Unit tests
1. Standard static code analysis
