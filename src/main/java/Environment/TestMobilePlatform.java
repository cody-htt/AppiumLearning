package Environment;

public enum TestMobilePlatform {
    ANDROID("Android platform"),
    IOS("iOS platform");

    private final String name;

    TestMobilePlatform(String name) {
        this.name = name;
    }

    public String getPlatform(int index) {
        TestMobilePlatform[] platforms = TestMobilePlatform.values();
        return platforms[index] + " is " + this.name;
    }
}
