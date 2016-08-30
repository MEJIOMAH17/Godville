package godvile;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 30.08.16.
 */

 class Settings {


    @Parameter(names = "-god_name", description = "Имя бога", required = true, variableArity = true)
   // private List<String> godNameList = new ArrayList<>();
    private String godName;


    @Parameter(names = "-file_address", description = "Имя файла для записи в него дневника", required = false)
    private String fileAddress;


    @Parameter(names = "-help", description = "Показать это сообщение",help = true)
    private boolean help;



    public String getGodName() {
        return godName;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public boolean isHelp() {
        return help;
    }
}