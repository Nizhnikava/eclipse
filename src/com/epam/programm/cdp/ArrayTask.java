package com.epam.programm.cdp;

import java.util.Arrays;
import java.util.Properties;
/**
 * Created by HELEN on 18.03.2015.
 */
public class ArrayTask {
    private Properties properties;

    public ArrayTask(Properties props) {
        properties = props;
    }

    public void start() {
        System.out.println(properties.getProperty("startArray"));
        Integer[][] array = initArray();
        System.out.println(properties.getProperty("enteredArray"));
        print2DArray(array);
        Coordinate maxValueCoordinate = findMaxValue(array);
        System.out.println("Coordinates of max value: x=" + maxValueCoordinate.getX() + ", y=" + maxValueCoordinate.getY());
        Integer[][] result = remove(array, maxValueCoordinate);
        System.out.println("Result");
        print2DArray(result);
    }

    private Coordinate findMaxValue(Integer[][] array) {
        Integer maxValue = array[0][0];
        int xMaxValue = 0;
        int yMaxValue = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                Integer currentValue = array[i][j];
                if (maxValue < currentValue) {
                    maxValue = currentValue;
                    xMaxValue = j;
                    yMaxValue = i;
                }
            }
        }
        System.out.println("Max value in array is: " + maxValue);
        return new Coordinate(xMaxValue, yMaxValue);
    }

    private Integer[][] initArray() {
        System.out.println(properties.getProperty("rowCount"));
        Console console = Console.getInstance();
        Integer rowCount = console.readInteger();
        System.out.println(properties.getProperty("columnCount"));
        Integer columnCount = console.readInteger();
        System.out.println("Size of matrix is: " + rowCount + "*" + columnCount);
        Integer[][] array = new Integer[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            System.out.println("Enter elements of row " + (i + 1));
            array[i] = console.readIntArray(columnCount);
        }
        return array;
    }

    private Integer[][] remove(Integer[][] source, Coordinate maxValueCoordinate) {
        Integer result[][] = new Integer[source.length - 1][source[0].length - 1];
        int rowIndex = maxValueCoordinate.getY();
        int columnIndex = maxValueCoordinate.getX();
        int p = 0;
        for (int i = 0; i < source.length; ++i) {
            if (i == rowIndex) {
                continue;
            }
            int q = 0;
            for (int j = 0; j < source[0].length; ++j) {
                if (j == columnIndex) {
                    continue;
                }
                result[p][q] = source[i][j];
                ++q;
            }
            ++p;
        }
        return result;
    }

    private void print2DArray(Integer[][] src) {
        for (int i = 0; i < src.length; i++) {
            System.out.println(Arrays.toString(src[i]));
        }
    }

    private class Coordinate{
        private int x;
        private int y;

        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

    }

}