# Hadoop syslog counter project

Counts logs of different levels

Assembly system - Maven:
Clean -> Package (or Install, but jar-file is generated during the package step).

In target/ appears jar-file with dependences.

Used system CentOS 7 Linux. VM with 4 CPU and 8GB RAM, 60GB hard disk.
Hadoop version 2.10.1.
Java version java-1.8.0-openjdk-devel.x86_64.

Other dependencies in pom.xml.

To start and prepare dfs and yarn:
```
cd /opt/hadoop-2.10.1/
sbin/start-dfs.sh
sbin/start-yarn.sh

hdfs dfs -mkdir -p /user/root
```

Then go to project dir.
```
./prepare_input_output.sh
```

10 000 syslog messages will be created and syslog-file will be copied to input/syslog__curdate__ (make sure in your conf syslog messages are saved to /var/log/syslog and all messages are logged).

Then run 
```
yarn jar target/lab1-1.0-SNAPSHOT-jar-with-dependencies.jar input output
```

To read compressed SequenceFile run
```
hdfs dfs -text output/part-r-00000
```
