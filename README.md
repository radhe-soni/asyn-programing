# asyn-programing
This is example source code for Futures in Java.
* SquareCalculator gives an API to calculate square of an integer.
* Submit an integer to SquareCalculator and it provides a future which holds the result.
* As soon as the executor completes the underlying task, the future can provide the result.
* FuturesExample class is shows how to obtain result from Future.
* FuturesExample class shows that after submitting a task to executor, the main thread can do some other task and when it requires the result, it can ask future by using get method.
