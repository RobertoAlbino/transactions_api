FROM openjdk:11

RUN wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz &&  \
    tar -xvf apache-maven-3.6.3-bin.tar.gz && \
    mv apache-maven-3.6.3 /opt/
ENV PATH="/opt/apache-maven-3.6.3/bin:$PATH"

COPY . /transactions-api
WORKDIR transactions-api
RUN mvn -T 4 clean install
ENTRYPOINT ["java", "-jar","target/transactions-1.0.0.jar"]