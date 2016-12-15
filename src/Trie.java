import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roozbeh on 12/6/2016.
 */
public class Trie{
    Node root;
    int asciiCode;
    class Node{
        char[] values;
        Node[] pointer;
        int vertexNumber;
        ArrayList<Integer> mapAdj;
        ArrayList<Integer> wieght;
        //int counterMapAj=0;
        //ArrayList<Integer> map;
        public Node(int count,int vertexNum,String type,ArrayList<Integer> map){
            this.mapAdj=map;
            this.values=new char[count];
            this.pointer=new Node[count];
            this.vertexNumber=vertexNum;
            if(type.equals("name")){
                int a;
                for(int i=0;i<count;i++){
                    a=i+97;
                    this.values[i]=(char)a;
                    this.pointer[i]=null;
                }
            }
        }
    }
    int countAlpha;
    String type;
    public Trie(String type){
        this.type=type;
        if(type.equals("name")) {
            this.root = new Node(26, -1, "name",null);
            this.asciiCode=97;
            this.countAlpha=26;
        }
    }
    public void addVertex(String name,int number,ArrayList<Integer> map,ArrayList<Integer> wiegth){
        int count;
        Node temp=this.root;
        for(char c:name.toCharArray()){
            count=(int)c-this.asciiCode;
            if(temp.pointer[count]==null){
                temp.pointer[count]=new Node(countAlpha,-1,this.type,map);
                temp=temp.pointer[count];
            }else{
                temp=temp.pointer[count];
            }

        }
        temp.vertexNumber=number;
        temp.wieght=wiegth;
    }
    public Node search(String name){
        int count;
        Node temp=this.root;
        Node tempPointer;
        for(char c:name.toCharArray()){
            count=(int)c-this.asciiCode;
            tempPointer=temp.pointer[count];
            if(tempPointer==null){
                return null;
            }else{
                temp=tempPointer;
            }
        }
        if((Integer)temp.vertexNumber!=-1){return temp;}
        else{
            return null;
        }
    }

}
