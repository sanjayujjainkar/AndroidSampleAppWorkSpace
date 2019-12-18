package com.example.sampleapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    List<String> intermediatePath = new ArrayList<>();
    private static int MAX_ROW = 3;
    private static int MAX_COL = 3;
    private static List<String> path = new ArrayList<>();
    private static List<List<String>> paths = new ArrayList<List<String>>();
    private static List<Coordinate> targets = new ArrayList<Coordinate>();
    private static int xDest = 2;
    private static int yDest = 2;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void vowelsubstring() {

        String s = "aeioaexaaeuiou";

        // Write your code here
        if(!isValidString(s)) {
            System.out.println("Invalid String");
            return;
        }
        String[] strVowels = s.split("[b-df-hj-np-tv-z]+");
        List<String> listStrVowels = Arrays.asList(strVowels);
        System.out.println(listStrVowels);
        /*return listStrVowels.stream()
                .map(subStringsWithAllVowels(it))
                .reduce(0,(ans,i)-> ans+i);*/

        int count = 0;
        for(String str : listStrVowels) {
            count = count + subStringsWithAllVowels(str);
        }

        System.out.println("Total substrings:"+count);

    }

    @Test
    public void testminMoves() {
        List<List<Integer>> array2d = new ArrayList<List<Integer>>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        list1.add(0); list1.add(1); list1.add(1);
        list2.add(1); list2.add(2); list2.add(0);
        list3.add(1); list3.add(0); list3.add(0);
        array2d.add(list1); array2d.add(list2); array2d.add(list3);
        minMoves(array2d, 2, 2);
    }


    public static int minMoves(List<List<Integer>> maze, int x, int y) {
        MAX_ROW = maze.size();
        MAX_COL = maze.get(0).size();
        Integer[][] mazeArray = get2DArray(maze);
        targets.add(new Coordinate(x, y));

        int xCurrent = 0, yCurrent = 0;

        for(int i=0; i<targets.size(); i++) {
            xDest = targets.get(i).x;
            yDest = targets.get(i).y;

            if(mazePath(mazeArray, xCurrent, yCurrent)) {
                xCurrent = xDest;
                yCurrent = yDest;
                List<String> tempPATH = new ArrayList<>(path);
                paths.add(tempPATH);
                path.clear();
            } else {
                break;
            }
            /*yCurrent = yDest;
            xCurrent = xDest;*/
        }
        System.out.println(paths);
        int steps=0;
        for(List<String> list : paths) {
            steps = steps + list.size()-1;
        }
        return steps;
    }

    private static boolean mazePath(Integer[][] maze, int xCurrent, int yCurrent) {

        if(xCurrent == xDest && yCurrent == yDest) {
            path.add(xCurrent+""+yCurrent);
            return true;
        }

        if(!isValidCell(maze, xCurrent, yCurrent)) {
            return false;
        }


        if(yCurrent < yDest && mazePath(maze, xCurrent, yCurrent+1)) { //Move Horizontal
            path.add(xCurrent+""+yCurrent); //Add to list
            return true;
        } else if(yCurrent > yDest && mazePath(maze, xCurrent, yCurrent-1)) {
            path.add(xCurrent+""+yCurrent); //Add to list
            return true;
        } else if (xCurrent < xDest && mazePath(maze, xCurrent+1, yCurrent)) { //Move vertical
            path.add(xCurrent+""+yCurrent); //Add to list
            return true;
        } else if (xCurrent > xDest && mazePath(maze, xCurrent-1, yCurrent)) {
            path.add(xCurrent+""+yCurrent); //Add to list
            return true;
        } else {
            return false;
        }
    }


    private static Integer[][] get2DArray(List<List<Integer>> maze) {
        Integer array[][] = new Integer[maze.size()][];
        int i=0;
        for(List<Integer> list : maze) {
            array[i] = list.toArray(new Integer[list.size()]);
            i = i+1;
        }
        setTargets(array);
        return array;
    }

    private static void setTargets(Integer[][] maze) {
        for(int i=0; i<maze.length; i++) {
            for (int j=0; j<maze[i].length; j++) {
                if(maze[i][j] == 2) { //GOLD
                    targets.add(new Coordinate(i,j));
                }
            }
        }
        Collections.sort(targets);
    }

    private static boolean isValidCell(Integer[][] maze, int x, int y) {
        return x >= 0 && x < MAX_ROW && y >=0 && y < MAX_COL && maze[x][y]!=1;
    }



    //Input string is valid if all letters are in lowercase
    //and between a-z.
    private static boolean isValidString(String string) {

        if(!string.equals(string.toLowerCase())) {
            return false;
        }
        //
        Pattern p = Pattern.compile("[a-z]+");
        Matcher matcher = p.matcher(string);
        return matcher.matches();
    }

    private static boolean isStringOfAllVowels(String string) {
        return  string.length()>=5 &&
                string.contains("a") &&
                string.contains("e") &&
                string.contains("i") &&
                string.contains("o") &&
                string.contains("u");
    }

    private static int subStringsWithAllVowels(String string) {
        int numberOfString=0;
        Set<String> set = new HashSet<>();
        for(int i=0; string.substring(i).length()>=5; i++) {
            if(isStringOfAllVowels(string.substring(i))) {
                numberOfString++;
                set.add(string.substring(i));
                System.out.println(string.substring(i));
            }
            for(int j=string.substring(i).length(); string.substring(i,j).length()>=5; j--) {
                if(isStringOfAllVowels(string.substring(i,j))) {
                    numberOfString++;
                    System.out.println(string.substring(i,j));
                    set.add(string.substring(i,j));
                }
            }
        }
        return set.size();
    }

    private static class Coordinate implements Comparable<Coordinate> {
        int x;
        int y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private Integer sum() {
            return x + y;
        }

        @Override
        public int compareTo(Coordinate o) {
            return sum().compareTo(o.sum());
        }
    }

}