import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class read_csv{
    public static void main(String[] args){
        try (BufferedReader br =
                     new BufferedReader(new FileReader("assets/objects.csv"))) {
            String text;
            while ((text = br.readLine()) != null) {
                String words[] = text.split(",");
                switch (words[0]){
                    case "engineer":
                        System.out.println("engineer");
                        break;
                    case "pylon":
                        System.out.println("pylon");
                        break;
                    case "command_centre":
                        System.out.println("command_centre");
                        break;
                    case "metal_mine":
                        System.out.println("metal_mine");
                        break;
                    case "unobtainium_mine":
                        System.out.println("unobtainium_mine");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


