import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bruce on 12/5/16.
 */
public class Stats {
    public static HashMap wins(BufferedReader reader) {
        String s;
        HashMap<String, Integer> hm = new HashMap<>();
        try {
            while ((s = reader.readLine()) != null) {
                String[] array = s.split(" ");
                int winflag = Integer.valueOf(array[0]);
                if (winflag == 0) {
                    for (int i = 1; i <= 5; i++) {
                        String name = array[i];
                        if (hm.get(name) != null) {
                            int count = hm.get(name);
                            hm.replace(name, count, ++count);
                        } else {
                            hm.put(name, 1);
                        }
                    }
                } else {
                    for (int i = 7; i <= 11; i++) {
                        String name = array[i];
                        if (hm.get(name) != null) {
                            int count = hm.get(name);
                            hm.replace(name, count, ++count);
                        } else {
                            hm.put(name, 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
        }
        return hm;
    }
    public static String winner(HashMap<String,Integer> passedMap) {
        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : passedMap.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
            //System.out.println(entry.getKey() + "," + entry.getValue());
        }
        String winner = maxEntry.getKey();
        return winner;

    }

    public static void main(String[] args) {
        File f = new File("input.txt");
        BufferedReader in = null;
        HashMap<String,Integer> result = null;
        try {
            in = new BufferedReader(new FileReader(f));
            result = wins(in);
        } catch (FileNotFoundException e) {}
        finally {
            if (in != null) {
                try {
                    in.close();
                }catch (IOException e){}
            }
        }
        String winner = winner(result);
        System.out.println("Winner=" + winner);
    }
}
