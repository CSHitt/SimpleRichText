# SimpleRichText
## A (probably very bad) library for rich text
### Not much documentation is available yet, as of writing this I created it like less than 3 days ago
Goals for the project
- Java 5 and newer support (so I can play around with Windows 9x)
- Decently fast
- Easy to use

NOTE: The project right now is _really bad._ I don't recommend anyone use it at the moment.
## Compiling (On windows at least)
Enter the `/root` directory in the terminal
To compile the demo, type in the following command from the `/root` directory:
```
javac srt/Main.java
```
You can then run the project with:
```
java srt.Main
```
To package as an executable jarfile, run:
```
jar cvfe <NameOfTheJar>.jar srt.Main srt
```
To compile for Java 5, you will need to install JDK/JRE 8, and set it to target Java 5.
You also need the Java-5-corresponding `rt.jar` file. Then you can compile like so:
```
javac -source 1.5 -target 1.5 -bootclasspath <path/to/rt> srt/Main.java
```
You can also try installing the JDK/JRE for Java 5, but I would not recommend it. Java 5 is already long dead and in the grave,
and installing it on your system may pose a security risk; I found the tools in the JDK/JRE 5 to be kinda buggy and slow, and `jar` didn't work properly.

Also don't mind the jockey :)
