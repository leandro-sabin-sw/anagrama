package com.smallworldfs;

import java.util.Scanner;

public class Challenge {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.print("Enter string: ");
    String entered = in.next();

    System.out.println("Anagram: " + new AnagramGenerator(entered).generate());
  }

}
