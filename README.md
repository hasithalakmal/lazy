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


```java
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
 
```java
lazySuite.getStack().getDefaultValues().setContextPath("student-api"); //On lazy suite (root) level
testSuite.getStack().getDefaultValues().setContextPath("student-api"); //On Test suite level
testScenario.getStack().getDefaultValues().setContextPath("student-api"); //On Test scenario level
testCase.getStack().getDefaultValues().setContextPath("student-api"); //On Test case level
apiCall.getStack().getDefaultValues().setContextPath("student-api"); //On Api call level
```

Like that **you can configure values on any level by using that level stack.** 

## 1. configure stack default values
Following example shows how you can change different values for stack default values

```java
DefaultValues defaultValues = testSuite1.getStack().getDefaultValues();
defaultValues.setHttpMethod("https");
defaultValues.setHostName("localhost");
defaultValues.setPort(8080);
defaultValues.setContextPath("student-api");
defaultValues.setHttpMethod("POST");

HeaderGroup headerGroup = new HeaderGroup("Sample header group");
Header header1 = new Header("Accept Header", "accept", "application/json");
Header header2 = new Header("Content type Header", "content-type", "application/json");
headerGroup.getHeaders().add(header1);
headerGroup.getHeaders().add(header2);
defaultValues.setHeaderGroup(headerGroup);

```

## 2. configure stack default assertion rule list
Following example shows how you can define default  assertion rule list in the stack

```java
AssertionRuleGroup defaultCreateAssertionGroup = new AssertionRuleGroup("Test scenario assertion group");
List<AssertionRule> assertionRules = defaultCreateAssertionGroup.getAssertionRules();
assertionRules.add(Assert.responseTimeAssertionGreaterThanGivenMilliSeconds("2000"));
assertionRules.add(Assert.responseCodeAssertion("201"));
assertionRules.add(Assert.responseBodyNotNull());
testScenario1.getStack().addDefaultAssertionGroup(defaultCreateAssertionGroup);
```


## 3. configure stack global values
This feature is still under development

# Link the Dots (How to build a lazy test suite)
## Define API Call
```java
public class AccountApiCall {

    public static ApiCall getAccountApiCall() {
        ApiCall apiCall2 = new ApiCall("Get Account by Id");
        apiCall2.setUri("service/accounts/1000");
        apiCall2.addAssertionRule(Assert.responseCodeAssertion("200"));
        apiCall2.addAssertionRule(Assert.notNullBodyValueAssertion("$['accountName']"));
        apiCall2.addAssertionRule(Assert.equalBodyValueAssertion("$['accountId']", "1000"));
        apiCall2.addAssertionRule(Assert.equalBodyValueAssertion("$['accountName']", "Lazy Account"));
        return apiCall2;
    }
}
```
## Define Test case and attach api call to test case
```java
public class GetAccountTestCases {

    public static TestCase getCreateAccountTestCase() throws LazyCoreException {
        TestCase testCase1 = new TestCase("Create Account successfully - 1");
        testCase1.getApiCalls().add(AccountApiCall.getAccountApiCall());
        return testCase1;
    }
}
```

## Define Test scenario and attach test case to test scenario
````java
public class GetAccountTestScenarios {

    public static TestScenario getAccountTestScenario() throws LazyCoreException {
        TestScenario testScenario1 = new TestScenario("get Account");
        testScenario1.getTestCases().add(GetAccountTestCases.getCreateAccountTestCase());
        return testScenario1;
    }
}
````

## Define Test suite and attach test scenario to test suite
```java
public class AccountTestSuite {

    public static com.smile24es.lazy.beans.suite.TestSuite getAccountApiTestSuite() throws LazyCoreException {
        TestSuite testSuite1 = new TestSuite("Account Test Suite");
        testSuite1.getStack().getDefaultValues().setContextPath("account-api"); //We are configuring context path
        testSuite1.getTestScenarios().add(GetAccountTestScenarios.getAccountTestScenario());
        return testSuite1;
    }
}
```

## Define Lazy suite and attach test suites to lazy suite
```java
public class SmileLazySuite {
    public static LazySuite populateSampleLazySuite() throws LazyCoreException {
        LazySuite lazySuite = new LazySuite("Sample lazy suite");
        lazySuite.getTestSuites().add(AccountTestSuite.getAccountApiTestSuite());
        return lazySuite;
    }
}
```

**Recommend to do all above implementations under the src directory and enable logs.**

#Execute test suite
**Define Test class to execute under the test directory**
```java
@SpringBootTest(classes = LazyApplication.class)
public class Sample0Executor {

    @Autowired
    private LazyManager lazyManager;

    @Test
    public void executeSampleLazySuite() {
        try {
            LazyExecutionData results = lazyManager.executeLazySuite(SmileLazySuite0.populateSampleLazySuite());
            Assert.assertNotNull(results);
        } catch (Exception ex) {
            Assert.fail("Success scenarios should not be failed", ex);
        }
    }
}
```

#Execute and view log file
When you execute your lazy suite, you can get a comprehensive log about the test execution. 
```less
2020-04-07 01:30:49,970 INFO com.smile24es.lazy.suite.sample0.Sample0Executor [main] Starting Sample0Executor on Sysco with PID 29443 (started by hasithag in /home/hasithag/Hasitha/Personal/GIT/lazy/core)
2020-04-07 01:30:49,982 INFO com.smile24es.lazy.suite.sample0.Sample0Executor [main] No active profile set, falling back to default profiles: default
2020-04-07 01:30:51,926 INFO com.smile24es.lazy.suite.sample0.Sample0Executor [main] Started Sample0Executor in 2.746 seconds (JVM running for 4.432)
2020-04-07 01:30:52,210 INFO com.smile24es.lazy.manager.impl.LazyManagerImpl [main] Ready to execute lazy suite - [Sample lazy suite]
2020-04-07 01:30:52,240 INFO com.smile24es.lazy.manager.impl.TestSuiteManagerImpl [main] Ready to execute test suite - [1] - [Account Test Suite]
2020-04-07 01:30:52,245 INFO com.smile24es.lazy.manager.impl.TestScenarioManagerImpl [main] Executing test scenario - [1] - [get Account]
2020-04-07 01:30:52,248 INFO com.smile24es.lazy.manager.impl.TestCaseManagerImpl [main] Executing test case - [1] - [Create Account successfully - 1]
2020-04-07 01:30:52,251 INFO com.smile24es.lazy.manager.impl.ApiCallManagerImpl [main] Executing api call - [1] - [Get Account by Id]
2020-04-07 01:30:52,495 INFO com.smile24es.lazy.manager.handlers.ApiCallHandlerImpl [main] 

----------------------------------------------------------------- 
Executing api call [1] - [Get Account by Id] 
----------------------------------------------------------------- 
Http Method         : [GET]
Request URL         : [http://localhost:8080/account-api/service/accounts/1000]
Header List         :
                     - [accept:application/json]
                     - [content-type:application/json]
-----------------------------------------------------------------
Execution time      : [33] milli seconds
HTTP status code    : [200]
Response            : [{"status":"ACTIVE","createdBy":"12345","createdTimestamp":1586112248464,"updatedTimestamp":0,"accountId":"1000","parentId":"1","enterpriseId":"1","accountName":"Sathara-1577641690","ownerName":"Hasitha-1577641690","versionId":"1.0.0","addresses":[],"settings":[]}]
-----------------------------------------------------------------


2020-04-07 01:30:52,495 INFO com.smile24es.lazy.manager.impl.ApiCallManagerImpl [main] Executed api call - [1] - [Get Account by Id]
2020-04-07 01:30:52,531 INFO com.smile24es.lazy.manager.impl.ApiCallManagerImpl [main] Completed execution of api call - [1] - [Get Account by Id]
2020-04-07 01:30:52,531 INFO com.smile24es.lazy.manager.impl.TestCaseManagerImpl [main] Executed test case - [1] - [Create Account successfully - 1]
2020-04-07 01:30:52,531 INFO com.smile24es.lazy.manager.impl.TestCaseManagerImpl [main] Executed all test cases...
2020-04-07 01:30:52,531 INFO com.smile24es.lazy.manager.impl.TestScenarioManagerImpl [main] Executed test scenario - [1] - [get Account]
2020-04-07 01:30:52,531 INFO com.smile24es.lazy.manager.impl.TestSuiteManagerImpl [main] Executed test suite - [1] - [Account Test Suite]
2020-04-07 01:30:52,531 INFO com.smile24es.lazy.manager.impl.LazyManagerImpl [main] Executed lazy suite - [Sample lazy suite]
2020-04-07 01:30:52,538 INFO com.smile24es.lazy.manager.impl.LazyBaseManager [main] 
+-----------+-------------------+---------------------------------------------------------------+------------------+---------+
| Result Id | API Call Name     | Assertion Name                                                | Execution Status | Is Pass |
+-----------+-------------------+---------------------------------------------------------------+------------------+---------+
| 1         | Get Account by Id | Response code assertion for - [200]                           | EXECUTED         | true    |
| 2         | Get Account by Id | Body value not null assertion                                 | EXECUTED         | true    |
| 3         | Get Account by Id | Response body value assertion $['accountId'] = 100001         | EXECUTED         | false   |
| 4         | Get Account by Id | Response body value assertion $['accountName'] = Lazy Account | EXECUTED         | false   |
+-----------+-------------------+---------------------------------------------------------------+------------------+---------+

```