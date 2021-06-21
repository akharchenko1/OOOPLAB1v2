package com.company;

import com.sun.source.tree.Tree;

import java.util.*;
import java.io.*;
import java.lang.*;
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a File adress: ");
        String File = in.nextLine();
        in.close();
        Integer allCount = 0;
        BufferedReader reader = null;
        try
        {
            Map<String,Integer> word = new HashMap<String, Integer>();
            Comparator <Words> wcomp = new WordComparator().thenComparing(new WordKeyComparator());
            TreeSet<Words> nword = new TreeSet<Words>(wcomp);
            reader = new BufferedReader(new FileReader(File));

            String s;
            while((s=reader.readLine())!=null){
                StringBuilder strBuilder = new StringBuilder(s);
                ArrayList<String> words = new ArrayList<String>();
                int predrazd = 0;
                for (int i = 0; i < s.length(); ++i) {
                    if (Character.isLetterOrDigit(s.charAt(i)) == false) {
                        if (i != 0 && Character.isLetterOrDigit(s.charAt(i-1)) == true) {
                            words.add(s.substring(predrazd,i));
                        }
                        predrazd = i;
                    }
                }

                for(String str : words){
                    if(word.containsKey(str))
                    {
                        word.put(str,word.get(str) + 1);
                    }
                    else
                    {
                        word.put(str, 1);
                    }
                    ++allCount;
                }
            }
            reader.close();

            for(Map.Entry<String, Integer> item : word.entrySet()){
                nword.add(new Words(item.getKey(),item.getValue()));
            }
            NavigableSet<Words> navSet = nword.descendingSet();
            try(PrintStream printStream = new PrintStream("result.csv"))
            {
                // записываем значения
                 for(Words wor : navSet) {

                                System.out.println( wor.getCount() + " " + wor.getNkey());
                     printStream.printf(" %s, %d, %f%s \n", wor.getNkey(), wor.getCount(), 100.0*wor.getCount()/allCount,"%");
                 }
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }

            word.clear();
            navSet.clear();
            nword.clear();
        }

        catch (IOException e)
        {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally
        {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }




    }
}

