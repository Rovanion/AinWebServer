AinWebServer
============

Polisens webbserver som tillhandahåller data från en PostgreSQL-server i JSON-format över HTTPS genom ett RESTful API.

Setup
------------------------------
    $ git clone git@github.com:Rovanion/AinWebServer.git
    $ mvn eclipse:eclipse
    $ mvn compile

After this you can import the web server project into eclipse.

The final step of the setup is to specify the database username and password in src/main/xml/gov/polisen/mybatis/mybatis-config.xml
