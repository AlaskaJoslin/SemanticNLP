# SemanticNLP
Server side application for my crowd sourcing experiment at my website.

Compile with Maven using: mvn package.

For unit tests to work you need to create a file with:
jdbc:mysql://localhost:3306/database name
user name
password

You should place this file outside of the repository to avoid accidentally uploading it.
Then update file location in /src/main/java/com/alaskajoslin/app/db/db_connection.java.
