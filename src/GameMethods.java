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
}