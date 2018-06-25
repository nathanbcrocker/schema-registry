# schema registry
Avro schema registry service

Build then image:
`./gradlew docker build -x test`

Run the image:
`docker run -p 8080:8080 -t com.digiup/schema-registry`

Stop the image:
`docker stats`
`docker stop {ID}`