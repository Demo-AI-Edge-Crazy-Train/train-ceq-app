# Demo Stop The Crazy Train - Lego Train Camel post processing app

![lego](https://www.lego.com/cdn/cs/set/assets/blt95604d8cc65e26c4/CITYtrain_Hero-XL-Desktop.png?fit=crop&format=webply&quality=80&width=1600&height=1000&dpr=1)

## Description

This application is a part of global demo "Stop The Crazy train" :
“ The train is running mad at full speed and has no driver ! Your mission, should you choose to accept it, is to train and deploy an AI model at the edge to stop the train before it crashes. This message will self-destruct in five seconds. Four. three. Two. one.  tam tam tada tum tum tada tum tum tada tum tum tada tiduduuuuummmm tiduduuuuuuuuummm ”


## Objectives

Showcase a Quarkus Camel application that :
  - consume a String message produced by the AI model application from the MQTT topic (Mosquitto or AMQ Broker) `train-model-result`
  - convert the String message to a cloudevent and write it to an Kafka topic `train-monitoring`
  - convert the message to a JSON and extract the controller command from the message (which is an integer) 
  - write the command to the MQTT topic `train-command`



### Prerequisites
 
You will need:
  - podman /docker
  - Java openjdk version "17.0.9" 
  - Quarkus
  - Maven
  - MQTT broker (mosquitto docker image)
  - MQTT cli
  - Kafka broker (we use AMQ Streams)


### Dev run
run AMQ Streams and Mosquitto broker
```sh
docker-compose up
```
run the Camel route 
```sh
mvn quarkus:dev -DskipTests=true   
```
