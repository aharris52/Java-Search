package edu.greenriver.dev;
import java.util.*;

public class CodingInterviewPractice {

    public static void main(String[] args) {

        int[] num = {22, 18, 3, 42, 7};
        int max = num[0];   //maximum value in the array
        int min = num[0];   //smallest value
        int sum = 0;        //cum. sum

        for(int i = 0; i < num.length; i++){
            //check min
            if(num[i] < min) min = num[i];

            //check max
            if(num[i] > max) max = num[i];

            sum += num[i];
        }

        System.out.println("Smallest value:" + min);
        System.out.println("Largest value:" + max);
        System.out.println("Average:" + (sum / num.length));
    }
}