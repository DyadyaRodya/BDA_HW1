import syslog
import random

SYSLOG_LEVELS = {
    syslog.LOG_EMERG: '[emerg]',
    syslog.LOG_ALERT: '[alert]',
    syslog.LOG_CRIT: '[crit]',
    syslog.LOG_ERR: '[error]',
    syslog.LOG_WARNING: '[warning]',
    syslog.LOG_NOTICE: '[notice]',
    syslog.LOG_INFO: '[info]',
    syslog.LOG_DEBUG: '[debug]',
}

def main():
    syslog.openlog(facility=syslog.LOG_SYSLOG)
    for i in range(10000):
        priority = random.choice([syslog.LOG_EMERG, syslog.LOG_ALERT, syslog.LOG_CRIT, syslog.LOG_ERR, syslog.LOG_WARNING, syslog.LOG_NOTICE, syslog.LOG_INFO, syslog.LOG_DEBUG])
        syslog.syslog(priority, SYSLOG_LEVELS[priority]+': test msg #'+str(i))
    syslog.closelog()

if __name__ == '__main__':
    main()

