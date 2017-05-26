# RectangleFinder

#### Like Pathfinder, only not

An implementation of a Mars Rover for Next45.

## Guiding principles

- I try to not disinform in the naming. For instance, `setStarting*` methods raise `ValueAlreadyModifiedExceptions` if they are called after initialisation / after the Rover has moved.

## Wild Assumptions

## Other notes

I had some internal debate about the design. In the end I decided to just have the rover control it's own movement, as that allows me to not overthink it, and it's probably closer to how a real world rover would be compartmentalised.


# A guide to getting it, building it, driving it, and modifying it

## Prerequisites

- Java 1.8. I use the Oracle version (not OpenJDK). If you don't like 1.8 you can remove the lambda in `util.FileUtil:47` - it's the only place I can think of where I use a Java 8 feature.
- SDKman (optional). I just used it to install Gradle. Your OS's preferred way to install gradle is great, too.
- Gradle 3.5 (optional). I do include the gradle-wrapper in the distribution.
- IntelliJ (optional). Not really necessary, but the Community Edition is free and fun, like a lot of good things in life.


### Getting a copy

Like so:

`git clone https://github.com/johnblackspear/RectangleFinder.git`

### Gradle tasks

#### `gradle test`

Run the test suite.


#### `gradle javadoc`
Create the docs. They should be readable via `build/docs/javadoc/index.html`
![Javadocs](/raw/images/javadoc.png?raw=true "Javadocs")



#### `gradle assembleDist`
This builds the release version of the Rover.

You will see a `RectangleFinder-1.0.zip` and `RectangleFinder-1.0.tar` file in `build/distributions/` after running this.

The zip file contains the jar file and a bat file and shell script to start the Rover.

#### `gradle jar`
This creates a jar file with the Rover application. It's in `build/libs/`.

#### `gradle build`
This compiles the project. Classes are in `build/classes`.


### Running it

#### Via gradle

Build it via `gradle jar`.

Then, run it like so:

`java -classpath build/libs/RectangleFinder-1.0.jar com.gerrieswart.recfinder.Main src/test/resources/basic_test.rover`

You can optionally pass a second argument to only output the final position and none of the noisy things:
`java -classpath build/libs/RectangleFinder-1.0.jar com.gerrieswart.recfinder.Main src/test/resources/basic_test.rover OnlyPrintFinalPos`


#### Via IntelliJ

Open the project in IntelliJ.

Double click on com.gerrieswart.recfinder.Main.

Press CTRL-SHIFT-F10 (Run current file).

It will exit with an error because we haven't changed the run config. To do so, press ALT-SHIFT-F10, and choose "Edit Configurations...".

Change "Program arguments" to read `src/test/resources/with_blanks_and_comments.rover`
It's included in the repository.

Click Run.

Output should be in the console.


### Modifying it safely

The Rover has a reasonably complete test suite, so you can edit with safety.

I would suggest you run the gradle tests continuously, so that you see immediately when something is borked.

I added a system notifier to the test task - it should pop a toast notification up on your system when any test fails (tested on Ubuntu) - if no, make sure your system's notifier daemon is installed and running. I know it's something like "growl" on mac, no idea what it is in Windows.

To re-run tests on every save:
`gradle test --rerun-tasks --continuous --info`

You'll notice some syserr output from the tests, like in this screenshot:
![Syserr text when tests are run](/raw/images/test_errors.png?raw=true "syserr output on test run")

That's by design, I used it while I built the rover.

If (when) as test fails it's often easiest to look at gradle's report to see what exactly happened.

It's accessible via `build/reports/tests/test/index.html`


![Test Results](/raw/images/test_results.png?raw=true "Test Results")

#### Source Tree

```
├── gradle
│   └── wrapper
├── raw
│   └── images
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── gerrieswart
    │               └── recfinder
    │                   ├── exception
    │                   ├── movement
    │                   └── util
    └── test
        ├── java
        │   └── com
        │       └── gerrieswart
        │           └── recfinder
        │               ├── movement
        │               └── util
        └── resources
```
