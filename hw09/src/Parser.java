/**
 * Created by Bruce on 11/5/16.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Parser {
    private String username;
    private int numQueries;
    private ArrayList<String> sqlText = new ArrayList<>();
    public void parse(String filePath) throws
            WrongFileFormatException, WrongNumberOfQueriesException,
            InvalidInputException, MalformedQueryException, IOException {
        File sqlFile = new File(filePath);

        FileReader fr = new FileReader(sqlFile);
        BufferedReader bfr = new BufferedReader(fr);

        String s = bfr.readLine();
        if (s == null || !s.equals("C"))
            throw new WrongFileFormatException("delimiters are not placed properly");
        if(s.equals("C"))
           s = bfr.readLine();
        username = s;
        s = bfr.readLine();

        if(s == null || !s.equals("c"))
            throw new WrongFileFormatException("delimiters are not placed properly");
        s = bfr.readLine();

        if(s == null || !s.equals("N"))
            throw new WrongFileFormatException("delimiters are not placed properly");
        s = bfr.readLine();
        int number = Integer.parseInt(s);

        if (number < 1)
            throw new InvalidInputException("not an integer number 1 or larger");
        s = bfr.readLine();

        if(s == null || !s.equals("n"))
            throw new WrongFileFormatException("delimiters are not placed properly");
        s = bfr.readLine();

        if(s == null || !s.equals("Q"))
            throw new WrongFileFormatException("delimiters are not placed properly");
        int counter = 0;

        while(true) {
            s = bfr.readLine();

            if(s == null)
                throw new WrongFileFormatException("delimiters are not placed properly");
            if(s.equals("q"))
            break;
            boolean wrongSqlText = !s.split(" ")[0].equals("SELECT") &&
                    !s.split(" ")[0].equals("INSERT") &&
                    !s.split(" ")[0].equals("UPDATE") &&
                    !s.split(" ")[0].equals("DELETE");
            if (wrongSqlText)
                throw new MalformedQueryException("the query is not a valid SQL command type");
            sqlText.add(s);
            counter++;
        }
        numQueries = counter;
        if(numQueries != number)
            throw new WrongNumberOfQueriesException("the number of queries in the third block does not match the number provided in the second block");
        bfr.close();
    }
    public String getUserName() {
        return username;
    }
    public int getNumQueries() {
        return numQueries;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("input: ");
        String fileName = s.next();
        Parser parser = new Parser();
        try {
            parser.parse(fileName);
        }
        catch(Exception e){
            System.out.println(parser.getUserName());
            System.out.println(parser.getNumQueries());
        }
    }

}
