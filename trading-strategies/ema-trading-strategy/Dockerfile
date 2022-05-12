FROM openjdk:11-jre-buster
VOLUME /tmp
EXPOSE 8080
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
COPY ./target/ema-trading-strategy-0.0.1-SNAPSHOT.jar /app/ema-trading-strategy.jar
ENTRYPOINT ["java", "-jar", "/app/ema-trading-strategy.jar"]
