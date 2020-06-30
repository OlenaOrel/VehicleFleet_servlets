FROM mysql:8.0

COPY src/main/resources/scripts/ /docker-entrypoint-initdb.d/
