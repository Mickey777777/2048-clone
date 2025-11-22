import java.io.*;

public class ScoreManager {
    private static final String FILE_PATH = System.getProperty("user.dir") + "/2048_score.txt";

    public static void saveBest(int best){
        try(
                FileWriter fw = new FileWriter(FILE_PATH);
                BufferedWriter bw = new BufferedWriter(fw);
                ){
            bw.write(String.valueOf(best));
            bw.flush();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static int loadBest(){
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return 0;
        }

        try(
                FileReader fr = new FileReader(FILE_PATH);
                BufferedReader br = new BufferedReader(fr);
                ){
            return Integer.parseInt(br.readLine());
        }catch(IOException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
