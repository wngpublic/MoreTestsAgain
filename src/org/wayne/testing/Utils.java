package org.wayne.testing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class Utils {

    char [] charset;
    Random rand = new Random();

    static void print(String f, Object ...a) {
        System.out.printf(f, a);
    }

    static void p(String f, Object ...a) {
        print(f, a);
    }

    Utils() {
        reset();
    }
    
    public int [][] copy(int [][] a) {
        int numRows = a.length;
        int numCols = a[0].length;
        int [][] o = new int[numRows][numCols];
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                o[i][j] = a[i][j];
            }
        }
        return o;
    }
    
    public void copyList(List<Integer> src, List<Integer> dst) {
        dst.clear();
        for(Integer i: src)
            dst.add(i);
    }
    
    public List<List<Integer>> copy(List<List<Integer>> list) {
        int numRows = list.size();
        List<List<Integer>> o = new ArrayList<>();
        for(int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(list.get(i));
            o.add(row);
        }
        return o;
    }
    
    public int [][] 
    initMatrixArray(int numRows, int numCols, int val) {
        int [][] a = new int[numRows][numCols];
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                a[i][j] = val;
            }
        }
        return a;
    }
    
    public void printMatrix(int [][] a) {
        p("PRINT MATRIX\n");
    }
    
    public void printMatrix(List<List<Integer>> list) {
        p("PRINT MATRIX\n");
        int numRows = list.size();
        for(int i = 0; i < numRows; i++) {
            List<Integer> row = list.get(i);
            for(int j = 0; j < row.size(); j++) {
                p("%3d ", row.get(j));
            }
            p("\n");
        }
    }
    
    public List<Integer>
    initList(int numCols, int val) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < numCols; i++)
            list.add(val);
        return list;
    }
    
    public List<List<Integer>> 
    initMatrixList(int numRows, int numCols, int val) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < numRows; i++) {
            List<Integer> row = initList(numCols, val);
            list.add(row);
        }
        return list;
    }
    
    public List<List<Integer>> 
    getRandomMatrix(int numRows, int numCols, int min, int max) {
        List<List<Integer>> list = new ArrayList<>(numRows);
        int diff = max - min + 1;
        for(int i = 0; i < numRows; i++) {
            List<Integer> cols = new ArrayList<>(numCols);
            for(int j = 0; j < numCols; j++) {
                int v = rand.nextInt(diff) + min;
                cols.add(v);
            }
            list.add(cols);
        }
        return list;
    }
    
    public List<List<Integer>>
    matrixToList(int [][] a) {
        List<List<Integer>> list = new ArrayList<>();
        int numRows = a.length;
        int numCols = a[0].length;
        for(int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < numCols; j++) {
                row.add(a[i][j]);
            }
            list.add(row);
        }
        return list;
    }
    
    public int [][]
    listToMatrix(List<List<Integer>> list) {
        int numRows = list.size();
        int numCols = list.get(0).size();
        int [][] a = new int[numRows][numCols];
        for(int i = 0; i < numRows; i++){ 
            List<Integer> row = list.get(i);
            for(int j = 0; j < numCols; j++) {
                a[i][j] = row.get(j);
            }
        }
        return a;
    }
    
    public String getRandString(String stringSet, int sizeStr) {
        StringBuilder sb = new StringBuilder();
        int sizeSet = stringSet.length();
        for(int j = 0; j < sizeStr; j++) {
            int idx = rand.nextInt(sizeSet);
            sb.append(stringSet.charAt(idx));
        }
        String word = sb.toString();
        return word;
    }
    
    public String getRandString(int sizeStr) {
        StringBuilder sb = new StringBuilder();
        int sizeSet = charset.length;
        for(int i = 0; i < sizeStr; i++) {
            int idx = rand.nextInt(sizeSet);
            sb.append(charset[idx]);
        }
        String word = sb.toString();
        return word;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    Comparable min(Comparable ...c) {
        if(c.length == 0) {
            return null;
        }
        Comparable min = c[0];
        for(Comparable v: c) {
            if(min.compareTo(v) > 0) {
                min = v;
            }
        }
        return min;
    }
    
    @SuppressWarnings({ "rawtypes" })
    void printMinInts(Comparable ... c) {
        Comparable min = min(c);
        if(min == null) {
            p("null\n");
            return;
        }
        p("min:%2d; ", min);
        for(Comparable v: c) {
            p("%d ", v);
        }
        p("\n");
    }
    
    Integer getIntNotInList(List<Integer> l, int min, int max) {
        // gets a value that is within min:max and not in l, null if not found.
        Set<Integer> set = new HashSet<>();
        for(Integer v: l) {
            set.add(v);
        }
        for(int i = min; i < max; i++) {
            if(!set.contains(i)) {
                return i;
            }
        }
        return null;
    }
    
    void setCharset(String s) {
        charset = s.toCharArray();
    }
    
    char [] getCharset() {
        return charset;
    }
    
    void reset() {
        charset = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    }
    
    void shuffle(char [] a, int numShuffles) {
        for(int i = 0; i < numShuffles; i++) {
            shuffle(a);
        }
    }
    
    void shuffle(int [] a, int numShuffles) {
        for(int i = 0; i < numShuffles; i++) {
            shuffle(a);
        }
    }
    
    void shuffle(char [] a) {
        for(int i = a.length; i > 0; i--) {
            int idx = rand.nextInt(i);
            char c = a[idx];
            a[idx] = a[i-1];
            a[i-1] = c;
        }
    }
    
    void shuffle(int [] a) {
        for(int i = a.length; i > 0; i--) {
            int idx = rand.nextInt(i);
            int c = a[idx];
            a[idx] = a[i-1];
            a[i-1] = c;
        }        
    }
    
    /**
    * normal distribution of 0 is -10:10
    * 10 distribution is -50:50
    * 2 distribution is -10:10
    */
    int getIntNormal(int min, int max) {
        double range = (max - min + 1)/10.0;
        int median = (min + max) / 2;
        return getIntNormal(range, median, min, max);
    }
    
    int getIntNormal(double range, int median, int min, int max) {
        /*
        * double * 1    -> -5:5
        * double * 10   -> -50:50
        * double * 100  -> -500:500
        */
        boolean found = false;
        for(int i = 0; i < 1000 && !found; i++) {
            int v = (int)(rand.nextGaussian() * range) + median;
            if(min <= v && v <= max) {
                return v;
            }
        }
        return median;
    }
    
    List<Integer> getIntListNormal(int size, int min, int max) {
        /*
        * double * median = 1    -> -5:5
        * double * median = 10   -> -50:50
        * double * median = 100  -> -500:500
        * 
        */
        List<Integer> list = new ArrayList<>();
        double range = (max - min + 1)/10.0;
        int median = (min + max) / 2;
        for(int i = 0; i < size; i++) {
            int v = getIntNormal(range, median, min, max);
            list.add(v);
        }
        return list;
    }
    
    int getInt(int min, int max) {
        int range = max - min + 1;
        return rand.nextInt(range) + min;
    }
    
    int [] getAryInt(int sz, int min, int max) {
        int [] a = new int[sz];
        int range = max - min + 1;
        for(int i = 0; i < sz; i++) {
            a[i] = rand.nextInt(range) + min;
        }
        return a;
    }
    
    List<Integer> getListInt(int sz, int min, int max) {
        List<Integer> l = new ArrayList<>();
        int range = max - min + 1;
        for(int i = 0; i < sz; i++) {
            int v = rand.nextInt(range) + min;
            l.add(v);
        }
        return l;
    }
    
    char [] getAryChar(int sz) {
        char [] a = new char[sz];
        int range = charset.length;
        for(int i = 0; i < sz; i++) {
            a[i] = charset[rand.nextInt(range)];
        }
        return a;
    }
    
    void printArray(int [] a, int idxB, int idxE) {
        for(int i = idxB; i <= idxE; i++) {
            print("%3d ", a[i]);
        }
        print("\n");
    }
    
    void copyArray(int [] a, int [] acopy) {
        for(int i = 0; i < a.length; i++) {
            acopy[i] = a[i];
        }
    }

    void copyArray(int [] a, int [] acopy, int idxBeg, int idxEnd) {
        for(int i = idxBeg; i <= idxEnd; i++) {
            acopy[i] = a[i];
        }
    }
    
    void swap(int [] a, int i, int j) {
        if(i < 0 || i >= a.length || j < 0 || j >= a.length) {
            return;
        }
        int c = a[i];
        a[i] = a[j];
        a[j] = c;
    }

    void swap(char [] a, int i, int j) {
        if(i < 0 || i >= a.length || j < 0 || j >= a.length) {
            return;
        }
        char c = a[i];
        a[i] = a[j];
        a[j] = c;
    }
}