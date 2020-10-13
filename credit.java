package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> cardList = new ArrayList<Integer>();

        Scanner input = new Scanner(System.in);
        System.out.println("Card Number: ");
        String cardnum = input.nextLine();

        String cardtype = "";

        //convert ones and twos place to integer
        int ones = Integer.parseInt(String.valueOf((cardnum.charAt(0))));
        int twos = Integer.parseInt(String.valueOf(cardnum.charAt(1)));

        int length = cardnum.length();

        if(length == 15 && ones == 3 && twos == 4 || twos == 7)
        {
            cardtype = "AMEX";
        } else if(length == 16 && ones == 5 && twos == 1 || twos == 2 || twos == 3 || twos == 4 || twos ==5) 
        {
            cardtype = "MasterCard";
        } else if(length == 16  || length == 13 && ones == 4) 
        {
            cardtype = "Visa";
        }
        
        //convert each character from cardnum to an int, and it add to the array list
        for (int i = 0; i < length; i++) {
            cardList.add(Integer.parseInt(String.valueOf(cardnum.charAt(i))));
        }

        int stepOneTotal = 0;
        int currNum;
        //loop through array list backwards incrementing by -2, starting from second to last index
        for (int i = cardList.size()-2; i >= 0 ; i += -2) {
            cardList.set(i, cardList.get(i) * 2);
            
            //track current number value after multiplying it by 2
            currNum = cardList.get(i);
            
            //if currNum is 2 digit number, separate digits and add them to step 1 total
            if(currNum >= 10 && currNum <= 99) {
                while(currNum>0) {
                    //get last digit of number with modulo
                    int digit = currNum % 10;
                    stepOneTotal += digit;
                    //remove last digit. This will only keep the first digit since this is an int. Then repeat.
                    currNum = currNum / 10;
                }
            }else {
                stepOneTotal += cardList.get(i);
            }
        }

        int stepTwoTotal = 0;
        for (int i = cardList.size()-1; i >= 0 ; i += -2) {
            stepTwoTotal += cardList.get(i);
        }

        int total = stepOneTotal + stepTwoTotal;
        if(total % 10 == 0) {
            System.out.println("Valid!");
            System.out.println(cardtype);
        } else {
            System.out.println("Card Not Valid");
        }
    }
}
