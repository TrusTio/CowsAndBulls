import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

class GameMethods {
    private static int randomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    static int checkBull(int secretNumbers[], String inputNumbers) {

        int bullCounter = 0;
        if (Character.getNumericValue(inputNumbers.charAt(0)) == secretNumbers[0]) {
            ++bullCounter;
        }
        if (Character.getNumericValue(inputNumbers.charAt(1)) == secretNumbers[1]) {
            ++bullCounter;
        }
        if (Character.getNumericValue(inputNumbers.charAt(2)) == secretNumbers[2]) {
            ++bullCounter;
        }
        if (Character.getNumericValue(inputNumbers.charAt(3)) == secretNumbers[3]) {
            ++bullCounter;
        }

        return bullCounter;
    }


    static int checkCow(int secretNumbers[], String inputNumbers) {
        int cowCounter = 0;
        int a = Character.getNumericValue(inputNumbers.charAt(0));
        int b = Character.getNumericValue(inputNumbers.charAt(1));
        int c = Character.getNumericValue(inputNumbers.charAt(2));
        int d = Character.getNumericValue(inputNumbers.charAt(3));
        if (a == secretNumbers[1] || a == secretNumbers[2] || a == secretNumbers[3]) {
            ++cowCounter;
        }
        if (b == secretNumbers[0] || b == secretNumbers[2] || b == secretNumbers[3]) {
            ++cowCounter;
        }
        if (c == secretNumbers[0] || c == secretNumbers[1] || c == secretNumbers[3]) {
            ++cowCounter;
        }
        if (d == secretNumbers[0] || d == secretNumbers[1] || d == secretNumbers[2]) {
            ++cowCounter;
        }
        return cowCounter;
    }


    static int[] numberGen() {
        int[] secretNumber = new int[4];
        secretNumber[0] = GameMethods.randomNumberInRange(1, 9);
        do {
            secretNumber[1] = GameMethods.randomNumberInRange(0, 9);
        } while (secretNumber[0] == secretNumber[1]);
        do {
            secretNumber[2] = GameMethods.randomNumberInRange(1, 9);
        } while (secretNumber[2] == secretNumber[0]
                || secretNumber[2] == secretNumber[1]);
        do {
            secretNumber[3] = GameMethods.randomNumberInRange(1, 9);
        } while (secretNumber[3] == secretNumber[0]
                || secretNumber[3] == secretNumber[1]
                || secretNumber[3] == secretNumber[2]);
        return secretNumber;
    }

    static void changeLabelVis(JLabel c1,JLabel c2, JLabel c3, JLabel c4, int number){
        switch(number){
            case 1:{
                c1.setVisible(true);
                break;
            }
            case 2: {
                c1.setVisible(true); c2.setVisible(true);
                break;
            }
            case 3: {
                c1.setVisible(true); c2.setVisible(true); c3.setVisible(true);
                break;
            }
            case 4: {
                c1.setVisible(true); c2.setVisible(true); c3.setVisible(true); c4.setVisible(true);
            }
        }
    }

    static void resetLabelVis(JLabel c1,JLabel c2, JLabel c3, JLabel c4){
        c1.setVisible(false); c2.setVisible(false); c3.setVisible(false); c4.setVisible(false);
    }

    static boolean checkHS(int highScore){
        File file = new File("src\\Highscore.txt");
        if (!file.exists()) {
            return true;
        }
        else{
            try {
                BufferedReader reader = new BufferedReader(new FileReader("src\\Highscore.txt"));
                String line = reader.readLine();
                if (highScore <= Integer.parseInt( line.substring(line.lastIndexOf("]")+1,line.indexOf("(")) )){
                    return true;
                }
                return false;
            }catch (IOException ex){
                System.err.println("ERROR reading scores from file");
            }
        }
        return false;
    }

    static void highScoreWriter(int highScore,String name){
        try {
            File file = new File("src\\Highscore.txt");
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                bw.write("["+name+"]"+highScore+"("+timeStamp+")");
                bw.close();
            }
            else{
                BufferedReader reader = new BufferedReader(new FileReader("src\\Highscore.txt"));
                String line = reader.readLine();
                if (highScore <= Integer.parseInt( line.substring(line.lastIndexOf("]")+1,line.indexOf("(")) )){
                    PrintWriter writer = new PrintWriter("src\\Highscore.txt", "UTF-8");
                    String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                    writer.println("["+name+"]"+highScore+"("+timeStamp+")");
                    writer.close();
                }
                reader.close();
            }
        } catch (IOException ex) {
            System.err.println("ERROR reading scores from file");
        }
    }

    static String returnHs(){
        try {
            File file = new File("src\\Highscore.txt");
            if (!file.exists()) {
                return "No high scores yet!";
            } else {
                BufferedReader reader = new BufferedReader(new FileReader("src\\Highscore.txt"));
                String line = reader.readLine();
                String fixedLine= line.substring(1, line.lastIndexOf("]")) +": "
                        +line.substring(line.lastIndexOf("]")+1,line.indexOf("(")) + "\n"
                        +line.substring(line.indexOf("("));
                return fixedLine;
            }
        } catch(IOException ex) {
            System.err.println("ERROR reading scores from file");
        }
        return "No high scores yet! ";
    }

    static void resetHs(){
            File file = new File("src\\Highscore.txt");
            if(file.delete()){
                System.out.println("Deleted successfully.");
            };
    }
}

