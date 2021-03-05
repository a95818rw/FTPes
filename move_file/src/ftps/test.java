package ftps;

import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

public class test {

    public static void main(String[] args) {
        
        String str1 = "ABC123";
        String str2 = "3030";
        String str3 = "000000009";

        System.out.println("Is "+str1+" an Integer? -> "+isStringInteger(str1));
        System.out.println("Is "+str2+" an Integer? -> "+isStringInteger(str2));
        System.out.println("Is "+str3+" an Integer? -> "+isStringInteger(str3));
    }

    public static boolean isStringInteger(String stringToCheck) {
        Scanner sc = new Scanner(stringToCheck.trim());
        if(!sc.hasNextInt()) return false;
        sc.nextInt();
        return !sc.hasNext();
    }

}