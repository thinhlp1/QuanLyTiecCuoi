package com.happywedding.helper;

/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.text.DecimalFormat;

public class EnglishNumberToWords {

  private static final String[] tensNames = {
    "",
    " mười",
    " hai mươi",
    " ba mươi",
    " bốn mươi",
    " năm mươi",
    " sáu mươi",
    " bảy mươi",
    " tám mươi",
    " chín mươi"
  };

  private static final String[] numNames = {
    "",
    " một",
    " hai",
    " ba",
    " bốn",
    " năm",
    " sáu",
    " bảy",
    " tám",
    " chín",
    " mười",
    " mười một",
    " mười hai",
    " mười ba",
    " mười bốn",
    " mười lăm",
    " mười sáu",
    " mười bảy",
    " mười tám",
    " mười chín"
  };

  private EnglishNumberToWords() {}

  private static String convertLessThanOneThousand(int number) {
    String soFar;

    if (number % 100 < 20){
      soFar = numNames[number % 100];
      number /= 100;
    }
    else {
      soFar = numNames[number % 10];
      number /= 10;

      soFar = tensNames[number % 10] + soFar;
      number /= 10;
    }
    if (number == 0) return soFar;
    return numNames[number] + " trăm" + soFar;
  }


  public static String convert(long number) {
    // 0 to 999 999 999 999
    if (number == 0) { return "không"; }

    String snumber = Long.toString(number);

    // pad with "0"
    String mask = "000000000000";
    DecimalFormat df = new DecimalFormat(mask);
    snumber = df.format(number);

    // XXXnnnnnnnnn
    int billions = Integer.parseInt(snumber.substring(0,3));
    // nnnXXXnnnnnn
    int millions  = Integer.parseInt(snumber.substring(3,6));
    // nnnnnnXXXnnn
    int hundredThousands = Integer.parseInt(snumber.substring(6,9));
    // nnnnnnnnnXXX
    int thousands = Integer.parseInt(snumber.substring(9,12));

    String tradBillions;
    switch (billions) {
    case 0:
      tradBillions = "";
      break;
    case 1 :
      tradBillions = convertLessThanOneThousand(billions)
      + " tỷ ";
      break;
    default :
      tradBillions = convertLessThanOneThousand(billions)
      + " tỷ ";
    }
    String result =  tradBillions;

    String tradMillions;
    switch (millions) {
    case 0:
      tradMillions = "";
      break;
    case 1 :
      tradMillions = convertLessThanOneThousand(millions)
         + " triệu ";
      break;
    default :
      tradMillions = convertLessThanOneThousand(millions)
         + " triệu ";
    }
    result =  result + tradMillions;

    String tradHundredThousands;
    switch (hundredThousands) {
    case 0:
      tradHundredThousands = "";
      break;
    case 1 :
      tradHundredThousands = "một ngàn ";
      break;
    default :
      tradHundredThousands = convertLessThanOneThousand(hundredThousands)
         + " ngàn ";
    }
    result =  result + tradHundredThousands;

    String tradThousand;
    tradThousand = convertLessThanOneThousand(thousands);
    result =  result + tradThousand;

    // remove extra spaces!
    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
  }

  /**
   * testing
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("*** " + EnglishNumberToWords.convert(0));
    System.out.println("*** " + EnglishNumberToWords.convert(1110005000));


    /*
     *** zero
     *** one
     *** sixteen
     *** one hundred
     *** one hundred eighteen
     *** two hundred
     *** two hundred nineteen
     *** eight hundred
     *** eight hundred one
     *** one thousand three hundred sixteen
     *** one million
     *** two millions
     *** three millions two hundred
     *** seven hundred thousand
     *** nine millions
     *** nine millions one thousand
     *** one hundred twenty three millions four hundred
     **      fifty six thousand seven hundred eighty nine
     *** two billion one hundred forty seven millions
     **      four hundred eighty three thousand six hundred forty seven
     *** three billion ten
     **/
  }
}