"# yelp-data-mining-maven" 

## How the problem is addressed.

1. Framework created with factory pattern to handle creating Yelp requirement specific implementation
2. Project has a JUnit class that uses the base util and helper to handle most of the validations

### com/brightplan/automation/projects/yelp/YELP001_DisplaySearchDetails_JUnit
1. This has one method that implements most of the functionality

public void test_restaurant_search
	(
  ..
  )
  
 Individual test methods call this with parameters as shown below.
 
 	@Test
	public void test0004_restaurant_search() throws Exception
	{
		_thisTestCaseNum = "TC0004";
		test_restaurant_search
		(
				_thisTestCaseNum,
				"YELP001_" + _thisTestCaseNum + " Restaurant Search with filter - ",
				"Restaurant search with neighborhood (1) and price filter (1)",
				"Restaurant Pizza / Neighborhood 1 / Price 1",
				"Yelp",
				"",
				"Restaurant Pizza",
				new String [] {
						"Go to yelp.com",
						"Setting focus on Find",
						"Select 'Restaurants' in the dropdown box in Find",
						"Select 'Restaurants' in the dropdown box in Find",
						"Click Find button after setting search criteria",
						"Get total initial result count",
						"Expand All Filters to select parameters",
						"Selecting neighborhood element at index : ",
						"Selecting distance element at index : ",
						"Get total filtered result count",
						"Getting star rating of merchants",
						"Selecting price element at index : "
				},
				new int [] {1},
				new int [] {},
				new int [] {1}
		);
	}
 
 


## Installing and running tests
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


