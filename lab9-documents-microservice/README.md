# Lab9 - Document microservice

In the lab9-documents-microservice folder I created a project starting from the Document Service from the previous lab and turning it into a microservice using Open Liberty.

I only had to write the entity class for the document and the dao class which comunicated with the database. The controller which exposes the endpoints is the same as the one from the DocumentService using javax-rs.

The configuration for the data source is in the config folder from liberty this time, instead of the glassfish server.

I also deployed the microservice into a container with docker. The configuration is in the Dockerfile.
The mysql database is also deployed and the configuration file si docker-compose.yml.