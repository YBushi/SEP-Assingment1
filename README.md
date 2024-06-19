# Report for Assignment 1

## Project chosen
Name: cribeJava
URL: https://github.com/scribejava/scribejava
Number of lines of code and tool used to count it: 17332, lizard
Programming language: Java

## Coverage measurement
### Existing tool
Name of the existing tool used was Jacoco. It was executed by adding a Jacoco plugin into the pom.xml file and then executing maven. (Add some details)
(Provide screenshot, there is a problem that it only creates reports for specific parts such as scribejava-core, figure out a way to put it all in one report)
![ScreenShot of coverage results](projectCoverage.png)

### Our own coverage tool
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
#### Tomas Busa
#### 3.3
#### Function 1: com.github.scribejava.httpclient.apache.ApacheHttpClient.getRequestBuilder
#### Data Structure to hold coverage information, write all information about the branches taken to a console:
![Data Structure to hold coverage information, write all information about the branches taken to a console](tomas_images/function1_dataStructure.png)
#### Set a flag if the branch is reached:
![Set a flag if the branch is reached](tomas_images/function1_flagsetting.png)
#### Coverage results output:
![Coverage results output](tomas_images/function1_results.png)
####
#### Function 2: com.github.scribejava.httpclient.apache.OAuthAsyncCompletionHandler.completed
#### Data Structure to hold coverage information, write all information about the branches taken to a console:
![Data Structure to hold coverage information, write all information about the branches taken to a console](tomas_images/function2_dataStructure.png)
#### Set a flag if the branch is reached:
![Set a flag if the branch is reached](tomas_images/function2_flagsetting.png)
#### Coverage results output:
![Coverage results output](tomas_images/function2_results.png)
####
#### 3.4
#### Function 1: com.github.scribejava.httpclient.apache.ApacheHttpClient.getRequestBuilder
#### New tests:
![New tests](tomas_images/function1_newTests-1.png)
![New tests](tomas_images/function1_newTests-2.png)
#### Coverage before:
![Coverage before tests](tomas_images/function1_coverageBefore.png)
#### Coverage after:
![Coverage after tests](tomas_images/function1_coverageAfter.png)
####
#### Function 2: com.github.scribejava.httpclient.apache.OAuthAsyncCompletionHandler.completed
#### New tests:
![New tests](tomas_images/function2_newTests.png)
#### Coverage before:
![Coverage before tests](tomas_images/function2_coverageBefore.png)
#### Coverage after:
![Coverage after tests](tomas_images/function2_coverageAfter.png)

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
#### Jayran Duggins
#### 3.3
#### Function 1: scribejava-httpclient-armeria/src/main/java/com/github/scribejava/httpclient/armeria/ArmeriaHttpClient.java (getHttpMethod)
#### Data Structure to hold coverage information, write all information about the branches taken to a console:
![Data Structure to hold coverage information, write all information about the branches taken to a console](insert image)
#### Set a flag if the branch is reached:
![Set a flag if the branch is reached](insert image)
#### Coverage results output:
![Coverage results output](insert image)
####
#### Function 2: scribejava-httpclient-armeria/src/main/java/com/github/scribejava/httpclient/armeria/ArmeriaHttpClient.java (getServicePath)
#### Data Structure to hold coverage information, write all information about the branches taken to a console:
![Data Structure to hold coverage information, write all information about the branches taken to a console](insert image)
#### Set a flag if the branch is reached:
![Set a flag if the branch is reached](insert image)
#### Coverage results output:
![Coverage results output](insert image)
####
#### 3.4
#### Function 1: scribejava-httpclient-armeria/src/main/java/com/github/scribejava/httpclient/armeria/ArmeriaHttpClient.java (getHttpMethod)
#### New tests:
![New tests](insert image)
![New tests](insert image)
#### Coverage before:
![Coverage before tests](insert image)
#### Coverage after:
![Coverage after tests](insert image)
####
#### Function 2: scribejava-httpclient-armeria/src/main/java/com/github/scribejava/httpclient/armeria/ArmeriaHttpClient.java (getServicePath)
#### New tests:
![New tests](insert image)
#### Coverage before:
![Coverage before tests](insert image)
#### Coverage after:
![Coverage after tests](insert image)
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Nikola Bakalinov
Function 1: scribejava/scribejava-httpclient-ning/src/main/java/com/github/scribejava/httpclient/ning
/NingHttpClient.java  (doExecuteAsync)
![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/af7aefc7-8db2-422b-8420-7760e43b8191)

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/f45a7c09-47b2-4142-9d92-223d0b758001)

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/cda0b2e0-cef0-499d-a484-cdcdab1d1985)


![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/38aada3b-daf4-4391-b616-281aa152fefa)

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/90e3d968-0e97-4785-b3fa-c4aa001ff250)

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/4a1a351c-74b5-4c46-b95c-2eb499a9e776)

Function 2: SEP-Assingment1/scribejava-httpclient-armeria/src/main/java/com/github/scribejava/httpclient/armeria
/ArmeriaHttpClient.java  (doExecuteAsync)

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/bae2c17f-828d-434f-8796-7e5f7ce05b5d)
![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/e469bb70-d3d3-4f0d-963f-b0e480117631)
![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/28f7488b-f7ec-4a88-a94c-4f0d5708106c)
![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/c6833583-ac0e-41b8-bfb4-397fc7d102d8)




![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/9f7c108a-fc45-4466-bbae-f72f10861526)

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/8a842731-beec-4bc9-957b-c7b0be9ca350)

## Luis Sartorius
# **Function 1: appendSignature**

![Modified code for OAuth10aService](Luis-images/image-4.png)
![Modified code for OAuth10aService](Luis-images/image-3.png)

# **Function 2: Equals**
#### Polar
![Modified code for Polar](Luis-images/image-8.png)
![Modified code for Polar](Luis-images/image-9.png)

#### FitBit
![Modified code for Fitbit](Luis-images/image-12.png)
![Modified code for Fitbit](Luis-images/image-13.png)


### Individual tests

#### Console logs for branch hits taken.
![Print results for OAuth10aService](Luis-images/image.png)
![Print results for Auth2](Luis-images/image-5.png)

## Test 1 for appendSignature
Created a test file
[Test file][https://github.com/YBushi/SEP-Assingment1/commits/Luis-report/scribejava-core/src/test/java/com/github/scribejava/core/oauth/OAuth10aServiceTest.java]
#### Before:
![OAuth10aService coverage before](Luis-images/image-1.png)
#### After:
![OAuth10aService coverage after](Luis-images/image-2.png)

#### Results:
The coverage went to 0 to 100% because no test was created for it.


## Test 2 for Equals
Created a test file
### Auth2Polar
[Test file][https://github.com/YBushi/SEP-Assingment1/commits/Luis-report/scribejava-apis/src/test/java/com/github/scribejava/apis/polar/PolarOAuth2AccessTokenTest.java]
#### Before:
![Polar coverage before](Luis-images/image-7.png)
#### After:
![Polar coverage after](Luis-images/image-6.png)

### Auth2FitBit
[Test file][https://github.com/YBushi/SEP-Assingment1/commits/Luis-report/scribejava-apis/src/test/java/com/github/scribejava/apis/fitbit/FitBitOAuth2AccessTokenTest.java]
#### Before:
![FitBit coverage before](Luis-images/image-11.png)
#### After:
![FitBit coverage after](Luis-images/image-10.png)

#### Results:
The coverage went to 0 to 100% because no tests was created for both.



















---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------










