ews-test
========

Small demo using Exchange Web Services Java API.
Outputs the last 10 emails of your Inbox

build
=====

    gradle shadowJar (makes a fat jar)


config
======

Fill out fields in config.yaml

    #Sample YAML Config
    server: https://SOMESERVER/ews/exchange.asmx 
    user: USER
    pass: MyAwesomePassword
    domain: MYDOMAIN

run
===

    java -jar build/libs/ews-test-1.0-all.jar
