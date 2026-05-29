package dev.kesares.zea;

import dev.kesares.zea.shell.Shell;

public class Main {

    static void main() {
        Shell.INSTANCE.init();
        Shell.INSTANCE.run();
    }
}
