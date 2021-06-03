package com.company;

public class Main {

    /**
     * Cesar: en, de
     * DT: en
     * Magic: DONE
     */


    public static void main(String[] args) {

        //x - rows
        //y - cols

        // ===== Двойная перестановка =====
        CryptoDoubleTransportation doubleTransportation = new CryptoDoubleTransportation();

        var sentenceEncryptDT = "ВРЕМЕНА МЕНЯЮТСЯ";
        var xEncryptDT = 4;
        var yEncryptDT = 4;
        int[] ySequenceEncryptDT = {};
        int[] xSequenceEncryptDT = {};

        System.out.println(doubleTransportation.encrypt(sentenceEncryptDT, xEncryptDT, yEncryptDT, ySequenceEncryptDT, xSequenceEncryptDT));

        var sentenceDecryptDT = "КВЯААСА_|_В_Д_АВЧ";
        var xDecryptDT = 4;
        var yDecryptDT = 4;
        int[] ySequenceDecryptDT = {3, 2, 1, 4};
        int[] xSequenceDecryptDT = {1, 3, 4, 2};

        System.out.println(doubleTransportation.decrypt(sentenceDecryptDT, xDecryptDT, yDecryptDT, ySequenceDecryptDT, xSequenceDecryptDT));


        // ===== Магический квадрат =====
        CryptoMagicSquare magicSquare = new CryptoMagicSquare();

        var sentenceDeMagicSquare = "ЮЯВОЫТ_СОЛЕТДАГЕ";
        int[] sequenceDeMagicSquare = {7, 12, 1, 14, 2, 13, 8, 11, 16, 3, 10, 5, 9, 6, 15, 4};
        var sizeDeMagicSquare = 4;

        System.out.println(magicSquare.decrypt(sentenceDeMagicSquare, sequenceDeMagicSquare, sizeDeMagicSquare));

        var sentenceEnMagicSquare = "ВЫЛЕТАЮ_ДЕСЯТОГО";
        int[] sequenceEnMagicSquare = {7, 12, 1, 14, 2, 13, 8, 11, 16, 3, 10, 5, 9, 6, 15, 4};
        var sizeEnMagicSquare = 4;

        System.out.println(magicSquare.encrypt(sentenceEnMagicSquare, sequenceEnMagicSquare, sizeEnMagicSquare));


        // ===== Цезарь =====
        CryptoCesar cesar = new CryptoCesar();

        var sentenceEncryptCesar = "Мы должны признать очевидное: понимают лишь те, кто хочет понять";
        var keyEncryptCesar = 25;
        var wordKeyEncryptCesar = "";

        System.out.println(cesar.encrypt(sentenceEncryptCesar, keyEncryptCesar, wordKeyEncryptCesar));
    }
}
