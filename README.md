# Guestbook
First of all sign the <a href="http://linusnyren.ddns.net/Website/index.html"> Guestbook</a> <br>
<h2>An assignment at YRGO, studying java enterprise</h2>

This is an way overbuilt GuestBook utilizing Java EnterPrise beans and RESTful api. <br>
The server(wildfly) talks to an underlying Derby database storing the messages.<br>
To get the database connections right, replace standalone.xml in wildfly with the one provided.<br>

You can GET all the Notes by GET calls to the URL which will return an JSON Array containing all the elements<br><br>

You can GET Notes by Id by GET calls to URL/noteid/{id} which will return an JSON Object<br><br>

You can GET Notes by Author by GET calls to URL/author/{author} which will return an JSON Array with one or more objects<br><br>

You can add new Notes either by JNDI or POST calls with JSON in following format;<br>
{<br>
  "author": "name",<br>
  "message": "Whats on your mind?"<br>
}<br>
The Server autogenerates id and date when posting to the database.<br>
