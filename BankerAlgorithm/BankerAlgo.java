package com.osalgo;

public class BankerAlgo {

    public static void main(String[] args) {

        Banker banker = new Banker();
        int n;
        boolean flag=true;
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("1、judge the system security:\n2、judge the request security：\n3、quit:");
        while(flag)
        {
            System.out.print("Enter operating the number(1 or 2 or 3):  ");
            n = input.nextInt();
            switch(n)
            {
                case 1:	banker.Security_examine();break;
                case 2:	banker.Reallocation();break;
                default:flag = false;System.out.print("\n END!!!");
            }
        }
        input.close();

    }

}
