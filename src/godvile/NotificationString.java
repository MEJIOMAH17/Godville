package godvile;

/**
 * Created by mark on 24.08.16.
 */

class NotificationString {
    private String string;
    final int wrapSize=40;
    NotificationString(String s){
        StringBuilder sb = new StringBuilder(s);
        int i = 0;
        while (i + wrapSize < sb.length() && (i = sb.lastIndexOf(" ", i + wrapSize)) != -1) { // FIXME: 26.08.16 сделай перенос по словам а не по строчкам
            sb.replace(i, i + 1, "\n");
        }
        string=sb.toString();
    }

    @Override
    public String toString() {
        return string;
    }
}
