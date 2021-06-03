package com.company;

public class Main {

    /**
     * Cesar: en, de
     * DT: DONE
     * Magic: DONE
     */


    public static void main(String[] args) {

        //x - rows
        //y - cols

        // ===== Двойная перестановка =====
        CryptoDoubleTransportation doubleTransportation = new CryptoDoubleTransportation();

        var sentenceEncryptDT = "ВРЕМЕНА МЕНЯЮТСЯ";
        var rowsEncryptDT = 4;
        var colsEncryptDT = 4;
        int[] rowsSequenceEncryptDT = {4, 3, 2, 1};
        int[] colsSequenceEncryptDT = {1, 3, 4, 2};

        System.out.println(doubleTransportation.encrypt(sentenceEncryptDT, rowsEncryptDT, colsEncryptDT, rowsSequenceEncryptDT, colsSequenceEncryptDT));

        var sentenceDecryptDT = "ЮЯТСМЯЕНЕ_НАВМРЕ";
        var rowsDecryptDT = 4;
        var colsDecryptDT = 4;
        int[] rowsSequenceDecryptDT = {4, 3, 2, 1};
        int[] colsSequenceDecryptDT = {1, 3, 4, 2};

        System.out.println(doubleTransportation.decrypt(sentenceDecryptDT, rowsDecryptDT, colsDecryptDT, rowsSequenceDecryptDT, colsSequenceDecryptDT));


        // ===== Магический квадрат =====
        CryptoMagicSquare magicSquare = new CryptoMagicSquare();

        var sentenceEnMagicSquare = "ВЫЛЕТАЮ_ДЕСЯТОГО";
        int[] sequenceEnMagicSquare = {7, 12, 1, 14, 2, 13, 8, 11, 16, 3, 10, 5, 9, 6, 15, 4};
        var sizeEnMagicSquare = 4;

        System.out.println(magicSquare.encrypt(sentenceEnMagicSquare, sequenceEnMagicSquare, sizeEnMagicSquare));

        var sentenceDeMagicSquare = "ЮЯВОЫТ_СОЛЕТДАГЕ";
        int[] sequenceDeMagicSquare = {7, 12, 1, 14, 2, 13, 8, 11, 16, 3, 10, 5, 9, 6, 15, 4};
        var sizeDeMagicSquare = 4;

        System.out.println(magicSquare.decrypt(sentenceDeMagicSquare, sequenceDeMagicSquare, sizeDeMagicSquare));


        // ===== Цезарь =====
        CryptoCesar cesar = new CryptoCesar();

        var sentenceEncryptCesar = "Разума лишает не сомнение, а уверенность";
        var keyEncryptCesar = 10;
        var wordKeyEncryptCesar = "КРИПТОГРАФИЯ";

        System.out.println(cesar.encrypt(sentenceEncryptCesar, keyEncryptCesar, wordKeyEncryptCesar));
    }
}
