package godvile;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Created by mark on 24.08.16.
 */
 class Diary implements Runnable {
    private Date lastRequest;
    private String lastEntry;
    private URL apiURL;
    private File diaryFile;

    /**
     *
     * @param name God Name
     */
    public Diary(String name){
        try {
            apiURL=new URL("http://godville.net/gods/api/"+name+".json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Diary(String name, File diaryFile){
        this(name);
        this.diaryFile=diaryFile;
    }


    private  String getJSON() throws IOException {
        lastRequest=new Date();
        URLConnection connection = apiURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();
        return a.toString();
    }

    /**
     *     get entry from JSON
      */
    private  void  getEntry(){
        if (lastRequest!=null &&lastRequest.getTime()+Constants.getRequestDelay()>System.currentTimeMillis()) return;
        try {
            JSONObject jsonObject= (JSONObject) new JSONParser().parse(getJSON());
            String entry=(String) jsonObject.get("diary_last");
            lastEntry=entry;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public synchronized String getLastEntry(){
        getEntry();
        return lastEntry;
    }

    public File getDiaryFile() {
        return diaryFile;
    }

    @Override
    public void run() {
        try {
            JSONParser parser=new JSONParser();
            JSONObject jsonObject= (JSONObject) parser.parse(getJSON());
            String entry=(String) jsonObject.get("diary_last");
            lastEntry=entry;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
