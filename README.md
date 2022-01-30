# ASSN

## Distributed Social Network

### Requirements

Run name server (in background)

```shell
$ rmiregistry &
```

If needed, compile all Java source files

```shell
$ javac *.java
```

Start the server by calling it

```shell
$ java Server
```

Start one client by calling it and passing username and password

```shell
$ java ASSNClient <username> <password>
```