# Report for Assignment 1

## Project chosen
Name: ScribeJava
URL: https://github.com/scribejava/scribejava
Number of lines of code and tool used to count it: 17332, lizard
Programming language: Java

## Coverage measurement
### Existing tool
Name of the existing tool used was Jacoco. It was executed by adding a Jacoco plugin into the pom.xml file and then executing command: "mvn clean jacoco:prepare-agent install jacoco:report"

Coverage results provided by the existing tool:
![ScreenShot of coverage scribejava-apis](coverageTool_images/scribeJava-apis-before.png)
![ScreenShot of coverage scribejava-core](coverageTool_images/scribeJava-core-before.png)
![ScreenShot of coverage scribejava-httpclient-ahc](coverageTool_images/scribeJava-http-ahc-before.png)
![ScreenShot of coverage scribejava-httpclient-apache](coverageTool_images/scribeJava-http-apache-before.png)
![ScreenShot of coverage scribejava-httpclient-armeria](coverageTool_images/scribeJava-http-armeria-before.png)
![ScreenShot of coverage scribejava-httpclient-ning](coverageTool_images/scribeJava-http-ning-before.png)
![ScreenShot of coverage scribejava-httpclient-okhttp](coverageTool_images/scribeJava-http-okhttp-before.png)

### Our own coverage tool
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
-
#### Jayran Duggins
#### 3.3
#### Function 1: scribejava-httpclient-armeria/src/main/java/com/github/scribejava/httpclient/armeria/ArmeriaHttpClient.java (getHttpMethod)
#### Data Structure to hold coverage information, write all information about the branches taken to a console:
![Data Structure to hold coverage information, write all information about the branches taken to a console](jayran_images/Function_1_Data_Structure_for_Info.png)
#### Set a flag if the branch is reached:
![Set a flag if the branch is reached](jayran_images/Function_1_Set_flag_pt1.png)
![Set a flag if the branch is reached](jayran_images/Function_1_Set_flag_pt2.png)
#### Coverage results output:
![Coverage results output](jayran_images/Function_1_Manual_Coverage_Report.png)
####
#### Function 2: scribejava-httpclient-armeria/src/main/java/com/github/scribejava/httpclient/armeria/ArmeriaHttpClient.java (getServicePath)
#### Data Structure to hold coverage information, write all information about the branches taken to a console:
![Data Structure to hold coverage information, write all information about the branches taken to a console](jayran_images/Function_2_Data_structure_for_info_about_the_branches_1.png)
#### Set a flag if the branch is reached:
![Set a flag if the branch is reached](jayran_images/Function_2_Set_a_flag_section.png)
#### Coverage results output:
![Coverage results output](jayran_images/Function_2_Manual_Coverage_Report.png)
#### Code Used to Print Coverage (Function 1 & 2):
![Code used to print coverage](jayran_images/Print_Function_Used_To_Print_Coverage_Results.png)
####
#### 3.4
#### Function 1: scribejava-httpclient-armeria/src/main/java/com/github/scribejava/httpclient/armeria/ArmeriaHttpClient.java (getHttpMethod)
#### New tests:
![New tests](jayran_images/Function_1_TestCode_1.png)
![New tests](jayran_images/Function_1_TestCode_2.png)
#### Coverage before:
![Coverage before tests](jayran_images/Function_1_Coverage_Before_New_Test.png)
#### Coverage after:
![Coverage after tests](jayran_images/Function_1_Coverage_After_New_Test.png)
####
#### Function 2: scribejava-httpclient-armeria/src/main/java/com/github/scribejava/httpclient/armeria/ArmeriaHttpClient.java (getServicePath)
#### New tests:
![New tests](jayran_images/Function_2_TestCode.png)
#### Coverage before:
![Coverage before tests](jayran_images/Function_2_Coverage_Before_New_Test.png)
#### Coverage after:
![Coverage after tests](jayran_images/Function_2_Coverage_After_New_Test.png)

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Nikola Bakalinov


3.3
Function 1: scribejava-apis/src/main/java/com/github/scribejava/apis/facebook/FacebookAccessTokenErrorResponse.equals

Data Structure to hold coverage information, write all information about the branches taken to a console:
![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/076c647d-ac92-44aa-9ded-3909b64290ca)


Set a flag if the branch is reached:

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/cf9f7319-5267-4074-a336-94390c8ad201)


Coverage results output:

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/cec52a48-7904-4708-8603-c2caee802718)


Function 2: scribejava-httpclient-ning/src/main/java/com/github/scribejava/httpclient/ning/NingHttpClient.doExecuteAsync

Data Structure to hold coverage information, write all information about the branches taken to a console:


![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/060d6659-83f2-400f-b5ba-ee9f33f57501)


Set a flag if the branch is reached:

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/8639b5d3-c4d9-4510-8c78-313a581585df)
![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/d92a06bc-43d1-4a7e-b62e-1ce23516f2a5)





Coverage results output:


![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/99a1f6c1-46c3-4989-8516-fc049d54f389)







3.4

New Tests 

Function 1: scribejava-apis/src/main/java/com/github/scribejava/apis/facebook/FacebookAccessTokenErrorResponse.equals


![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/1007aaf6-8a61-49ea-9729-e3f52bd674cf)

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/f43daf3c-505f-4b69-9a77-82dbb51a40a2)



Coverage before:

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/2f8b12e4-2b33-4047-ba30-99c08838881f)

Coverage after:


![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/e6220d6b-a68d-4d21-b890-55be099b600f)



New Tests 

Function 2: scribejava-httpclient-ning/src/main/java/com/github/scribejava/httpclient/ning/NingHttpClient.doExecuteAsync


![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/9cbbb9be-455c-4bc3-97ea-b547cf1f3f01)




Coverage before : 


![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/b5e3b5b7-fcf3-486d-9deb-58519bed2be9)


Coverage after:


![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/374fdd51-de07-4af6-9c40-32729cfdab91)

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

### Overall
Old coverage results:
![ScreenShot of coverage scribejava-apis](coverageTool_images/scribeJava-apis-before.png)
![ScreenShot of coverage scribejava-core](coverageTool_images/scribeJava-core-before.png)
![ScreenShot of coverage scribejava-httpclient-ahc](coverageTool_images/scribeJava-http-ahc-before.png)
![ScreenShot of coverage scribejava-httpclient-apache](coverageTool_images/scribeJava-http-apache-before.png)
![ScreenShot of coverage scribejava-httpclient-armeria](coverageTool_images/scribeJava-http-armeria-before.png)
![ScreenShot of coverage scribejava-httpclient-ning](coverageTool_images/scribeJava-http-ning-before.png)
![ScreenShot of coverage scribejava-httpclient-okhttp](coverageTool_images/scribeJava-http-okhttp-before.png)

New coverage results: 
![ScreenShot of coverage scribejava-apis](coverageTool_images/scribeJava-apis-after.png)
![ScreenShot of coverage scribejava-core](coverageTool_images/scribeJava-core-after.png)
![ScreenShot of coverage scribejava-httpclient-ahc](coverageTool_images/scribeJava-http-ahc-after.png)
![ScreenShot of coverage scribejava-httpclient-apache](coverageTool_images/scribeJava-http-apache-after.png)
![ScreenShot of coverage scribejava-httpclient-armeria](coverageTool_images/scribeJava-http-armeria-after.png)
![ScreenShot of coverage scribejava-httpclient-ning](coverageTool_images/scribeJava-http-ning-after.png)
![ScreenShot of coverage scribejava-httpclient-okhttp](coverageTool_images/scribeJava-http-okhttp-after.png)

## Statement of individual contributions
Tomas: I have added the jacoco plugin into the pom.xml file and executed the previously mentioned command to generate coverage reports. I have created my own coverage tool for two functions which showed me which branches weren't covered by the existing tests. I have created new test cases which improved the branch coverage for those functions. Lastly, I have created the new coverage reports after we merged all of our development branches that contained new test cases, which improved our overall branch coverage.
