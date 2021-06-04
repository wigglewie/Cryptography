package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class CryptoCesar {

    String cryptoType = getClass().getSimpleName() + ": ";

    char[] chars = new char[33];

    private List<Character> resultAsList = new ArrayList<>();
    private List<Character> sequence;

    private final String alphabetString = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private List<Character> alphabet = alphabetString.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

    public String encrypt(String sentence, int key, String wordKey) {

        var sentenceToEncrypt = formatForEncryption(sentence.toUpperCase());
        chars = sentenceToEncrypt.toCharArray();

        sequence = new ArrayList<>(33);
        for (char ignored : alphabet) {
            sequence.add('0');
        }

        if (wordKey == null || wordKey.isBlank()) {
            sequence = getSequenceWithoutWordKey(key);
        } else {
            sequence = getSequenceWithWordKey(key, wordKey);
        }

        return cryptoType + getEncryptionResult(sequence);
    }

    public String decrypt(String sentence, int key, String wordKey) {

        var sentenceToEncrypt = formatForDecryption(sentence.toUpperCase());
        chars = sentenceToEncrypt.toCharArray();

        sequence = new ArrayList<>(33);
        for (char ignored : alphabet) {
            sequence.add('0');
        }

        if (wordKey == null || wordKey.isBlank()) {
            sequence = getSequenceWithoutWordKey(key);
        } else {
            sequence = getSequenceWithWordKey(key, wordKey);
        }

        return cryptoType + getDecryptionResult(sequence);
    }

    private String getDecryptionResult(List<Character> sequence) {

        var result = new StringBuilder();
        alphabet = alphabetString.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        resultAsList = new ArrayList<>();

        for (char ch : chars) {
            int index = sequence.indexOf(ch);
            try {
                resultAsList.add(alphabet.get(index));
            } catch (Exception e) {
                resultAsList.add('_');
            }
        }

        for (char ch : resultAsList) {
            if (ch == '_') {
                result.append(' ');
                continue;
            }
            result.append(ch);
        }

        return result.toString();
    }

    private List<Character> getSequenceWithWordKey(int key, String wordKey) {

        Set<Character> uniqueWordKey = wordKey.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(LinkedHashSet::new));

        var step = key;
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
            try {
                sequence.set(step, alphabet.get(i));
                alphabet.set(i, null);
                step++;
            } catch (Exception e) {
                break;
            }
        }

        alphabet.removeIf(Objects::isNull);

        for (int i = 0; i < key; i++) {
            sequence.set(i, alphabet.get(i));
        }

        return sequence;
    }



    private List<Character> getSequenceWithoutWordKey(int key) {

        sequence = new ArrayList<>();

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

        return sequence;
    }

    private String getEncryptionResult(List<Character> sequence) {

        var result = new StringBuilder();
        alphabet = alphabetString.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        resultAsList = new ArrayList<>();

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

        return result.toString();
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
        sentence = sentence
                .replace("|", "");
        return sentence;
    }
}
