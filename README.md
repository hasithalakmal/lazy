# What is lazy
Lazy is a tool based on meta-modeling to implement API testing. In version 1.0.0 include only java code base API testing framework
. API test suite can be initiated within a few minutes using the lazy framework. 

# Why Lazy is Special from other frameworks
1. Hierarchical approach
2. Motivates for code reusability and modularization
3. Empower with template engine facility
4. Simple configurations and high user friendliness
5. Improve test case implementation efficiency
6. Powerful assertion methods
7. Empower with pre-actions and post-action
8. Powerful environment and global variable defining
9. Enable/disable/override assertion executions within the hierarchical approach
10. Comprehensive result analysis
11. Execute any sub tree of the lazy tree. In other words, you can execute test suite, test scenario, test cases, api call independently 
12. Test case grouping and group execution

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
 
To provide above functionality lazy framework facilitate something called **stack**. If you configure a value on stack on some level that value will be
 used only to it's sub tree. That value will be removed after complete the execution of that sub tree. Above mentioned values should be configured
  on that stack.
 
## What can define using hierarchical approach
You can configure lot of things with this hierarchical approach. Please refer to the following list to get idea about that. Also please note that
 bold values are provide as default values of those attributes.
1. Protocol - (**http**/Https)
2. Host - (**localhost**/smile24es.com)
3. Port - (**8080**)
4. Context-path - (**lazy-api**/student-api)
5. HTTP method - (**GET**/POST)
6. Header group - (**"accept:application/json"/"content-type:application/json"**)
7. Environment variables as key value pare - (username:lazy-user)
8. Default assertion group (You can configure any assertion with this. Ex: you can assert response time as a default assertion group and identify
 performance issues from the beginning. If you can configure this on top levels your applications will monitor automatically).
 
 Any of above value can be override any of above configured value at any level. Additionally Lazy provides enable/disable facility for default
  assertions. As a example if you need to skip response time assertion for one API call you can configure it very easily.

# Ready to use these Features
1. Lazy Language - DSL implementation to improve user friendliness
2. Web UI to create your test suite visually. 
3. Improve test scenario coverage by auto generated test values  


# Why you should trust us?
1. Using cutting edge technologies
2. Comprehensive Java Documentation
3. Modularized implementation with suitable design patterns
4. Well designed extensible and pluggable architecture 
5. More than 80% unit test coverage
6. Standardized with static code analysis
7. Highly extendable with meta-model based design

<Hr>

# How you can start with Lazy?
It's only two steps
1. You have to start spring-boot application with java11 or above. 
2. Then add lazy dependency.


```$xslt
<dependency>
    <groupId>com.smile24es</groupId>
    <artifactId>lazy-core</artifactId>
    <version>{version-number}</version>
</dependency>
```

# Proposed Project structure
```$xslt
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── smile24es
    │   │           └── lazy
    │   │               └── sample
    │   │                   ├── SampleLazySuite1.java
    │   │                   ├── suites
    │   │                   │   └── AccountApiTestSuite.java
    │   │                   ├── scenarios
    │   │                   │   └── CreateAccountTestScenario.java
    │   │                   ├── testcase
    │   │                   │   └── CreateAccountSuccessTestCase.java
    │   │                   ├── apicall
    │   │                   │   └── AccountApiCalls.java
    │   │                   └── dto 
    │   │                       └── AccountSetting.java
    │   └── resources
    │       ├── application.properties
    │       ├── logback.xml
    │       └── request-body
    │           └── account-api
    │               ├── create-account
    │               │   └── create-simple-account.json
    │               └── templates
    │                   └── create-account-request-body-template.ftl
    └── test
        ├── java
        │   └── com
        │       └── smile24es
        │           └── lazy
        │               └── sample
        │                   └── LazyManagerTest.java
        └── resources

```
We are suggested to create separate packages for each level of the hierarchy. That modularization will help to manage your project. Also please
 note that this structure has not mandatory.  
 
# How to define a lazy suite?
```$xslt
LazySuite lazySuite = new LazySuite("Lazy Test Suite", lazySuiteStack);
```

# How to define a test suite?
```$xslt
TestSuite testSuite1 = new TestSuite("Student-API test suite");
```

# How to define a test scenario
```$xslt
TestScenario testScenario1 = new TestScenario("Create student with minimum data");
```

# How to define a test case
```$xslt
TestCase testCase1 = new TestCase("Create Account successfully - 1");
```

# How to change stack values
As mentioned under the hierarchical approach explanation, you can configure different attribute values using stack. Let's see how we can configure
 the values using stack. Stack contains three main attributes; those are
    1. Default values
    2. Default Assertion rule list
    3. Global environment
    
Let's consider how to change stack default values. Stack default values contains protocol, hostName, port, contextPath, httpMethod, default
 headerGroup values. Each hierarchy object (Test suite/ Scenario/ Case/ API call) contains stack. We have to get it's stack and it's default value
  object and change whatever value as we wish. As a example let's see how we can change context-path.
 
```$xslt
lazySuite.getStack().getDefaultValues().setContextPath("student-api"); //On lazy suite (root) level
testSuite.getStack().getDefaultValues().setContextPath("student-api"); //On Test suite level
testScenario.getStack().getDefaultValues().setContextPath("student-api"); //On Test scenario level
testCase.getStack().getDefaultValues().setContextPath("student-api"); //On Test case level
apiCall.getStack().getDefaultValues().setContextPath("student-api"); //On Api call level
```
