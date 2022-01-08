import java.util.Scanner;


class RoundRobin{
    String name;
    int TimeOfArrive;
    int  RunTime;

    public RoundRobin(){ }
    public RoundRobin(String _name, int _TimeOfArrive, int _RunTime) {
        this.name = _name;
        this.TimeOfArrive = _TimeOfArrive;
        this.RunTime = _RunTime;
    }
    void ALgo(){

        int proNum;       // Number of processes
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of processes:");
        proNum = in.nextInt();
        PCB[] pcb = new PCB[proNum];
        Queue Wait = new Queue();
        Queue Ready = new Queue();
        int CPUTIME = 2;       // cpu time 2
        String PName;
        int  AT , NT;
        System.out.println("Enter processName , Time of arrive and need time :");
        for(int i = 0; i < proNum; i++){
            PName = in.next();
            AT = in.nextInt();
            NT = in.nextInt();
            pcb[i] = new PCB(PName,AT,NT);
        }
        PCB t = new PCB();
        for(int i = 0; i < proNum - 1; i++){
            for(int j = 0;j < proNum - 1 - i; j++){
                if(pcb[j].TimeOfArrive > pcb[j+1].TimeOfArrive)
                {
                    t = pcb[j];
                    pcb[j] = pcb[j+1];
                    pcb[j+1] = t;
                }
            }
        }
        System.out.println("pName TimeOfArrive  NeedTime");
        for(int i = 0;i < proNum; i++){
            System.out.print("  "+pcb[i].name+"     ");
            System.out.print(pcb[i].TimeOfArrive+"      ");
            System.out.println(pcb[i].RunTime);
        }
        int nowtime = pcb[0].TimeOfArrive;
        for(int i = 0; i < proNum; i++){
            if(pcb[i].TimeOfArrive == nowtime) {
                Ready.comeIn(i);
                pcb[i].State = 'W';
            }
            else {
                Wait.comeIn(i);
                pcb[i].State = 'W';
            }
        }
        while( Wait.Exit() == 1 || Ready.Exit() == 1 ) {
            while(Ready.Exit() == 1) {
                pcb[Ready.PNum[Ready.Full]].RunTime = pcb[Ready.PNum[Ready.Full]].RunTime - CPUTIME;
                pcb[Ready.PNum[Ready.Full]].CPUT++;
                pcb[Ready.PNum[Ready.Full]].State = 'R';
                if(pcb[Ready.PNum[Ready.Full]].RunTime > 0) {
                    Ready.comeIn(Ready.PNum[Ready.Full]);
                }
                else {
                    pcb[Ready.PNum[Ready.Full]].RunTime = 0;
                    pcb[Ready.PNum[Ready.Full]].State = 'F';
                    Ready.ComeOut();
                }
                System.out.println("Now Time："+nowtime);
                System.out.println("pName  CUPTime  NeedTime   TimeOfArrive   State");
                for(int i = 0; i < proNum; i++){
                    System.out.print("  "+pcb[i].name+"     ");
                    System.out.print(pcb[i].CPUT+"         ");
                    System.out.print(pcb[i].RunTime+"        ");
                    System.out.print(pcb[i].TimeOfArrive+"      ");
                    System.out.println(pcb[i].State);
                }
                if(pcb[Ready.PNum[Ready.Full]].State == 'R'){
                    pcb[Ready.PNum[Ready.Full]].State = 'W';
                    Ready.ComeOut();
                }
                nowtime +=  CPUTIME;
                for(int i = Wait.Full;i < Wait._Null; i++) {
                    if(nowtime >= pcb[Wait.PNum[i]].TimeOfArrive) {
                        Ready.comeIn(Wait.PNum[i]);
                        Wait.ComeOut();
                    }
                }
            }
            System.out.println("Now Time ："+nowtime);
            nowtime = nowtime + CPUTIME;
            for(int i = Wait.Full ;i < Wait._Null; i++) {
                if(nowtime >= pcb[Wait.PNum[i]].TimeOfArrive) {
                    Ready.comeIn(Wait.PNum[i]);
                    Wait.ComeOut();
                }
            }

        }
        in.close();
    }
    public static void main(String[] args) {
        RoundRobin roundRobin = new RoundRobin();
        roundRobin.ALgo(); // algorithm for Round Robin
    }
}

 class PCB {

    String name;    //process name
    int TimeOfArrive;   // time of arrive
    int RunTime;      // need time
    int CPUT = 0;    // cpu time
    char State;     // process state

    PCB(){}
    PCB(String _name,int _TimeOfArrive ,int _RunTime){
        name = _name;
        TimeOfArrive = _TimeOfArrive;
        RunTime = _RunTime;
    }
}
// queue
class Queue{

    int[] PNum = new int[100];
    int Full = 0;
    int _Null = 0;

    void comeIn(int num){
        if(_Null >= 99)
            System.out.println("Full");
        PNum[_Null] = num;
        _Null++;
    }
    void ComeOut(){
        if(Full > _Null)
            System.out.println("Null");
        Full++;
    }
    // isExit
    int Exit() {
        if(_Null > Full)
            return 1;
        else
            return 0;
    }
}
