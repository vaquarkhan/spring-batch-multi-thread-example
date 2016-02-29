# spring-batch-multi-thread-example
## Introduction
This Spring Batch project aim to read an XML file in a concurrent mode (multi-thread) and write all data in a in-memory database.

## Thread safe XML Reader
To read XML tag in a concurrent way, we need a Thread Safe XML Reader because all the beans are shared by different thread created.

## TaskExecutor (SimpleAsyncTaskExecutor)
To do this, the taskExecutor which is an instance of SimpleAsyncTaskExecutor let me parallelize my process in a very simple way.
I just need to create a taskExecutor bean implementing the SimpleAsyncTaskExecutor class and inject it in my Tasklet I want to parallelize.
A taskExecutor never use the same thread to process data, it will always create a new thread for that (no thread pool). 


```xml
<bean id="taskExecutor" class="org.springframework.core.task.SimpleAsyncTaskExecutor"/>

<batch:tasklet task-executor="taskExecutor" throttle-limit="5">
    <batch:chunk reader="threadSafeXmlItemReader" writer="embeddedDatabaseJdbcBatchItemWriter" commit-interval="100"/>
</batch:tasklet>

```

## Throttle-limit attribute
The throttle-limit attribute is the maximum number of thread running at one time on your machine during the run time of your application.

e.g. If we need to read and write 1000 items and we have a chunk size of 100 then the application will create 5 new threads with 100 items to process for each one of them. And only when the first thread of this 5 is terminate then the taskExecutor will destroy it and create a new one with the next 100 items to process and keep going like that until the 1000 items be proceed. But the maximum number of thread executed at one time during the run time of your application will be 5.

e.g. If we need to read and write 100 items and we have a chunk size of 1000 then the application will create 5 new threads with (more or less) 20 items to process for each one of them.

Note : If a maximum connection pool variable is defined in the database bean then the maximum number of thread running at one time on your machine during the run time of your application will be :

```
if((throttle limit) < (maximum connection pool variable)) {
	use (throttle limit) as throttle-limit
} else {
	use (maximum connection pool variable) as throttle-limit
}
```
