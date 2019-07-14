

<h1 id="introduction">Introduction</h1>
<p>This repository includes three Java applications namely Java Grep App, JDBC App and Twitter CLI App. </p>
<h1 id="java-grep-app">Java Grep App</h1>
<p>This App searches for a text pattern recursively in a given directory, and output matched lines to a file. The app takes three arguments.</p>
<h2 id="usage">Usage</h2>
<pre><code>How To Use: JavaGrepImp regex rootPath outFile
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
<li>Scan through all folders of the input directory and search for file path and if found sub directory, invoke listFiles recursively, until pure file path found, and add them to List.</li>
<li>Scan all lines in a file and output it as a list of string.</li>
<li>Compare all text in list of String to regex.</li>
<li>Output matched string to an output file.</li>
</ul>
<h2 id="enhancement-and-issues">Enhancement and Issues</h2>
<ol>
<li>readLines saves the entire content of every file in memory which means that large files would be entirely written in memory. Improvement could be to simultenously read and match regex and only store matched lines in memory or directly append it into output file.</li>
<li>Multiple regex patterns not supported now. Could be added in later versions.</li>
 <li>Every file in input directory is scanned. Maybe add a filter to escape few formats e.g filter to not scan .xml files.</li>
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
 <li>Create new table : <pre><code>JDBCExecutor delete "ID"</code></pre></li>
 </ul>
<h2 id="design-and-implementation-1">Design and Implementation</h2>
<p>To connect the Database Storage layer with Java, Client/Server connection is used. The diagram below shows the design of this project.</p>
<img src="/diagrams/jdbc.png" alt="Server-host"></p>
<ul>
<li><strong>DatabaseConnectionManager</strong> communicates with Database server through JDBC ie. java.sql library component</li>
<li><strong>CustomerDAO/OrderDAO</strong>are classes which extends DataAccessObject interface. These classes execute the SQL queries, saves the output of the queries and then handles customerDto/orderDto</li>
<li>Dto objects (order/customer) are java representation of database tables. They are manipulated by DAO layer.</li>
<li><strong>JDBCExecutor</strong> has the main method which receives user input, initialized other layer and execute the program. </li>

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
<p>This Application allows user to Post, Delete and show Tweets through command line.</p>

<h2 id="setup">Initial Setup</h2>
After creating an application on Twitter Developer account, get access to keys and tokens. Setup those tokens and keys in your local machine environment variables. For example:
<pre><code>
#put the following env var in ~/.bash_profile
export consumerKey=
export consumerSecret=
export accessToken=
export tokenSecret=
$ source ~/.bash_profile

</code></pre>




<h2 id="usage-1">Usage</h2>

<pre><code>To Post a Tweet: TwitterCLI post "Tweet_text" "latitude:longitude"


Description: Create a tweet with a geotag and
output the created tweet object(simplifeid version)
in JSON format.

</code></pre>


<pre><code>To show a tweet: TwitterCLI show  Tweet-Id

Description: Lookup a tweet by ID and print the
tweet object in JSON format.
</code></pre>
<pre><code>To delete a Tweet: TwitterCLI delete Tweet-Id1,Tweet-Id2

Description: Delete a list of tweets by id
Output deleted tweet id and print deleted tweet
object.
</code></pre>

<h2 id="design-and-implementation-1">Design and Implementation</h2>
<p>The archtecture of this program is similar to client server architecture where the Twitter REST API is the server.</p>
<img src="/diagrams/Twitter.png" alt="Server-host"></p>
<ul>
<li><strong>ApacheHttpHelper</strong>This class handles authorization with the Twitter REST API and make HTTP requests(GET/POST/DELETE) and then get HTTPResponse.</li>
 <li><strong>TwitterResDao</strong>this class implements CrdRepo interface. This class construct URI which is passed to ApacheHttpHelper class so that HTTP requests can get executed. The response from HTTP comes in this class where its body is parse to see the HTTP response code. Finally the response entity is converted into java object i.e Tweet Object.</li>
<li><strong>TwitterServiceImp</strong> This class handles all business logic on the user input arguments and validates them and handles tweet object.Finally the cleaned data is pass to DAO layer to be executed on Twitter.com</li>
 <li><strong>TwitterCLIRunner</strong>Parse user input and calls corresponding service methods</li>


<li><strong>TwitterCLI</strong> This class manages dependencies and pass the arguments to TwitterCLIRunner so that the application is run.</li>
<h2 id="sp">Spring Framework</h2>

</ul>
<p>Dependencies of different layers within the application is also shown to be managed by Spring Framework. Three different approaches are used namely Spring Bean, Spring Component Scan and Spring Boot.</p>
<ol>
<li><strong>Spring Bean</strong>:<strong>TwitterCLIBean</strong> class setup or configures different components of the application by using java beans components as its building blocks.</li>
<li><strong>Spring Annotation</strong>: Rather than explicitly annotating beans, we can create @compnent annonation in the classes which are supposed to be added in dependencies for the application to run in IoC. This is done in  <strong>TwitterCLIComponentScan</strong> class.</li>
<li><strong>Spring Boot</strong>:<strong>TwitterCLISpringBoot</strong> class uses spring framework auto configuration mode to manage dependencies automatically without much configurations to be done manually.</li>
</ol>
<h3 id="test">Test</h3>
<p>Mockito testing framework was used to perform unit testing on twitter service layer by mocking DAO layer. Dao layer i.e TwitterResDAO module went through Junit tests cases on Create, findbyId and deletebyId methods in the Dao layer.</p>
<h2 id="enhancement-and-issues-1">Enhancement and Issues</h2>
<ol>
<li>Search by ID and Delete by ID can be cumbersome, rather possible improvement could be to get a list of all tweets maybe by searching hashtags or text and then delete them with interactive mode. </li>
 <li>Posting is only with text now, but can add links, images and videos.</li>
 <li>Prompt the user, if the access tokens are not formatted or invalid.</li>
</ol>
<h2 id="libraries">Libraries</h2>
<ul>
<li>jackson-databind</li>
<li>signpost-commonHttp4</li>
<li>commons-dbcp2</li>
<li>mockito-core</li>
<li>JUnit</li>
<li>spring-boot-starter-web</li>
</ul>

