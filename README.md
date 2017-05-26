# RectangleFinder

#### Like Pathfinder, only not

An implementation of a Mars Rover for Next45.

## Guiding principles
- [YAGNI]. Seriously. You ain't gonna need it. Only add it when you **really** need it. Any assumptions in a program, class, interface, API, or ABI are with you for life in most cases. So make minimal assumptions and you will have the minimal amount of wrong assumptions to fix as the program evolves.
- Clarity before cleverness. I'm quite tempted to do a 10-liner implementation on a spherical 2d grid (I think a whole rover can be done via + and % operators) but I write code for humans first, machines second. I do violate this once - using a deltaArray for `Rover.move()` instead of a more readable `switch`. Hopefully a little bit of coolness can be excused ;)
_"Let us change our traditional attitude to the construction of programs: Instead of imagining that our main task is to instruct a computer what to do, let us concentrate rather on explaining to human beings what we want a computer to do._" -- Donald Knuth, **Literate Programming** (1984)
- Testing is important, because then anyone can work on it. If you are going to change it be sure to give the _Modifying it safely_ section a squiz.
- Code that doesn't exist doesn't have bugs. So keep it short. This is why I don't add a ton of interfaces etc until they are necessary. (Nonexistant code is often a bit light on features, but that's another story).
- I try to not disinform in the naming. For instance, `setStarting*` methods raise `ValueAlreadyModifiedExceptions` if they are called after initialisation / after the Rover has moved.
- I violate the [don't be cute rule] for one Exception. But if you can't make an exception for an Exception when are you planning to make one?

## Wild Assumptions
- `+y` is north, `-y` is south, `+x` is east, `-x` is west. Starting point of `0,0` means the rover is in the middle of the square bound by 0..1, 0..1.
- You can only move in integer multiples of 1. No moving 0.01 to the west, in other words.
- **BIG ASSUMPTION** the "zone" (`8 8`) in the document are the postive i.e. East, North (x,y) bounds of this strangely rectangular planetoid. So the left bottom corner of every zone is marked as 0,0 - and negative values for x and y are exceptions.
- I assume (due to the fact that `8 8` is seen as 64 blocks and not 81 blocks) that the [interval] is [0,8) (i.e., as closed interval that would be [0,7]) for the valid coordinates and that any value outside that is out of bounds.
- I'm raising exceptions when the rover goes out of bounds. Another option would be to silently fail (sit at the edge of the exploration zone) until a command moves it away from the edge again.
- Rover battery is infinite. So I am not filtering the command set for things like four consecutive identical turns (effectively a NOOP) or `RL` and `LR` combos (again, effectively doing nothing as the heading is unchanged). I also don't change an `RRR` sequence into an `L` or similar. I like to think the rover sitting there turning like a mad beast is either going full disco mode (thanks, Johansson) or taking photos for [Nasa's Mars Images].
- I assume quite small sets of instructions. Many megabytes of instructions is explicitly not (yet) supported.


## Other notes

I had some internal debate about the design. In the end I decided to just have the rover control it's own movement, as that allows me to not overthink it, and it's probably closer to how a real world rover would be compartmentalised.


# Getting it, building it, driving it, and modifying it

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

The easy way (recommended):
`gradle execute -Pexec.args="src/test/resources/basic_test.rover"`

(or `./gradlew execute -Pexec.args="src/test/resources/basic_test.rover"` if you didn't install
gradle and just want to use the wrapper)


Or, the less easy way: Build it via `gradle jar`.

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

If (when) a test fails it's often easiest to look at gradle's report to see what exactly happened.

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


[YAGNI]: https://martinfowler.com/bliki/Yagni.html
[Nasa's Mars Images]: https://www.nasa.gov/mission_pages/mars/images/
[interval]: https://en.wikipedia.org/wiki/ISO_31-11
[don't be cute rule]: http://www.maultech.com/chrislott/resources/cstyle/ottinger-naming.html#TOC_Too_Cute
