# Hadoop syslog counter project

Counts logs of different levels

## Dependencies and requirements

Assembly system - Maven:
Clean -> Package (or Install, but jar-file is generated during the package step).

In target/ appears jar-file with dependences.

Used system CentOS 7 Linux. VM with 4 CPU and 8GB RAM, 60GB hard disk.
Hadoop version 2.10.1.
Java version java-1.8.0-openjdk-devel.x86_64.

Other dependencies in pom.xml.

## Commands

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

10 000 syslog messages will be created and syslog-file will be copied to input/syslog__curdate__ (make sure in your /etc/rsyslog.conf syslog messages are saved to /var/log/syslog and all messages are logged).

Then run 
```
yarn jar target/lab1-1.0-SNAPSHOT-jar-with-dependencies.jar input output
```

To read compressed SequenceFile run
```
hdfs dfs -text output/part-r-00000
```

## Successfully completed tests

![tests](https://user-images.githubusercontent.com/55759699/163042879-3f8f87d9-8006-42d4-a787-d44e28f3bebc.png)

## Loaded files

![uploaded files](https://user-images.githubusercontent.com/55759699/163043169-9a30d925-b84b-4a31-ac87-c3000b8fa075.png)

## Completed MapReduce Job's listings

![job1](https://user-images.githubusercontent.com/55759699/163043329-4f8a4e49-9ed9-40b9-a64d-1a2933ac1dff.png)

![job2](https://user-images.githubusercontent.com/55759699/163043346-5363a948-85cd-49ad-aa9a-1e94f70dc212.png)

## Reading results from compressed file

![read_file](https://user-images.githubusercontent.com/55759699/163043461-e44c2818-472d-4068-a24c-b9aa086619c6.png)
