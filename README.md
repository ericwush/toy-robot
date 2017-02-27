# Toy Robot

### Problem description
[PROBLEM](PROBLEM.md)

### How to build
In project directory: 
* MAC
```sh
$ ./gradlew clean build
```
* Windows
```sh
$ gradlew.bat clean build
```
Artifact is built into build/libs folder

### How to run
```
java -jar <path-to>/toy-robot.jar
```
### Testing framework
Spock framework is used for tests. Compared to JUnit, it removes boilerplate thanks to Groovy. The DSL supports BDD out of the box.
 
Data Driven Testing is heavily used in the test code. A data pipe, indicated by the left-shift (<<) operator, connects a data variable to a data provider. The data provider holds all values for the variable, one per iteration.
 
```
where:
a << [3, 7, 0]
b << [5, 0, 0]
c << [5, 7, 0]
```
Above block indicates that 3 data sets are provided: 
```
1 - a = 3, b = 5, c = 5
2 - a = 7, b = 0, c = 7
3 - a = 5, b = 7, c = 0
```
This is helpful when testing same functionality against different data, especially for edge cases.

### Test data
```
> PLACE 4,4,EAST
> REPORT
4,4,EAST
> MOVE
> REPORT
4,4,EAST
```
```
> PLACE 1,2,EAST
> MOVE
> MOVE
> LEFT
> MOVE
> REPORT
3,3,NORTH
> PLACE 3,2,NORTH
> MOVE
> RIGHT
> REPORT
3,3,EAST
```