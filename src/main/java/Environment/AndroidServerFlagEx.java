package Environment;

import io.appium.java_client.service.local.flags.ServerArgument;

public enum AndroidServerFlagEx implements ServerArgument {
    /**
     Port to use on device to talk to Appium. Sample: --bootstrap-port 4724
     */
    BOOTSTRAP_PORT_NUMBER("--bootstrap-port"),
    /**
     If set, prevents Appium from killing the adb server
     instance. Default: false
     */
    SUPPRESS_ADB_KILL_SERVER("--suppress-adb-kill-server"),
    /**
     Port upon which ChromeDriver will run. Sample: --chromedriver-port 9515
     */
    CHROME_DRIVER_PORT("--chromedriver-port"),
    /**
     ChromeDriver executable full path.
     */
    CHROME_DRIVER_EXECUTABLE("--chromedriver-executable"),
    /**
     Reboot emulator after each session and kill it at the end. Default: false
     */
    REBOOT("--reboot"),
    /**
     If compatible Chrome driver is not found, will automatically discover and download compatible Chrome driver
     */
    ALLOW_INSECURE("--allow-insecure");

    private final String arg;

    AndroidServerFlagEx(String arg) {
        this.arg = arg;
    }

    @Override
    public String getArgument() {
        return arg;
    }
}
