import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Setup {
        static Properties prop = new Properties();

        public static void getEnvironment(){

            try{
                FileInputStream fis = new FileInputStream("src/Files/env.properties");
                prop.load(fis);
            }
            catch(FileNotFoundException e){
                System.out.println("File not found");
            }
            catch(IOException e) {
                System.out.println("IO Exception");
            }

        }
}
