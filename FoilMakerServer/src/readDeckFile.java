/**
 * Created by Bruce on 11/10/2016.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class readDeckFile {
    private String fileName = null;
    protected Vector deck = null;
    protected Vector usedDeck = null;
    protected Random random = new Random(System.currentTimeMillis());

    public readDeckFile(String filepath) {
        this.fileName = filepath;

        try {
            this.readDeck();
            this.usedDeck = new Vector();
        } catch (IOException e) {
        }
    }

    private void readDeck() throws IOException {
        BufferedReader in = null;
        deck = new Vector();
        try {
            File deckFile = new File(fileName);
            in = new BufferedReader(new FileReader(deckFile));
            String pairs;

            while ((pairs = in.readLine()) != null) {
                String[] questionAnswer;
                questionAnswer = pairs.split(":");

                if (questionAnswer.length == 2) {
                    WordDeck wordDeck = new WordDeck(questionAnswer[0], questionAnswer[1]);
                    deck.add(wordDeck);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new IOException();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

    }
}
