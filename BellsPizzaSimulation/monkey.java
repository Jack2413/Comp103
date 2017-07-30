import ecs100.*;
/**
 * Write a description of class money here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class monkey
{
    // instance variables - replace the example below with your own
    private String chars = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Constructor for objects of class money
     */
    public monkey()
    {
        int count = 0;
        int count2= 0;

        int breakpoint=1000;
        String[] data = new String[breakpoint*10];
        while(true){

            
            String NewStr =random();
            data[count]=NewStr;
            //System.out.printf("%-7s",NewStr);
            UI.printf("%-7s",NewStr);
            count++;

            if(count%10==0){
                count2++;
                //System.out.printf("%-8s",count2);
                //System.out.println();
                UI.printf("%-8s",count2);
                UI.println();
                

            }
            if(NewStr.equals("banana")||count2==breakpoint){break;}

        }

        quickSort2(data, 0, data.length);
        printdata(data,breakpoint);
    }

    public String random(){
        char Nchar = chars.charAt((int)(Math.random() * 26));
        String string = Character.toString(Nchar);

        for (int i=((int)(Math.random() * 6));i>0;i--){
            Nchar = chars.charAt((int)(Math.random() * 26));
            string = string+Character.toString(Nchar);
        }

        return string;

    }

    public  void quickSort2(String[ ] data, int low, int high) {
        if (low+1 >= high) // no items to sort.
            return;
        else {     // split into two parts, mid = index of pivot
            int mid = partition2(data, low, high);
            quickSort2(data, low, mid);
            quickSort2(data, mid+1, high);
        }
    }

    public int partition2(String[] data, int low, int high){
        swap(data, low, (low+high)/2);    // choose pivot and put at position low
        String pivot = data[low];
        int mid = low;
        for(int i = low+1; i < high; i++){  // for each item after the pivot
            if ( data[i].compareTo(pivot) <0 ){
                mid++;                      // move mid point up
                swap(data, i, mid);
            }
        }
        swap(data, low, mid);   // move pivot to the mid point
        return mid;
    }

    private  void swap(String[ ] data, int index1, int index2){
        if (index1==index2) return;
        String temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }
    
    private void printdata(String [] data ,int bp){
        int count3=0;
        UI.println();
        for(int i=0; i<10000; i++){
            
            UI.printf("%-7s",data[i]);
            if((i+1)%10==0){
                
                count3++;
                UI.printf("%-8s",count3);
                UI.println();
                
                
            }            
        }   
    }
}
