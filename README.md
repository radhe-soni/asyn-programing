# asyn-programing
This is example source code for Futures in Java.
* SquareCalculator gives an API to calculate square of an integer.
* Submit an integer to SquareCalculator and it provides a future which holds the result.
* As soon as the executor completes the underlying task, the future can provide the result.
* FuturesExample class demonstrates how to obtain result from Future.
* FuturesExample class demonstrates that after submitting a task to executor, the main thread can do some other task and when it requires the result, it can ask future by using get method.

This version contains example for CompletableFuture in Java.
* CompletableFutureExample class demonstrates four different example of using CompletableFutures.
* cmnPoolFuture executes in common thread pool and compute the result.
* cmnPoolFtrWithRsltSquared also executes in common thread pool and demonstrates composition of transformation in sequential way.
    * It first compute square of an integer and then again compute square of the output from previous step to arrive at result.
    * This you can perform a series of transformations or send result to consumers using same pipeline.
    * The result of each step is fed to next step.
* CompletableFuture provides same set of APIs to be used with custom thread pool, futureInSeparatePool and ftrInSeparatePoolWithRsltSquared demonstrates the same capability.
* The most important thing to note about common-pool is that as the name suggest it common to whole application. The state of thread is shared across the application. Meaning if there is some information stored in thread local of a common pool thread then it is visible everywhere. Similarly if the information is modified at any time then it will be applicable everywhere regardless the origin of call.
