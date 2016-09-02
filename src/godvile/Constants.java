package godvile;

class Constants {
    private static long requestDelay=30000;
    private static String imageWay = System.getProperty("user.dir") + "/icon_128.png";


     static void setRequestDelay(long requestDelay) {
        if(requestDelay<=30000) throw new IllegalArgumentException("requestDelay must be >=30000 see https://wiki.godville.net/API");
        Constants.requestDelay = requestDelay;
    }

    static long getRequestDelay() {
        return requestDelay;
    }

     static String getImageWay() {
        return imageWay;
    }
}
