import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roozbeh on 12/11/2016.
 */
public class MinHeap {
    ArrayList<Node> minHeap;
    int count;
    class Node{
        String vertexName;
        int vertexNumber;
        int value;
        public Node(int vertexNumber,String vertexName,int value){
            this.vertexName=vertexName;
            this.vertexNumber=vertexNumber;
            this.value=value;
        }
    }
    String[] name;
    public MinHeap(HashMap<String,Integer> map,String[] name,int[] wieght,int count){// int [] ->>wieght )) indexing
        this.minHeap=new ArrayList<Node>() ;
        Node node;
        this.name=name;
        this.mapWi=new HashMap<String,Integer>();

        this.count=count;
        for(int counter=0;counter< wieght.length;counter++){
            System.out.println("########");
            node=new Node(counter,name[counter],wieght[counter]);
            minHeap.add(node);
            this.mapWi.put(name[counter],counter);
        }
        build();
    }
    HashMap<String,Integer> mapWi;///name to index in heap ///sososososos TODO

//    public void heapsort(){
//        Node temp;
//        int tempCO;
//        for(int counter=count/2;counter>0;counter++){
//            tempCO=counter;
//            while(counter*2<count || counter*2+1<count) {
//                System.out.println("@@@@@@@@");
//                if (counter * 2 < count && counter * 2 + 1 < count && this.minHeap[counter].value < this.minHeap[counter * 2].value && this.minHeap[counter * 2].value <= this.minHeap[counter * 2 + 1].value) {
//                    //counter*2 is min
//                    temp = this.minHeap[counter];
//                    this.minHeap[counter] = this.minHeap[counter * 2];
//                    this.minHeap[counter * 2] = temp;
//                    this.mapWi.replace(this.minHeap[counter * 2].vertexName, counter);
//                    counter=counter*2;
//                }
//                if (counter * 2 < count && counter * 2 + 1 < count && this.minHeap[counter].value < this.minHeap[counter * 2 + 1].value && this.minHeap[counter * 2].value >= this.minHeap[counter * 2 + 1].value) {
//                    //counter*1 is min
//
//                    temp = this.minHeap[counter];
//                    this.minHeap[counter] = this.minHeap[counter * 2 + 1];
//                    this.minHeap[counter * 2 + 1] = temp;
//                    this.mapWi.replace(this.minHeap[counter * 2 + 1].vertexName, counter);
//                    counter=counter*2+1;
//                }
//                if (counter * 2 < count && counter * 2 + 1 < count && this.minHeap[counter].value < this.minHeap[counter * 2 + 1].value && this.minHeap[counter * 2].value <= this.minHeap[counter * 2 + 1].value) {
//                    //counter*2 is min
//
//                    temp = this.minHeap[counter];
//                    this.minHeap[counter] = this.minHeap[counter * 2];
//                    this.minHeap[counter * 2] = temp;
//                    this.mapWi.replace(this.minHeap[counter * 2].vertexName, counter);
//                    counter=counter*2;
//                }
//                if (counter * 2 >= count && counter * 2 + 1 < count && this.minHeap[counter].value > this.minHeap[counter * 2 + 1].value) {
//                    //min is counter*2+1
//
//                    temp = this.minHeap[counter];
//                    this.minHeap[counter] = this.minHeap[counter * 2 + 1];
//                    this.minHeap[counter * 2 + 1] = temp;
//                    this.mapWi.replace(this.minHeap[counter * 2 + 1].vertexName, counter);
//                    counter=counter*2+1;
//                }
//                if (counter * 2 < count && counter * 2 + 1 >= count && this.minHeap[counter].value > this.minHeap[counter * 2].value) {
//                    //min is counter*2
//
//                    temp = this.minHeap[counter];
//                    this.minHeap[counter] = this.minHeap[counter * 2];
//                    this.minHeap[counter * 2] = temp;
//                    this.mapWi.replace(this.minHeap[counter * 2].vertexName, counter);
//                    counter=counter*2;
//                }
//            }
//            counter=tempCO;
//
//        }
//    }
    public boolean isEmpty(){
        if(n<0){
            return true;
        }return false;
    }
    private int n;
    private int left;
    private int right;
    private  int largest;
    public Node min(){
        Node node=this.minHeap.get(0);
        exchange(0,n);
        this.minHeap.remove(n);
        this.mapWi.remove(node.vertexName);
        n--;
        count--;
        heapsort(0);
        return node;
    }
    public void build(){
        n=this.minHeap.size()-1;
        for(int i=n/2;i>=0;i--){
            heapsort(i);
        }
    }
    public void exchange(int i, int j){
        MinHeap.Node t=this.minHeap.get(i);
        MinHeap.Node e=this.minHeap.get(j);
        String name=this.minHeap.get(j).vertexName;

        this.mapWi.replace(t.vertexName,j);
        this.mapWi.replace(name,i);
        this.minHeap.remove(i);
        this.minHeap.add(i,e);
        this.minHeap.remove(j);
        this.minHeap.add(j,t);
    }
    public void heapsort(int i){
        left=2*i;
        right=2*i+1;
        if(left<= n && this.minHeap.get(left).value<this.minHeap.get(i).value){
            largest=left;
        }else {
            largest=i;
        }
        if(right<=n && this.minHeap.get(right).value < this.minHeap.get(largest).value){
            largest=right;
        }
        if(largest!=i){
            exchange(i,largest);
            heapsort(largest);
        }
    }
    public void heapup(int i){
        int parent=i/2;
        if(parent>0 && this.minHeap.get(parent).value>this.minHeap.get(i).value){
            exchange(i,parent);
            heapup(parent);
        }
        else if(parent==0 && this.minHeap.get(parent).value>this.minHeap.get(i).value){
            exchange(i,parent);
            return;
        }
    }
}
