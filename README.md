### introduction

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


