package com.company;

public class Main {

    public static void main(String[] args) {

        String sentence = "ТООГ_СШЕЕЮТАПЛРИ";
        int x = 4;
        int y = 4;
        int xSequenceValue = 1342;
        int ySequenceValue = 4321;
        System.out.println(task1(sentence, x, y, xSequenceValue, ySequenceValue));

    }

    private static String task1(String sentence, int x, int y, int xSequenceValue, int ySequenceValue) {
        StringBuilder result = new StringBuilder();

        char[] sentenceAsCharArray = sentence.toCharArray();

        char[][] matrix = new char[y][x];
        char[][] matrixProperX = new char[y][x];
        char[][] matrixProperY = new char[y][x];

        //getting x sequence
        int[] xSequence = new int[x];
        for (int j = xSequence.length - 1; j >= 0; j--) {
            xSequence[j] = xSequenceValue % 10;
            xSequenceValue /= 10;
        }

        //getting y sequence
        int[] ySequence = new int[x];
        for (int j = ySequence.length - 1; j >= 0; j--) {
            ySequence[j] = ySequenceValue % 10;
            ySequenceValue /= 10;
        }

        if (sentenceAsCharArray.length < x * y && sentenceAsCharArray.length > x * y) {
            return "error";
        }

        //getting matrix filled with values
        int step = 0;
        for (int j = 0; j < y; j++) {
            for (int z = 0; z < x; z++) {
                matrix[j][z] = sentenceAsCharArray[step];
                step++;
            }
        }

        //filtering X
        for (int j = 0; j < y; j++) {
            for (int z = 0; z < x; z++) {
                matrixProperX[j][z] = matrix[j][xSequence[z] - 1];
            }
        }

        //filtering Y
        for (int j = 0; j < y; j++) {
            for (int z = 0; z < x; z++) {
                matrixProperY[j][z] = matrixProperX[ySequence[j] - 1][z];
            }
        }

        //getting result
        for (int j = 0; j < y; j++) {
            for (int z = 0; z < x; z++) {
                result.append(matrixProperY[j][z]);
            }
        }
        return result.toString();
    }
}
