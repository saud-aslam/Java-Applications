---


---

<h1 id="introduction">Introduction</h1>
<p>This repository includes three Java applications which has been build and packages in Maven structure.</p>
<h1 id="java-grep-app">Java Grep App</h1>
<p>This App searches for a text pattern recursively in a given directory, and output matched lines to a file. The app takes three arguments.</p>
<h2 id="usage">Usage</h2>
<pre><code>How To Use:  regex rootPath outFile
Similar to : egrep -r {regex} {rootpath} &gt; {outFile}

</code></pre>
<ul>
<li>regex: a string of characters for describing the search pattern</li>
<li>rootpath: path of the directory</li>
<li>outFuile: path to output file and the anme of file</li>
</ul>
<h2 id="design-and-implementation">Design and Implementation</h2>
<p><strong>GrepApp:</strong>  is an interface where all the necessary methods have been declared.<br>
<strong>GrepAppImp:</strong>  is a the class taht includes the main method and implements GrepApp interface. This class implements listfiles and readlines method using Java 7 and 8 methods.</p>
<ul>
<li>Walk through all fiels and folders of the directory add them to List</li>
<li>item If it is a directory open the directory and add all the files in a files list</li>
<li>read all lines of a file to a list</li>
<li>compare regex with stored lines store matched lines in a list and write in a file</li>
</ul>
<h2 id="enhancement-and-issues">Enhancement and Issues</h2>
<ol>
<li>Add a feature where every line will be returned with the path of its file to make finding the file easy.</li>
</ol>
<h1 id="jdbc-app">JDBC App</h1>
<p>This is a JDBC application which has data about customers, salesperson and orders stored in a relational database. In this application create, read, update, delete (CRUD) functionalities has been implemented.</p>
<h2 id="design-and-implementation-1">Design and Implementation</h2>
<p>This project has been implemented with cleint server architecture as it is shown in diagram below.</p>
<ul>
<li>Configure Connection with database</li>
<li>Transfer request to dao layer with the use of dto object</li>
<li>execute the request in dao layer</li>
<li>update the database</li>
<li>return results back to user</li>
</ul>
<p>Components and classes:</p>
<ul>
<li>DatabaseConnectionManager class creates connection with database using JDBC library components.</li>
<li>CutomerDao class implemenst DataAccessObject interface. this class consructs SQL queries such as create, update, delete, read and executes them.</li>
<li>orderDao class implements DataAccessObject interface and constrcuts the SQL query which returns all the orders of a customer and their corresponding salesperson.</li>
<li>DTO objects are java respresentation of database tables. they are used to pass the data between dao layer and database.</li>
<li>JDBCExecuter class initializes and runs the program.</li>
</ul>
<h2 id="enhancements-and-issues">Enhancements and Issues</h2>
<ul>
<li>This App can be improved to ask for user requests instead of mannually executing SQL queries.</li>
</ul>
<h1 id="twitter-cli-app">Twitter CLI App</h1>
<p>This is a twitter command line app that takes advantage of twitter REST Api to Post, Delete and show tweets.</p>
<h2 id="usage-1">Usage</h2>
<p>Firstly, authorization component should be setup as environmental variables.</p>
<pre><code>To Post a Tweet: post "Tweet_text" latitude longitude 

</code></pre>
<pre><code> To show a tweet: show  Tweet-Id
 
 Description: Lookup a tweet by ID and print the
 tweet object in JSON format.
 Arguments:
 
&gt; Tweet Id should contain only numbers.
 

</code></pre>
<pre><code>To delete a Tweet: delete Tweet-Id

Description: Delete a list of tweets by id
Output deleted tweet id and print deleted tweet
object.

&gt; Tweet Id should be numbers only.


</code></pre>
<p>##Design and Implementation</p>
<p>The archtecture of this program is similar to client server architecture where the Twitter REST API is the server.</p>
<p>TwitterCLIRunner initializes the application. It gets the user input and passes it to service layer where all business logic will be checked , after passing the tests the requets would be send to TwitterRestDAo layer with the help of DTO objects. after executing the request, http response will be send back and displayed to user in jason format.</p>
<ul>
<li>ApacheHttpHelper class is in the lowest level and implaments HttpHelper interface. This class creates connection with the REST Api, passes the request and receives the the http response.</li>
<li>TwitterRestDao calss implements CrdRepository interface. this class constructs the URL passes it to ApacheHttpHelper class, in return it receives the response and validates the http response and converts jason file to java object.</li>
<li>TwitterServiceImp class implements TwitterService interface. this class validates user input, creates tweet object in case of posting a tweet and passes it to dao layer.</li>
<li>TwitterClIApp class manages the dependencies of the App and starts the application.</li>
<li>TwitterClIRunner acccepts user input.</li>
</ul>
<p>Inaddition to traditional way, dependencies of this app has been managed using spring framework.</p>
<ol>
<li>Spring Bean Appraoch: under spring package TwitterCliBean calss creats java beans for different components of the Apps and sets up configuration.</li>
<li>Spring Annotation Approach: TwitterCliSpringAnnotaion create annotation by using in-line @component annotation to add classes to IoC.</li>
<li>Spring Boot Approach: TwitterCliSpringBoot uses spring framework to manage dependencies but reduces configuration significantly.</li>
</ol>
<h3 id="test">Test</h3>
<p>TwitterCli App was tested with Junit test and mockito tets cases.<br>
TwitterREstDao class was tested with Junit, fucntionalities such as Create, FindByID, adn deleteByID was tested.<br>
Mockito was used to create unit test cases for service leyer.</p>
<h2 id="enhancement-and-issues-1">Enhancement and Issues</h2>
<ol>
<li>Implementig the funcitonality to post media.</li>
</ol>
<h2 id="libraries">Libraries</h2>
<ul>
<li>signpost commonHttp4</li>
<li>jackson databined</li>
<li>spring-boot-starter-web</li>
<li>commons-dbcp2</li>
<li>mockito-core</li>
<li>JUnit</li>
<li>Postgresql</li>
<li>spring-boot-maven-plugin</li>
</ul>

