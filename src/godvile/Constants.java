package godvile;

/**
 * Created by mark on 26.08.16.
 */
class Constants {
    private static long requestDelay=30000;

    public static void setRequestDelay(long requestDelay) {
        if(requestDelay<=30000) throw new IllegalArgumentException("requestDelay must be >=30000 see https://wiki.godville.net/API");
        Constants.requestDelay = requestDelay;
    }

    public static long getRequestDelay() {
        return requestDelay;
    }
}
