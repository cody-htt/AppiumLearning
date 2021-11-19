package utils.environment;

public class TestEnum {

    private final TestMobilePlatform platform;

    public TestEnum(TestMobilePlatform platform) {
        this.platform = platform;
    }

    public void displayPlatform() {
        switch (platform) {
            case ANDROID:
                System.out.println(platform.getPlatform(0));
                break;
            case IOS:
                System.out.println(platform.getPlatform(1));
                break;
            default:
                throw new IllegalArgumentException("Platform is not supported");
        }
    }

    public static void main(String[] args) {
        TestEnum androidPlatform = new TestEnum(TestMobilePlatform.ANDROID);
        androidPlatform.displayPlatform();

        TestEnum iOSPlatform = new TestEnum(TestMobilePlatform.IOS);
        iOSPlatform.displayPlatform();
    }


}
