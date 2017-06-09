"# yelp-data-mining-maven" 

Follow these steps after downloading the .zip file

1) Unzip into some <path>
2) cd <path>/BrightPlan/BrightPlan_M
3) Run these commands
mvn clean
mvn compile
mvn package
4) This generates

<path>/BrightPlan/BrightPlan_M/target/BrightPlan_M-1.0-SNAPSHOT.jar

5) edit location of selenium driver

<path>/BrightPlan/BrightPlan_M/testing/config/BPlan_RunInfo.properties

change entry

selenium.webdriver.chrome=driver/chromedriver.exe

to

selenium.webdriver.chrome=driver/mac/chromedriver

or some other location where you already have driver.

Or download and place driver under folder <path>/BrightPlan/BrightPlan_M/driver/mac

6) run the test

mvn -Dtest=com.brightplan.automation.projects.yelp.YELP001_DisplaySearchDetails_JUnit -DconfigFile=<path>/BrightPlan/BrightPlan_M/testing/config/BPlan_RunInfo.properties test > some-output-file.log

7) end of run will result in a report file (name in properties file BPlan_RunInfo.properties)

CurrentRun_ALL.out


