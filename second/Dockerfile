
MAINTAINER Taras <taras.zyhmunt@gmail.com>
CMD ["echo","Hello Second docker image"]


#COPY docker/cassandra/docker-entrypoint.sh /docker-entrypoint.sh
#RUN chmod a+x docker-entrypoint.sh
#
#COPY docker/cassandra/initial-seed.cql docker-entrypoint-initdb.d/initial-seed.cql


#FROM openjdk:8
#EXPOSE 8882
#ADD target/second.jar second.jar
#ENTRYPOINT ["java","-jar","/second.jar"]


FROM cassandra:3.11.4

EXPOSE 9042

#CMD ("docker run -p 9042:9042 cassandra:3.11.4")
#
#CMD ("docker run -p 9042:9042 cassandra:3.11.4")


RUN mkdir /data
COPY data/initial-seed.cql /data/