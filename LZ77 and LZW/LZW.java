/**
 * Name : Omar Atef Mohamed
 * ID : 20190356
 * Major : CS
 * Group : S2
 * */

import java.util.ArrayList;
import java.util.Scanner;

public class LZW {

    ArrayList<String> table;
    ArrayList<Integer> codes;
    public LZW(){
        table=new ArrayList<String>();
        codes=new ArrayList<Integer>();
    }
    public void compress(String data){
        System.out.println("***************************\nCompressing");
        String temp = "";
        int index =0;

        for (int i = 0 ;i<=127;i++){
            table.add(Character.valueOf((char) i).toString());
        }
        for (int i = 0 ; i<data.length();i++){
           temp+=data.charAt(i);
           if (matched(temp,table)!=-1)
               index=matched(temp,table);
           else {
               table.add(temp);
               codes.add(index);
               temp="";
               i--;
           }
        }

        System.out.println("Codes count = " + codes.size());
        for (int i =0; i<codes.size();i++){
            System.out.println(codes.get(i)+" "+ table.get(codes.get(i)));
        }

    }

    public String decompress(ArrayList<Integer>codes){
        System.out.println("*******************************\nDecompressing");

        String decomp = "";
        for (int i = 0 ; i<codes.size();i++){
            decomp+=table.get(codes.get(i));
        }
        return decomp;

    }
    public int matched (String data , ArrayList<String>table){
        for (int i = 0 ; i<table.size();i++){
            if (table.contains(data))
                return table.lastIndexOf(data);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.print("Enter data you want to decompress (Leave empty to use the slides example) :- ");
        Scanner in = new Scanner(System.in);
        String data = in.nextLine();
        if (data.equals(""))
            data ="ABAABABBAABAABAAAABABBBBBBBB ";
        LZW lz = new LZW();
        lz.compress(data);
        System.out.println(lz.decompress(lz.codes));
    }
}
