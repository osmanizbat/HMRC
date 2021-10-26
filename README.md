# HMRC

### Compiling

```
mvn clean package
```

### Running

Java 1.7

```
docker run --rm -v $PWD/target:/app openjdk:7u111 sh -c "java -jar /app/HMRC-1.0.0.jar"
```

Java 1.8

```
docker run --rm -v $PWD/target:/app openjdk:8 sh -c " java -jar /app/HMRC-1.0.0.jar"
```
