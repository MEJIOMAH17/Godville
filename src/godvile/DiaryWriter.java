package godvile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

/**
 * Created by mark on 24.08.16.
 */
class DiaryWriter implements Runnable{
    Diary diary;
    BufferedWriter writer;
    String lastEntry;
    SimpleDateFormat date= new SimpleDateFormat("dd.MM.yy HH:mm:ss");
    DiaryWriter(Diary diary) throws IOException {
        this.diary=diary;
        if(!diary.getDiaryFile().exists()) diary.getDiaryFile().createNewFile();
        FileWriter fileWriter= new FileWriter(diary.getDiaryFile(),true);
        writer= new BufferedWriter(fileWriter);
    }
    public void writeLastEntryToFile() throws IOException {
        String tmpString= diary.getLastEntry();
        if(!tmpString.equals(lastEntry)) {
            lastEntry=tmpString;
            writer.write(date.format(new Date())+" "+lastEntry+"\n");
            writer.flush();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                writeLastEntryToFile();
                sleep(Constants.getRequestDelay());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
