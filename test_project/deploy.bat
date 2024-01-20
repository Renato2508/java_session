xcopy bin webapp\WEB-INF\classes /E /I /Y
jar -cvf session_java.war -C webapp .
copy session_java.war D:\app\apache-tomcat-10.0.22\webapps