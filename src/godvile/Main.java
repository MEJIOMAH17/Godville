package godvile; /**

 * Created by mark on 24.08.16.
 */

import com.beust.jcommander.JCommander;
import org.gnome.gtk.Gtk;
import org.gnome.notify.Notification;
import org.gnome.notify.Notify;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Main {
    private void start(String godName) throws InterruptedException, IOException {
        Thread tray = new Thread(new Tray());
        tray.start();
        Diary diary = new Diary(godName);
        Gtk.init(new String[0]); // initialize Gtk
        String notifyImageWay = System.getProperty("user.dir") + "/icon_128.png";// because Notification  requires absolute path
        Notify.init("Program Name"); // initalize the notification system
        String tmp = "";
        while (true) {
            if (tmp.equals(diary.getLastEntry())) {
                sleep(3000);
                continue;
            }
            tmp = diary.getLastEntry();
            NotificationString stringToNotify = new NotificationString(tmp);
            Notification myNotification = new Notification("Годвилль", stringToNotify.toString(), notifyImageWay); // create the notification object
            myNotification.show(); // show the notification
            sleep(Constants.getRequestDelay());
        }
    }

    private void start(String godName,File diaryFile) throws InterruptedException, IOException {
        Diary diary = new Diary(godName, diaryFile );
        Thread diaryWriter = new Thread(new DiaryWriter(diary));
        diaryWriter.start();
        start(godName);
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        Settings settings = new Settings();
        JCommander commander=new JCommander(settings, args); // simple one-liner
        commander.setProgramName("Godville","Вы можете использовать \" для записи аргументов с проблами, например -god_name \"мой бог \" ");
        if(settings.isHelp()){
            commander.usage();
            return;
        }
        if(settings.getFileAddress()!=null) new Main().start(settings.getGodName(), new File(settings.getFileAddress()));
        else new Main().start(settings.getGodName());


    }
}
