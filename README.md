AinWebServer
============

Polisens webbserver som tillhandahåller data från en PostgreSQL-server i JSON-format över HTTPS genom ett RESTful API.

Setup
-----

First clone the source code to your computer along with it's submodules.

    $ git clone --recursive git@github.com:Rovanion/AinWebServer.git

### Import into eclipse with m2e

Install the eclipse maven plugin called m2e either through Help -> Install New Software or Help -> Eclipse Marketplace and import the "AinWebServer" folder as a maven project. Do not import as a normal java project. If you do you must remove .classpath and .project before importing as a maven project again.


### With terminal tools

    $ mvn eclipse:eclipse
    $ mvn dependency:resolve -Dclassifier=javadoc

After these commands you can import the folder "AinWebServer" as a normal Java project.


Configuration
-------------

The final step of the setup is to specify the database driver, path,  username and password in conf/db.preferences:

    $ cp conf/db.preferences.example conf/db.preferences
	$ editor conf/db.preferences


Headless start
--------------

After this you can import the web server project into eclipse and start editing. Or you can start it with:

    $ mvn compile exec:java
