/**
 * Name : Omar Atef Mohamed
 * ID : 20190356
 * Major : CS
 * Group : S2
 * */

import java.util.ArrayList;
import java.util.Scanner;
// AABABABBCBCBBBBBBBBBBBBBBBBBCBCBCBBBCABBA
public class LZ77 {
    private ArrayList<Tag>lz;
    LZ77(){
         lz = new ArrayList<>();
    }

    public void compress(String data){
        System.out.println("**************************\nCompressing");
        String temp="";
        String matched="";
        int pointer=0;
        for (int i = 0 ; i<data.length();i++){
            if (i==0){
                Tag t = new Tag(0,0,data.charAt(i));
                lz.add(t);
                System.out.println(t);
            }else{
                matched=data.substring(0,i);
                for (int j =  i ;j<data.length();j++){
                    temp+=data.charAt(j);
                    if (!matched.contains(temp)){
                        if (temp.length()==1 ){
                            Tag t = new Tag(0,0,temp.charAt(0));
                            lz.add(t);
                            System.out.println(t);
                            temp="";
                            //matched="";
                            break;
                        }
                        if (temp.contains("\n")){
                            Tag t = new Tag(j-2-matched.lastIndexOf(temp.charAt(0)), temp.length() - 2, temp.charAt(temp.length() - 2));
                            lz.add(t);
                            System.out.println(t);
                            i+=temp.length()-1;
                            temp = "";
                            //matched = "";
                            break;                        }
                        else {
                            pointer=matched.lastIndexOf(temp.substring(0,temp.length()-1));
                            Tag t = new Tag(i-pointer, temp.length() - 1, temp.charAt(temp.length() - 1));
                            lz.add(t);
                            System.out.println(t);
                            i+=temp.length()-1;
                            temp = "";
                            //matched = "";
                            break;
                        }
                    }

                }
            }
        }

    }

    public void decompress(ArrayList<Tag> lz){
        System.out.println("**************************\nDecompressing");
        StringBuilder decomp = new StringBuilder("");
        for(Tag tag : lz){
            int start = decomp.length() - tag.pointer;
            if(tag.pointer == 0){
                decomp.append(tag.nextChar);
                continue;
            }
            for(int currentTag = 0; currentTag < tag.length; currentTag++){
                decomp.append(decomp.charAt(start+currentTag));
            }
            decomp.append(tag.nextChar);
        }
        System.out.println(decomp);
    }

    public static void main(String[] args) {
        LZ77 lz=new LZ77();
        String data = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Enter data you want to compress (pls enter extra space at the end of data)\nleave blank for the slides example");
        data=in.nextLine();
        data+=" ";
        if (data.equals(" "))
            data="ABAABABAABBBBBBBBBBBBA\n";
        //String data;
        //System.out.println("Enter data you want to compress (pls enter extra space at the end of data)");
       // data=in.nextLine();

        // String data = "AAAB";
        lz.compress(data);
        lz.decompress(lz.lz);
    }
class Tag {
    int pointer;
    int length;
    char nextChar;

    public Tag(int p, int l, char n){
        pointer = p;
        length = l;
        nextChar = n;
    }
    @Override
    public String toString() {
        return "<"+ pointer +","+length+","+nextChar+">";
    }
}
}