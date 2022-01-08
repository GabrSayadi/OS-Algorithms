package com.osalgo;

import java.util.Scanner;

class Banker{

    int[] ID;
    int N;
    int M;
    int[] All;
    int Max[][];
    int[][] Allocation;
    int[][] Need;
    int[][] Available;
    boolean[] Finish;

    int a = 0;

    public Banker() {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of process:");
        M = input.nextInt();
        System.out.println("Enter number of resources:");
        N = input.nextInt();

        ID = new int[M];
        All = new int[N];
        Max = new int[M][N];
        Allocation = new int[M][N];
        Need = new int[M][N];
        Available = new int[M+1][N];
        Finish = new boolean[M];

        System.out.println("Enter the available resources:");
        for(int i=0; i<N; i++)
            All[i] = input.nextInt();
        System.out.println("Enter the Max :");
        for(int i=0; i<M; i++)
        {
            ID[i] = i;
            for(int j=0; j<N; j++)
                Max[i][j] = input.nextInt();
        }
        System.out.println("Enter the Allocation :");
        for(int i=0; i<M; i++)
            for(int j=0; j<N; j++)
                Allocation[i][j] = input.nextInt();

        Need_Resources();
        Available_Resources();
        Print_Banker();



    }

    public Banker(int[] iD, int m, int n, int[] all, int[][] max, int[][] allocation, int[][] need, int[][] available,
                  boolean[] finish, int a) {
        super();
        ID = iD;
        M = m;
        N = n;
        All = all;
        Max = max;
        Allocation = allocation;
        Need = need;
        Available = available;
        Finish = finish;
        this.a = a;
    }

    private void Need_Resources() {

        for(int i=0; i<M; i++)
            for(int j=0; j<N; j++)
                Need[i][j] = Max[i][j] - Allocation[i][j];
    }

    private void Available_Resources() {
        for(int n=0; n<N; n++)
        {
            Available[a][n] = All[n];
            for(int m=0; m<M; m++)
            {
                Available[a][n] -= Allocation[m][n];
            }
        }
    }

    private void Print_Banker() {
        System.out.print("Resource\tNumber of resource\n");
        for(int i=0; i<N; i++)
            System.out.printf("S%d\t%d\n", i, All[i]);
        System.out.print("\nName\t Max\tAllocation\tNeed\tAvailable\tFinish\n");
        for(int i=0; i<M; i++){
            System.out.print("P"+ID[i]);
            System.out.print("\t\t");
            for(int j=0; j<Max[ID[i]].length; j++)
            {
                System.out.printf("%d ", Max[ID[i]][j]);
            }
            System.out.print("\t");
            for(int j=0; j<Allocation[ID[i]].length; j++)
            {
                System.out.printf("%d ", Allocation[ID[i]][j]);
            }
            System.out.print("\t\t");
            for(int j=0; j<Need[ID[i]].length; j++)
            {
                System.out.printf("%d ", Need[ID[i]][j]);
            }

            System.out.print("\t");
            if(i == 0)
            {
                for(int j=0; j<N; j++)
                {
                    System.out.printf("%d ", Available[i][j]);
                }
            }

            System.out.print("\t");

            System.out.print("\t\t");

            System.out.print(Finish[i]);
            System.out.println();

        }


    }

    public void Security_examine(){
        boolean flag1,
                flag2;
        flag1 = true;
        while(flag1){
            flag1 = false;
            for(int i=0; i<M; i++){
                flag2 = true;
                for(int j=0; flag2 && j<N; j++){
                    if(Need[i][j] > Available[a][j] || Finish[i])
                    {
                        flag2 = false;
                    }
                }
                if(flag2 && !Finish[i]){
                    flag1 = true;
                    Finish[i] = true;
                    ID[a] = i;
                    a++;
                    for(int j=0; flag2 && j<N; j++){

                        Available[a][j] = Available[a-1][j] + Allocation[i][j];

                    }


                }
            }
        }
        if(a == M)
        {
            Print_Banker_Se();
            System.out.println("\nSecurity sequence:");
            for(int i=0; i<M; i++){
                System.out.print("->P"+ID[i]);
            }
            System.out.println("\nSYSTEM SECURITY!!!");
        }




    }

    public void Reallocation(){
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        int[] Request = new int[N];
        boolean flag = true;

        System.out.print("\nEnter process name(Integer):");
        int n = input.nextInt();
        System.out.print("Enter the customer's request:");
        for(int j=0; j<N; j++)
            Request[j] = input.nextInt();

        for(int i=0; i<N; i++)
        {
            if(Request[i] > Need[n][i] || Request[i] > Available[0][i])
                flag = false;
        }
        if(flag)
        {
            for(int i=0; i<N; i++)
            {
                Allocation[n][i] += Request[i];
            }

            Init();
            Print_Banker();

        }
        else
        {
            System.out.println("RESOURCE INSECURITY!!!\n");
            System.out.println("CUSTOMER "+ "P" + n + "CAN NOT  OBTAIN RESOURCES IMMEDIATELY");
        }

    }

    private void Init() {
        a = 0;
        Need_Resources();
        Available_Resources();
        for(int i=0; i<M; i++)
        {
            ID[i] = i;
            Finish[i] = false;
        }
    }

    private void Print_Banker_Se() {
        System.out.print("\nName\t Work\tNeed\tAllocation\tWork+Allocation\tFinish\n");
        for(int i=0; i<M; i++){
            System.out.print("P"+ID[i]);

            System.out.print("\t\t");
            for(int j=0; j<Available[i].length; j++)
            {
                System.out.printf("%d ", Available[i][j]);
            }

            System.out.print("\t");
            for(int j=0; j<Need[ID[i]].length; j++)
            {
                System.out.printf("%d ", Need[ID[i]][j]);
            }

            System.out.print("\t");
            for(int j=0; j<Allocation[ID[i]].length; j++)
            {
                System.out.printf("%d ", Allocation[ID[i]][j]);
            }

            System.out.print("\t\t");
            for(int j=0; j<Available[i+1].length; j++)
            {
                System.out.printf("%d ", Available[i+1][j]);
            }

            System.out.print("\t");

            System.out.print("\t\t");

            System.out.print(Finish[i]);
            System.out.println();
        }

    }


}