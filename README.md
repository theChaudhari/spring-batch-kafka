# SpringBatchKafka

# Steps To Download--->
1.https://kafka.apache.org/downloads
2.Download Version Latest

# Steps To Setup
1.Extract it
2.Copy it in C drive with folder name "Kafka"


# Steps To Configure 
1.Make Change in Server Properties    --->(Search For log.dir) log.dirs=c:/kafka/kafka-logs\
2.Copy this line to server Properties --->  listeners= PLAINTEXT://127.0.0.1:9092
3.Make Change in Zookeeper properties --->  (Search for dataDir) dataDir=c:/kafka/zookeeper


# Step 1- Start Zookeeper
./bin/windows/zookeeper-server-start.bat config/zookeeper.properties

# Step 2- Start Kafka Server
./bin/windows/kafka-server-start.bat config/server.properties


# To Create Topics
./bin/windows/kafka-topics.bat --create --partitions 1 --replication-factor 1 --topic [TopicName] --bootstrap-server localhost:9092/kafka-topics.sh --create --partitions 1 --replication-factor 1 --topic quickstart-events --bootstrap-server localhost:9092


# Produce Console
./bin/windows/kafka-console-producer.bat --topic [TopicName] --bootstrap-server localhost:9092

# Consumer Console
./bin/windows/kafka-console-consumer.bat --topic [TopicName] --from-beginning --bootstrap-server localhost:9092

To Terminate Kafka Event
rm -rf /tmp/kafka-logs /tmp/zookeeper

# To Delete Topics

Make Changes in Server.Properties

delete.topic.enable=true

.\bin\windows\kafka-run-class.bat kafka.admin.TopicCommand --delete --topic [Topic_Name] --zookeeper localhost:2181


# By Abhishek Chaudhari



   ______                  _                                          
  |___  /                 | |                                          
     / /    ___     ___   | | __   ___    ___   _ __     ___   _ __    
    / /    / _ \   / _ \  | |/ /  / _ \  / _ \ | '_ \   / _ \ | '__| 
   / /__  | (_) | | (_) | |   <  |  __/ |  __/ | |_) | |  __/ | |     
  /_____|  \___/   \___/  |_|\_\  \___|  \___| | .__/   \___| |_| 
                                               | |                      
		                       |_|                    
											  	   
