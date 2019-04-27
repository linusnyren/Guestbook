# Guestbook
An assignment at YRGO, studying java enterprise

This is an way overbuilt GuestBook utilizing Java EnterPrise beans.
The server(wildfly) talks to an underlying Derby database storing the messages.

You can GET all the Notes by GET calls to the URL which will return an JSON Array containing all the elements

You can GET Notes by Id by GET calls to URL/noteid/{id} which will return an JSON Object

You can GET Notes by Author by GET calls to URL/author/{author} which will return an JSON Array with one or more objects

You can add new Notes either by JNDI or POST calls with JSON in following format;
{
  "author": "name",
  "message": "Whats on your mind?"
}
The Server autogenerates id and date when posting to the database.
