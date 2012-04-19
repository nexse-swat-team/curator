
![screenshot][screenshot]

Curator is an application to create email newsletters from a twitter feed.

It is implemented in html/css/js and requires a rest backend for sending
emails.

You can open the src/main/webapp/index.html file locally in your browser to
play around with the application but you will not be able to actually send any
real emails.

For a real deployment you will need a servlet container such as Apache Tomcat.

## Quick start

Edit the file src/main/resources/META-INF/spring/smtp.properties to configure a valid smtp server. If you need more complex smtp settings, also edit the src/main/resources/META-INF/spring/applicationContext.xml file.

    $ mvn tomcat:run
    $ your-browser http://localhost:8080/curator

Enjoy! :)

[screenshot]: https://github.com/nexse-swat-team/curator/raw/master/screenshot.png  "Screenshot"
