cp -r bin webapp/WEB-INF/classes
jar -cvf java_session.war -C webapp .
cp java_session.war D:/app/apache-tomcat-10.0.22/webapps