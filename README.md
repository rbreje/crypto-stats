# Start
Use this command in order to start the app. Change the jdk location in necessary. JDK 11 is minimum required.

```shell
./gradlew bootRun -Dorg.gradle.java.home="/usr/lib/jvm/java-11-openjdk-amd64"
```
Or user `start.sh` to startup the app.

Coulnd't manage to handle all the symbols. I dumped the processed ones into the console. ETHBTC is the most used one and hopefully is returned in the first batch. You can check others from there as symbol details endpoint.

# Endpoint

`http://localhost:8080/api/symbols`

`http://localhost:8080/api/symbol/ethbtc` 

# Details

BeHashMap is my own trivial hash map implementation. Didn't use it at all in the end.

Heap is a data structure that is usually implemented with an array but can be thought of as a binary tree.
A (child) node can't have a value greater than that of its parent. Hence, in a max-heap, the root node always has the largest value.
A (parent) node can't have a value greater than that of its children. Thus, in a min-heap, the root node always has the smallest value.
The rebalancing works by moving the largest element from the max-heap to the min-heap, or by moving the smallest element from the min-heap to the max-heap.

# Screenshots

![Beggining](images/symbol_detail_1.png)
![After some time](images/symbol_detail_2.png)
![All symbols](images/all_symbols.png)