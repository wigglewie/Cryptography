package com.company;

public class Main {

    public static void main(String[] args) {

//        String sentence = "ТООГ_СШЕЕЮТАПЛРИ";
        String sentence = "_СШЕ";
        int x = 4;
        int y = 1;
        int xSequenceValue = 1342;
        int ySequenceValue = 4321;
        System.out.println(task1(sentence, x, y, xSequenceValue, ySequenceValue));


    }

    private static String task1(String sentence, int x, int y, int xSequenceValue, int ySequenceValue) {
        var result = "";

        char[] sentenceAsCharArray = sentence.toCharArray();

        char[][] matrix = new char[y][x];

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


        int step = 0;

        if (sentenceAsCharArray.length < x * y && sentenceAsCharArray.length > x * y) {
            return "error";
        }

        for (int j = 0; j < y; j++) {

            for (int z = 0; z < x; z++) {
                matrix[j][z] = sentenceAsCharArray[step];
                step++;
            }

        }

        System.out.println();

        char[][] matrixCopy = matrix;

        for (int j = 0; j < y; j++) {
            for (int z = 0; z < x; z++) {
                char temp = matrix[j][xSequence[z] - 1];
                matrix[j][xSequence[z] - 1] = matrix[j][z];
                matrix[j][z] = temp;

                System.out.println();
            }
        }


        return result;
    }
}
