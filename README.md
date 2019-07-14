---


---

<h1 id="introduction">Introduction</h1>
<p>This repository includes three Java applications namely Java Grep App, JDBC App and Twitter CLI App. </p>
<h1 id="java-grep-app">Java Grep App</h1>
<p>This App searches for a text pattern recursively in a given directory, and output matched lines to a file. The app takes three arguments.</p>
<h2 id="usage">Usage</h2>
<pre><code>How To Use:  regex rootPath outFile
Example : JavaGrepImp .*data.* home/centos/dev /tmp/grep.out

</code></pre>
<ul>
<li>regex: a string on which search would be performed</li>
<li>rootpath: path of the directory</li>
<li>outFile: path and name of output file where output search result would be stored</li>
</ul>

<h2 id="design-and-implementation">Design and Implementation</h2>
<p><strong>JavaGrep:</strong>  is an interface where abstraction of method used in JavaGrepApp is provided.<br>
<strong>JavaGrepApp:</strong>  In this class, main method is called in which the arguments are intially set into JavaGrepImp's object and then the method <strong>process</strong> is called. This method then calls other method such as listFiles, readLines, containsPattern and writeToFile. The pseudocode is as follows:</p>
<ul>
<li>Walk through all folders of the input directory and search for file path and if found sub directory, invoke listFiles recursively, until pure file path found, and add them to List.</li>
<li>Scan all lines in a file and output it as a list of string.</li>
<li>Compare all text in list of String to regex.</li>
 <li>Output matched string to an output file.</li>
</ul>
<h2 id="enhancement-and-issues">Enhancement and Issues</h2>
<ol>
<li>Add a feature where every line will be returned with the path of its file to make finding the file easy.</li>
</ol>

<h2 id="libraries">Libraries</h2>
<ul>
<li>FileWriter</li>
<li>BufferedWriter</li>
<li>PrintWriter</li>
</ul>

<h1 id="jdbc-app">JDBC App</h1>
<p>This App helps the user to perform CRUD (Create, Read, Update and Delete) operations in PostgreSQL database.</p>

<h2 id="usage1">Usage</h2>
<ul>
<li>Create new table : <pre><code>JDBCExecutor create "ID" "First_Name" "Address"</code></pre></li>
 <li>Find a data base on ID parameter : <pre><code>JDBCExecutor read "ID" </code></pre></li>
 <li>Create new table : <pre><code>JDBCExector JDBCExecutor delete "ID"</code></pre></li>
 </ul>
<h2 id="design-and-implementation-1">Design and Implementation</h2>
<p>To connect the Database Storage layer with Java, Client/Server connection is used. The diagram below shows the design of this project.</p>
<img src="/diagrams/jdbc.png" alt="Server-host"></p>
<ul>
<li>DatabaseConnectionManager communicates with Database server through JDBC ie. java.sql library component</li>
<li>CustomerDAO/OrderDAO are classes which extends DataAccessObject interface. These classes execute the SQL queries, saves the output of the queries and then handles customerDto/orderDto</li>
<li>Dto objects (order/customer) are java representation of database tables. They are manipulated by DAO layer.</li>
<li>JDBCExecutor has the main method which receives user input, initialized other layer and execute the program. </li>

</ul>

<h2 id="enhancements-and-issues">Enhancements and Issues</h2>
<ul>
<li>SQL queries are hardcoded. This can be improved by allowing SQL queries to be entered via CLI.</li>
<li>Data Cleaning before Update and Insert is not performed. This can be improved by providing a service layer where data cleaning as required by business can be performed.</li>
</ul>

<h2 id="libraries">Libraries</h2>
<ul>
<li>Postgresql</li>
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
<h2 id="design-and-implementation-1">Design and Implementation</h2>
<p>The archtecture of this program is similar to client server architecture where the Twitter REST API is the server.</p>
<img src="/diagrams/Twitter.png" alt="Server-host"></p>
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

