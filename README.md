
### Ex_2_1

in this assigment we were ask to implement a three diffrents methods,
to count the amount of lines in text files that we generate with *RANDOM* number of lines.

```java
int getNumOfLines(String[] fileNames)
```

this func recive a string array of the files names
and for each file , in order, count the lines with while loop

```java
int getNumOfLinesThreads (String[] fileNames)
```

this func create fileNames.length CounterThread.
each CounterThread (Class that extend from Thread class) calculate the amount of lines in 1 file 
that he recive in his constructor. 

```java
int getNumOfLinesThreadPool (String[] fileNames)
```
in this func i create a ThreadPool , the ThreadPool recive a callable task that count the number of lines for each file.

### results

[![HaVDrLg.md.png](https://iili.io/HaVDrLg.md.png)](https://freeimage.host/i/HaVDrLg)

in this test i genarate 10,000 files with at most 17,000 lines for each,
and after that active all the functions.
as we see function 3 and 2 are far way more effective and fast from the first one.
when i send the list to function 3 and 2
the Theards can calculate at the same time.
which in the first function , we calculate one file and just after we finish we move to the seconde and etc.


------------



[![HaWAWjS.md.png](https://iili.io/HaWAWjS.md.png)](https://freeimage.host/i/HaWAWjS)

in the second test i create 10,000 file with 82,000 at most.
as we see the Threads function are more way better

with minor changes with function 2 and 3



------------



### Ex_2_2

"CustomExecutor" that extends the ThreadPoolExecutor class.
It uses a PriorityBlockingQueue to hold its tasks and allows for tasks to be submitted with a priority level.
The maxPriority ArrayList is used to keep track of the number of tasks in each priority level.
The customSubmit() method is used to submit tasks, which increments the count for the task's priority level.
The dwon() method can be used to shutdown the thread pool.
The beforeExecute method is overridden to decrement the count for the task's priority level.

"Future" that extends the FutureTask class.
It is used to hold the result of a task that has been submitted to the CustomExecutor thread pool.
It also implements the Comparable interface which allows the PriorityBlockingQueue to order the tasks based on their priority level.
The class has a private field "PriorteyOfQ" that holds the priority level of the task.
The compareTo method compares the priority level of the task with another task.
The getTask() method returns the Task instance. The PriorteyOfQ() method returns the priority level of the task.

 "Task" that implements the Callable interface.
 It is used to represent a task that is submitted to the CustomExecutor thread pool.
 The class has the following fields:
theTask: A Callable object that represents the actual task to be executed.
PriorteyOfQ: An int that represents the priority level of the task.
type: A TaskType object that represents the type of task.

The class has the following methods:
Task(Callable<T> theTask,TaskType type): A constructor that takes a Callable and a TaskType object and sets the theTask, PriorteyOfQ, and type fields accordingly.
Task(Callable<T> theTask): A constructor that takes a Callable object and sets the theTask and PriorteyOfQ fields accordingly, the default priority is 3.
call(): A method that calls the call() method on the theTask object and returns the result.
createTask(Callable Taskrn ,TaskType t): A static method that creates a Task instance from a Callable and a TaskType object.
createTask(Callable Taskrn ): A static method that creates a Task instance from a Callable object.
It also has setters and getters for all the fields.
The Task class is used by the CustomExecutor to represent tasks that are submitted to the thread pool. When a task is submitted, it is wrapped in a Future object and added to the PriorityBlockingQueue.



