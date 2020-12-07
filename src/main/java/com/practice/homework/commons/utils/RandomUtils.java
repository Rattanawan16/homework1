package com.practice.homework.commons.utils;

import com.practice.homework.commons.dto.custom.PageLimit;
import com.practice.homework.commons.dto.custom.PageQuery;
import com.practice.homework.commons.dto.custom.PageResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


public class RandomUtils {
    public static long randomDigits(int digits) {
        if (digits <= 0 || digits > 18) {
            throw new IllegalArgumentException("A long can store the random of 18 full digits, you required: " + digits);
        }

        // use SecureRandom instead for truly random values
        final Random r = new Random();
        long randomNumber = r.nextInt(9) + 1;
        for (int i = 1; i < digits; i++) {
            randomNumber = randomNumber * 10L + (long) r.nextInt(10);
        }
        return randomNumber;
    }

    public static String generateRandomEmail(int length) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
        String email = "";
        String temp = RandomStringUtils.random(length, allowedChars);
        email = temp.substring(0, temp.length() - 9) + "@test.com";
        return email;
    }

    public static String generateRandomPwd(int length) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890" + "!#$%&_+=";
        String pwd = "";
        String temp = RandomStringUtils.random(length, allowedChars);
        pwd = temp.substring(0, temp.length() - 9);
        return pwd;
    }

    public static LocalDate randomDateOfBirth() {
        final int maxAge = 100 * 12 * 31;
        return LocalDate.now().minusDays(new Random().nextInt(maxAge));
    }

    public static int generateRandomIndex(int size) {
        int randomIndex = (int)(Math.random() * (size));
        return randomIndex;
    }

//    public static generateRandom(int min, int max) {
////        int min = 50;
////        int max = 100;
//        //Generate random double value from 50 to 100
////        System.out.println("Random value in double from "+min+" to "+max+ ":");
////        double random_double = Math.random() * (max - min + 1) + min;
//
//        //Generate random int value from 50 to 100
//        System.out.println("Random value in int from "+min+" to "+max+ ":");
//        int random_int = (int)(Math.random() * (max - min + 1) + min);
//        System.out.println(random_int);
//        return random_int;
//    }

    public static void main(String arg[]){
//        System.out.println("Email"+generateRandomEmail(19));

        int user_size = 20;
        for (int i = 1 ; i <= 1000 ; i++) {
            System.out.print((int)(Math.random() * (user_size))+"|");
            if(i%100 == 1) {
                System.out.println();
            }
        }

    }
}
