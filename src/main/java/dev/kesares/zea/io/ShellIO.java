package dev.kesares.zea.io;

public final class ShellIO {

    private ShellIO() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static void errln(String message) {
        System.err.println(message);
    }

    public static void println(Object o) {
        IO.println(o);
    }

    public static void println() {
        IO.println();
    }

    public static void print(Object o) {
        IO.print(o);
    }

    public static String readln(String prompt) {
        return IO.readln(prompt);
    }
}