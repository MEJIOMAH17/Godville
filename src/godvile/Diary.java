package godvile;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

 class Diary {
    private Date lastRequest;
    private String lastEntry;
    private URL apiURL;
    private File diaryFile;

    /**
     *
     * @param name God Name
     */
     Diary(String name){
        try {
            apiURL=new URL("http://godville.net/gods/api/"+name+".json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

     Diary(String name, File diaryFile){
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
            lastEntry=(String) jsonObject.get("diary_last");;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   synchronized String getLastEntry(){
        getEntry();
        return lastEntry;
    }

     File getDiaryFile() {
        return diaryFile;
    }


}
