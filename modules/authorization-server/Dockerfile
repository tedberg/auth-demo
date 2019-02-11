FROM openjdk:8-jre

ARG PACKAGE_NAME

 # where a Spring Boot application creates working directories for Tomcat by default.
VOLUME /tmp

#COPY certificates/verisign-root.cer  /etc/ssl/certs/
#
#RUN $JAVA_HOME/bin/keytool -storepasswd -new mysecretpassword -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit && \
#    echo "yes" | $JAVA_HOME/bin/keytool -import -trustcacerts -file /etc/ssl/certs/verisign-intermediate.cer -alias ldap-intermediate-ca -keystore $JAVA_HOME/lib/security/cacerts -storepass mysecretpassword && \
#    echo "yes" | $JAVA_HOME/bin/keytool -import -trustcacerts -file /etc/ssl/certs/verisign-root.cer -alias ldap-root-ca -keystore $JAVA_HOME/lib/security/cacerts -storepass mysecretpassword && \
#    rm -f /etc/ssl/certs/verisign-intermediate.cer &&\
#    rm -f /etc/ssl/certs/verisign-root.cer

COPY target/${PACKAGE_NAME} app.jar

 # "touch" the jar file so that it has a file modification time (Docker creates all container files in an "unmodified" state by default).
RUN sh -c 'touch /app.jar'

EXPOSE 8080
# USER nobody:nogroup

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Ddebug -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
