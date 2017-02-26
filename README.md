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