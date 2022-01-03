FROM icr.io/appcafe/open-liberty:full-java8-openj9-ubi
EXPOSE 9080
EXPOSE 9443
ENV HTTP_ENDPOINT true
COPY --chown=1001:0 target/documents.war /config/dropins/
COPY --chown=1001:0 src/main/liberty/config/server.xml /config/
COPY --chown=1001:0 src/main/liberty/config/mysql-connector-java-8.0.27.jar /config/
RUN configure.sh