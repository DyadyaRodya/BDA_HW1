#rm -R input
hdfs dfs -rm -r input
hdfs dfs -rm -r output
#mkdir input
python syslogger.py
cp /var/log/syslog input/syslog_"$(date +'%Y_%m_%d_%H%M%S')"
hdfs dfs -put input input
