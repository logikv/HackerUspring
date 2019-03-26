package util.json;

import org.springframework.util.ResourceUtils;

import java.io.*;

public class Help {
    private static File help = null;

    static {
        try {
            help  = ResourceUtils.getFile("classpath:About.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printHelp(){
        try(BufferedReader reader = new BufferedReader(new FileReader(help))){
            String s = "";
            while ((s = reader.readLine()) != null){
                System.out.println(s);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
