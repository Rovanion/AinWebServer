AinWebServer
============

Polisens webbserver som tillhandahåller data från en PostgreSQL-server i JSON-format över HTTPS genom ett RESTful API.

Setup
------------------------------
    $ git clone git@github.com:Rovanion/AinWebServer.git
    $ mvn eclipse:eclipse
    $ mvn compile

The final step of the setup is to specify the database driver, path,  username and password in src/main/xml/gov/polisen/mybatis/mybatis-config.xml:
    $ cp src/main/xml/gov/polisen/mybatis/mybatis-config.xml.example src/main/xml/gov/polisen/mybatis/mybatis-config.xml
	$ editor src/main/xml/gov/polisen/mybatis/mybatis-config.xml.example


After this you can import the web server project into eclipse and start editing. Or you can start it with:
   $ mvn exec:exec
