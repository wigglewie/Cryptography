package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class CryptoCesar {

    String cryptoType = getClass().getSimpleName() + ": ";

    char[] chars = new char[33];

    private List<Character> resultAsList;
    private List<Character> sequence;

    private String alphabetString = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private List<Character> alphabet = alphabetString.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

    public String encrypt(String sentence, int key, String wordKey) {
        var result = "";

        var sentenceToEncrypt = formatForEncryption(sentence.toUpperCase());
        chars = sentenceToEncrypt.toCharArray();
        resultAsList = new ArrayList<>(33);
        sequence = new ArrayList<>(33);
        for (char ch : alphabet) {
            sequence.add('0');
        }

        if (wordKey == null || wordKey.isBlank()) {
            return encryptWithoutWordKey(key);
        }

        Set<Character> uniqueWordKey = wordKey.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(LinkedHashSet::new));

        Character[] values = new Character[33];

        //FIX
        var step = key;
        for (char ch : uniqueWordKey) {
            values[step] = ch;
            step++;
        }

        step = key;
        for (char ch : uniqueWordKey) {
            try {
                sequence.set(step, ch);
                step++;
            } catch (Exception e) {
                System.out.println();
            }
        }

        for (Character ch : uniqueWordKey) {
            if (alphabet.contains(ch)) {
                alphabet.remove(ch);
            }
        }

        for (int i = 0; i < alphabet.size(); i++) {
            try{
                sequence.set(step, alphabet.get(i));
                alphabet.remove(i);
                step++;
            } catch (Exception e) {
                break;
            }
        }

        return result;
    }

    @NotNull
    private String encryptWithoutWordKey(int key) {
        var result = new StringBuilder();

        var step = 0;
        for (int i = key; i < alphabet.size(); i++) {
            try {
                sequence.add(alphabet.get(i));
                step++;
            } catch (Exception e) {
                break;
            }
        }

        for (int i = 0; i < alphabet.size() - step; i++) {
            try {
                sequence.add(alphabet.get(i));
            } catch (Exception e) {
                break;
            }
        }

        for (char ch : chars) {
            int index = alphabet.indexOf(ch);
            try {
                resultAsList.add(sequence.get(index));
            } catch (Exception e) {
                resultAsList.add('_');
            }
        }

        for (char ch : resultAsList) {
            result.append(ch);
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
}
