# springboot
A collection of multiple tech stack implementations using springboot

* Springboot KafkaProducer
* Springboot KafkaConsumer


Run the maven build:
mvn clean install

build the two docker images:
docker build -t aballaci/springboot-kafka-consumer:v4 consumer/
docker build -t aballaci/springboot-kafka-producer:v4 producer/

deploy to kubernetes:
kubectl apply -f springboot-kafka.yaml
