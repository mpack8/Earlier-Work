import java.util.Arrays;
import java.util.LinkedList;

class Prime{
LinkedList<Integer> IntList = new LinkedList<>();
public void addPrime (int max){
    boolean[] list = new boolean[max+1];
    Arrays.fill(list, true);
    list[0]=false;
    list[1]=false;
    for (int i = 2; i*i <= max;i++){
        if (list[i]){
            for (int j = i*i; j<= max; j++){
                list[j]=false;
            }
        }
    }
    for (int i = 2; i <= max; i++){
        if (list[i]){
            IntList.add(i);
        }
    }
}

public boolean isPrime(int num){
    for (int prime : IntList){
if (prime == num){
    return true;
}
    }
    return false;
}

public int getPrime(int position) throws Exception{
    if(position<0||position>=IntList.size()){
        throw new Exception("Invalid position");
}
return IntList.get(position);
}

}


public class Main {
    public static int constant = 1000;
    public static void findFactor(int target, Prime object){

    }
    public static void main(String[] args) {

    }
}