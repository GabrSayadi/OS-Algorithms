import java.util.*;

public class BestFit {
    static Scanner input = new Scanner(System.in);


    List<Integer> address = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public void  bubbleSort(List<Integer> numbers)
    {
       int i, j, swap , len = numbers.size();
       for (i = 0; i < len - 1 ; i++) {
            for (j = 0; j < len - i - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)){
                    swap = numbers.get(j);
                    numbers.set(j,numbers.get(j + 1));
                    numbers.set(j + 1,swap);

                }
            }
        }
    }


    private void setResult()
    {
        address.add(0);
        size.add(32767);
        while (true)
        {
            if (address.size() == 1)
            {
                System.out.println("Index  *  address  *  end  *  size");
                System.out.println((1 + "\t" +address.get(0)+ "\t" +((address.get(0) + size.get(0)) - 1)+ "\t" + size.get(0)));
            } else
            {
                System.out.println("Index  *  address  *  end  *  size");
                for (int i = 0; i < address.size(); i++) {
                    System.out.println(((i + 1) + "\t" +address.get(i)+ "\t" +((address.get(i) + size.get(i)) - 1)+ "\t" + size.get(i)));
                }
            }

            System.out.print("please choose (1 or 2 ) :\n1)Best\n2)First\n");
            int choice = input.nextInt();

            if (choice == 1 )
            {
                System.out.print("please choose (1 or 2 ):\n1)Accept\n2)Assign\n");
                int action = input.nextInt();

                if (action == 1)
                {
                    System.out.println("Enter address :");
                    int tempAddress = input.nextInt();
                    System.out.println("Enter size :");
                    int tempSize = input.nextInt();
                    address.add(tempAddress);
                    size.add(tempSize);
                } else if (action == 2) {
                    List<Integer>  temp = new ArrayList<>();
                    List<Integer> addressIndex = new ArrayList<>();

                    System.out.println("enter APPLACATION :");
                    Integer app = input.nextInt();

                    for (int i = 0; i < address.size(); i++) {
                        if (app < size.get(i) || app == size.get(i))
                        {
                            temp.add(size.get(i));
                            addressIndex.add(i);
                        }

                    } if (temp ==  temp) {

                        if (temp.size() == 1 && addressIndex.size() == 1)
                        {

                            System.out.println("SUCCESS!!! address is = " + ((temp.get(addressIndex.get(0) + address.get(addressIndex.get(0))) - app)));
                            int sum = size.get(addressIndex.get(0));
                            sum  = sum - app;
                        }else if (1 < temp.size())
                        {
                            bubbleSort(temp);
                            for (int i = 0; i < address.size(); i++) {
                                int index  = 0;
                                int sum = size.get(index);
                                if (temp.get(0) ==  size.get(i))
                                {
                                     index = i;
                                    break;
                                }
                                System.out.println("SUCCESS!!! address is = " + size.get(index));
                                sum = sum -  app;
                            }

                        }
                        else System.out.println("\napp is too large!\n");
                    }
                    else
                    {
                        System.out.println("\nunknown command!\n");
                    }
                }
            } else if (choice == 2) {
                System.out.print("please choose (1 or 2 ) :\n 1)Accept\n2)Assign\n");
                int action = input.nextInt();
                if (action == 1)
                {
                    System.out.println("enter address :");
                    int tempAddress = input.nextInt();
                    System.out.println("enter size :");
                    int tempSize = input.nextInt();

                    address.add(tempAddress);
                    size.add(tempSize);
                } else if (action == 2)
                {
                    List<Integer> temp = new ArrayList<>();
                    System.out.println("enter APPLACATION :");
                    Integer app = input.nextInt();
                    int index = 0;

                    for (int i = 0; i < address.size(); i++) {
                        if (app < size.get(i) || app == size.get(i))
                        {
                            temp.add(size.get(i));
                            index = i;
                            break;
                        }
                    }
                    int sum = size.get(index);
                    System.out.println("SUCCESS!!! address is = " + (address.get(index) + size.get(index) - app));
                    sum = sum  - app;

                    if (temp == temp ){
                        System.out.println();
                    } else
                        System.out.println("\n app is too large ");
                }
        }else
                System.out.println("wrong input!");
        }
    }

    public static void main(String[] args) {
        BestFit bestFit = new BestFit();
        bestFit.setResult();
    }
}
