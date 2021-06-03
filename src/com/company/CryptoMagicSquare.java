package com.company;

public class CryptoMagicSquare {

    String cryptoType = getClass().getSimpleName() + ": ";

    public String encrypt(String sentence, int[] sequence, int size) {
        var result = new StringBuilder();

        sentence = formatSentence(sentence);

        if (!isValid(sentence, sequence, size)) {
            return cryptoType + "error";
        }

        char[] chars = sentence.toCharArray();
        char[] resultArray = new char[size * size];

        var step = 0;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = sequence[step];
                step++;
            }
        }

        step = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultArray[step] = chars[matrix[i][j] - 1];
                step++;
            }
        }

        for (char ch : resultArray) {
            result.append(ch);
        }

        return cryptoType + result;
    }

    public String decrypt(String sentence, int[] sequence, int size) {
        var result = new StringBuilder();

        sentence = sentence.replace("|", "");

        if (!isValid(sentence, sequence, size)) {
            return cryptoType + "error";
        }

        char[] chars = sentence.toCharArray();
        char[] resultArray = new char[size * size];

        var step = 0;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = sequence[step];
                step++;
            }
        }

        step = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultArray[matrix[i][j] - 1] = chars[step];
                step++;
            }
        }

        for (char ch : resultArray) {
            result.append(ch);
        }

        return cryptoType + result;
    }

    private boolean isValid(String sentence, int[] sequence, int size) {
        return sequence.length / size == size && sentence.length() == size * size;
    }

    private String formatSentence(String sentence) {
        sentence = sentence
                .replace(" ", "_")
                .replace(",", "")
                .replace(".", "")
                .replace(";", "")
                .replace(":", "")
                .replace("!", "")
                .replace("?", "");
        return sentence;
    }
}
