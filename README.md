# Methanol Manager

[![Build Status](https://travis-ci.org/matobet/methanol-manager.svg?branch=master)](https://travis-ci.org/matobet/methanol-manager)

Project for PA165: Liquor bottles registry during prohibition

# Development

## Dependencies:

* Java 7
* Maven 3
* Apache Derby
    * running local instance on port **1527**
    * created database **pa165**

## Running

    mvn spring-boot:run

## Debugging GWT

First run the application backend server (either as above or from IDE).
Then you have 2 options:

1. Classical DevMode

    For classical DevMode you will need an older browser (such as Firefox 23).
    You start by running the following command in the *frontend* direcory.

        mvn gwt:debug

    After it finishes initialisation you can connect to it via standard java debugger from IDE using "Remote debugging"
    and port **8000**.

    This connection will spawn a small window with debug output. Open the given link in your old firefox. You can now
    set breakpoints in you IDE and application will be recompiled after each refresh (F5).

2. Super DevMode

    For Super DevMode you will need any modern browser with support of *Source Maps*.
    You start by running the code server, again in the *frontend* directory.

        mvn gwt:run-codeserver

    After it boots up, visit [localhost:9876](http://localhost:9876) and drag the bookmarklets to your browser's
    bookmark bar.

    Then navigate to [localhost:8080](http://localhost:8080) where your application is running. You can now use the
    **Compile** button (hidden inside "Dev Mode On") to recompile. Please note that as opposed to the classical DevMode
    the refresh of page wont trigger a recompile. With Super DevMode you are in control when the recompile happens.

    This recompilation is also faster than that with classical DevMode and recommended for refreshes after code changes.
    Classical DevMode on the other hand is better suited to actual debugging of problems as it gives you full power of
    java IDE whereas with Super DevMode you can use only you browser's integrated javascript debugger.
