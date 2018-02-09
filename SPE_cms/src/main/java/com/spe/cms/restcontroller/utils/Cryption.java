package com.spe.cms.restcontroller.utils;

public class Cryption {

    public static String encrypt(String string)
    {
        String enc = "";
        for (int i=0; i<string.length(); i++)
        {
            char c = string.charAt(i);
            int x = ((int) c);
            x = x + 3;
            c = ((char) x);
            enc += c;
        }
        return enc;
    }

    public static String decrypt(String string)
    {
        String dec = "";
        for (int i=0; i<string.length(); i++)
        {
            char c = string.charAt(i);
            int x = ((int) c);
            x = x - 3;
            c = ((char) x);
            dec += c;
        }
        return dec;
    }

}
