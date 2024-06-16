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







Armeria coverage before :

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/5c01d558-a2cc-4f83-aece-0e08067248fb)





3.3
Function 1: scribejava-apis/src/main/java/com/github/scribejava/apis/facebook/FacebookAccessTokenErrorResponse.equals
Data Structure to hold coverage information, write all information about the branches taken to a console:
![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/076c647d-ac92-44aa-9ded-3909b64290ca)


Set a flag if the branch is reached:

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/cf9f7319-5267-4074-a336-94390c8ad201)


Coverage results output:

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/cec52a48-7904-4708-8603-c2caee802718)

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/1007aaf6-8a61-49ea-9729-e3f52bd674cf)


![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/2f8b12e4-2b33-4047-ba30-99c08838881f)

![image](https://github.com/YBushi/SEP-Assingment1/assets/113595455/e6220d6b-a68d-4d21-b890-55be099b600f)




---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------










