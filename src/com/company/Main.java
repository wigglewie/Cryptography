package com.company;

public class Main {

    public static void main(String[] args) {

        var sentenceTask1 = "ТООГ_СШЕЕЮТАПЛРИ";
        var x = 4;
        var y = 4;
        var xSequenceValue = 1342;
        var ySequenceValue = 4321;
        System.out.println(decryptDoubleTransportation(sentenceTask1, x, y, xSequenceValue, ySequenceValue));

        var sentenceTask2 = "ЕВИНЖРЫЕА";
        var magicSquareSequence = 618753294;
        var magicSquareSize = 3;
        System.out.println(decryptMagicSquare(sentenceTask2, magicSquareSequence, magicSquareSize));

        var sentenceTask3 = "Мы должны признать очевидное: понимают лишь те, кто хочет понять";
        var numeralKey = 25;
        var wordKey = "";
        System.out.println(task3(sentenceTask3, numeralKey, wordKey));
    }

    private static String task3(String sentenceOriginal, int numeralKey, String wordKey) {
        var result = "";

        char[] chars = sentenceOriginal.toUpperCase().toCharArray();
        var stringBuilder = new StringBuilder();
        for (char ch : chars) {
            if (!(ch == ',' || ch == '.' || ch == '?' || ch == '!' || ch == ':' || ch == ';')) {
                if (ch == ' '){
                    stringBuilder.append('_');
                    continue;
                }
                stringBuilder.append(ch);
            }
        }
        var sentenceToEncrypt = stringBuilder.toString();

        if (wordKey.isBlank()) {
            return encryptWithoutWordKey(sentenceToEncrypt, numeralKey);
        }

        //TODO encrypt with word key

        return result;
    }

    private static String encryptWithoutWordKey(String sentence, int numeralKey) {
        var result = "";




        return result;
    }

    //двойная перестановка
    private static String decryptDoubleTransportation(String sentence, int x, int y, int xSequenceValue, int ySequenceValue) {
        StringBuilder result = new StringBuilder();

        if (sentence.contains("|")) {
            sentence = sentence.replace("|", "");
        }

        char[] sentenceAsCharArray = sentence.toCharArray();

        char[][] matrix = new char[y][x];
        char[][] matrixProperX = new char[y][x];
        char[][] matrixProperY = new char[y][x];

        int[] xSequence = new int[x];
        for (int j = xSequence.length - 1; j >= 0; j--) {
            xSequence[j] = xSequenceValue % 10;
            xSequenceValue /= 10;
        }

        int[] ySequence = new int[x];
        for (int j = ySequence.length - 1; j >= 0; j--) {
            ySequence[j] = ySequenceValue % 10;
            ySequenceValue /= 10;
        }

        if (sentenceAsCharArray.length < x * y && sentenceAsCharArray.length > x * y) {
            return "error";
        }

        var step = 0;
        for (int j = 0; j < y; j++) {
            for (int z = 0; z < x; z++) {
                matrix[j][z] = sentenceAsCharArray[step];
                step++;
            }
        }

        for (int j = 0; j < y; j++) {
            for (int z = 0; z < x; z++) {
                matrixProperX[j][z] = matrix[j][xSequence[z] - 1];
            }
        }

        for (int j = 0; j < y; j++) {
            for (int z = 0; z < x; z++) {
                matrixProperY[j][z] = matrixProperX[ySequence[j] - 1][z];
            }
        }

        for (int j = 0; j < y; j++) {
            for (int z = 0; z < x; z++) {
                result.append(matrixProperY[j][z]);
            }
        }
        return result.toString();
    }

    //магический квадрат
    private static String decryptMagicSquare(String sentence, int magicSquareSequence, int magicSquareSize) {
        var result = new StringBuilder();

        char[] chars = sentence.toCharArray();
        char[] resultArray = new char[magicSquareSize * magicSquareSize];

        int[] sequence = new int[magicSquareSize * magicSquareSize];
        for (int j = sequence.length - 1; j >= 0; j--) {
            sequence[j] = magicSquareSequence % 10;
            magicSquareSequence /= 10;
        }

        var step = 0;
        int[][] matrix = new int[magicSquareSize][magicSquareSize];
        for (int i = 0; i < magicSquareSize; i++) {
            for (int j = 0; j < magicSquareSize; j++) {
                matrix[i][j] = sequence[step];
                step++;
            }
        }

        step = 0;
        for (int i = 0; i < magicSquareSize; i++) {
            for (int j = 0; j < magicSquareSize; j++) {
                resultArray[matrix[i][j] - 1] = chars[step];
                step++;
            }
        }

        for (char ch : resultArray) {
            result.append(ch);
        }

        return result.toString();
    }
}
