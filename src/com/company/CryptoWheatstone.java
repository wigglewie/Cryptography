package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class CryptoWheatstone {

    String cryptoType = getClass().getSimpleName() + ": ";

    private final String punctuationMarksAsString = ",.!?-—:;";
    private List<Character> punctuationMarks = punctuationMarksAsString.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    private final String alphabetAsString = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private Set<Character> alphabet = new LinkedHashSet<>();
    private List<Character> sequence = new ArrayList<>();

    public String encrypt(String sentence) {

        alphabet = alphabetAsString.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(LinkedHashSet::new));
        alphabet.add('_');

        char[] chars = sentence.toCharArray();
        for (char ch : chars) {
            if (punctuationMarks.contains(ch)) {
                alphabet.add(ch);

            }
        }

        int i = 35 - alphabet.size();
        if (i == 0) {
            return doMagic(sentence);
        }
        if (i < 0) {
            return cryptoType + "remove " + Math.abs(i) + " punctuation marks from the sentence";
        }

        alphabet.addAll(punctuationMarks);
        sequence = new ArrayList<>(alphabet);

        if (alphabet.size() > 35) {
            sequence.subList(35, alphabet.size()).clear();
        }

        return cryptoType + doMagic(sentence);
    }

    private String doMagic(String sentence) {

        var result = new StringBuilder();

        sentence = sentence.replace(' ', '_');
        char[] chars = sentence.toUpperCase().toCharArray();
        List<List> bigrams = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            List<Character> list;
            try {
                list = new ArrayList<>();
                list.add(chars[i]);
                list.add(chars[i + 1]);
                bigrams.add(list);
                i++;
            } catch (Exception e) {
                list = new ArrayList<>();
                list.add(chars[i]);
                list.add('Ъ');
                bigrams.add(list);
                break;
            }
        }

//        Collections.shuffle(sequence);
        shuffleInProperWay1();

        char[][] matrix1 = new char[7][5];
        int step = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                matrix1[i][j] = sequence.get(step);
                step++;
            }
        }

//        Collections.shuffle(sequence);
//        Collections.reverse(sequence);
        shuffleInProperWay2();

        char[][] matrix2 = new char[7][5];
        step = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                matrix2[i][j] = sequence.get(step);
                step++;
            }
        }

        for (var list : bigrams) {
            char o1 = (char) list.get(0);
            char o2 = (char) list.get(1);
            int[] firstIndex = getIndex(matrix1, o1);
            int[] secondIndex = getIndex(matrix2, o2);

            if (firstIndex[0] == secondIndex[0]) {
                result.append(matrix2[firstIndex[0]][firstIndex[1]]).append(matrix1[secondIndex[0]][secondIndex[1]]).append(' ');
                continue;
            }

            result.append(matrix2[firstIndex[0]][secondIndex[1]]).append(matrix1[secondIndex[0]][firstIndex[1]]).append(' ');
        }

        return result.toString();
    }

    private int[] getIndex(char[][] matrix, char o) {
        int[] result = new int[2];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == o) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    private void shuffleInProperWay1() {
        var str = "БЮГЕЗХЧЮЦАВ—ФОКПИБРЯДЭЖУН_ТМЛЪЬШСЫЩ";
        sequence = str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

    }

    private void shuffleInProperWay2() {
        var str = "ЯАЮБОФЩДС-ЛРВЕЖЦЭШМКНУГИЧЫ—ПЗХЬ.ЪТ,";
        sequence = str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    }

}
