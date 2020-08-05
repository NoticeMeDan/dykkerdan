FROM openjdk:14

ADD build/distributions/dykkerdan.tar ./
WORKDIR ./dykkerdan
EXPOSE 8080
EXPOSE 6001

ENTRYPOINT ["bin/webservice"]