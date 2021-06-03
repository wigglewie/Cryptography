package com.company;

public class CryptoDoubleTransportation {

    String cryptoType = getClass().getSimpleName() + ": ";

    char[] sentenceAsCharArray;

    char[][] matrix;
    char[][] matrixProperX;
    char[][] matrixProperY;

    public String encrypt(String sentence, int row, int col, int[] rowsSequence, int[] colsSequence) {
        var result = new StringBuilder();

        sentence = formatForEncryption(sentence);

        if (!isValid(sentence, row, col, rowsSequence, colsSequence)) {
            return cryptoType + "error";
        }

        matrix = new char[row][col];
        matrixProperX = new char[row][col];
        matrixProperY = new char[row][col];

        var step = 0;
        for (int x = 0; x < row; x++) {
            for (int j = 0; j < col; j++) {
                matrix[x][j] = sentenceAsCharArray[step];
                step++;
            }
        }

        for (int x = 0; x < row; x++) {
            for (int j = 0; j < col; j++) {
                matrixProperX[x][colsSequence[j] - 1] = matrix[x][j];
            }
        }

        for (int x = 0; x < row; x++) {
            for (int j = 0; j < col; j++) {
                matrixProperY[rowsSequence[x] - 1][j] = matrixProperX[x][j];
            }
        }

        for (int x = 0; x < row; x++) {
            for (int j = 0; j < col; j++) {
                result.append(matrixProperY[x][j]);
            }
        }
        return cryptoType + result;
    }

    public String decrypt(String sentence, int row, int col, int[] rowsSequence, int[] colsSequence) {
        var result = new StringBuilder();

        sentence = formatForDecryption(sentence);

        if (!isValid(sentence, row, col, rowsSequence, colsSequence)) {
            return cryptoType + "error";
        }

        matrix = new char[row][col];
        matrixProperX = new char[row][col];
        matrixProperY = new char[row][col];

        var step = 0;
        for (int x = 0; x < row; x++) {
            for (int j = 0; j < col; j++) {
                matrix[x][j] = sentenceAsCharArray[step];
                step++;
            }
        }

        for (int x = 0; x < row; x++) {
            for (int j = 0; j < col; j++) {
                matrixProperX[x][j] = matrix[x][colsSequence[j] - 1];
            }
        }

        for (int x = 0; x < row; x++) {
            for (int j = 0; j < col; j++) {
                matrixProperY[x][j] = matrixProperX[rowsSequence[x] - 1][j];
            }
        }

        for (int x = 0; x < row; x++) {
            for (int j = 0; j < col; j++) {
                result.append(matrixProperY[x][j]);
            }
        }
        return cryptoType + result;
    }

    private String formatForEncryption(String sentence) {
        sentence = sentence
                .replace(" ", "_")
                .replace(",", "")
                .replace(".", "")
                .replace(";", "")
                .replace(":", "")
                .replace("!", "")
                .replace("?", "")
                .replace("—", "")
                .replace("-", "");
        return sentence;
    }

    private String formatForDecryption(String sentence) {
        sentence = sentence.replace("|", "");
        return sentence;
    }

    private boolean isValid(String sentence, int row, int col, int[] rowSequence, int[] colSequence) {
        sentenceAsCharArray = sentence.toCharArray();
        return sentenceAsCharArray.length == col * row && rowSequence.length == row && colSequence.length == col;
    }


}
