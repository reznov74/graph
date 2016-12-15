import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roozbeh on 12/6/2016.
 */
public class Graph {
//    int[][] graph;
    //String[] vertexName;
   // Trie trie1;//name type
    Trie trie;//digit type
    int vertexCounter;
    int vertexCount;
    String[] nameVertex;//indexing name
    Traveling traveling;
    HashMap<String,Integer> map;
    public Graph(int vertexCount, String[] name, HashMap<String,Integer> map){
        //this.vertexName=name;
        this.nameVertex=name;
        //System.out.println(this.vertexName + "S");
        this.map=map;
        this.vertexCounter=0;
        this.traveling=new Traveling(this);
        this.trie=new Trie("name");
//        this.graph=new int[vertexCount][vertexCount];
        this.vertexCount=vertexCount;
//        for(int i=0;i<vertexCount;i++){
//            for(int j=0;j<vertexCount;j++){
//                this.graph[i][j]=-1;
//            }
//        }
    }
    public void addEdage(int start,int finish,int wieght){
        Trie.Node node=this.trie.search(this.nameVertex[start]);
        if(node!=null){
            node.wieght.add(wieght);
            node.mapAdj.add(finish);
//            node.counterMapAj++;
        }
        else{
            ArrayList<Integer> i=new ArrayList<Integer>();
//            int[] map=new
            //HashMap<Integer,String> map=new HashMap<Integer, String>();
            i.add(finish);
            ArrayList<Integer> w=new ArrayList<>();
            w.add(wieght);
            this.initNode(this.nameVertex[start],new String[] {this.nameVertex[finish]},i,w);
        }
    }
    Traveling.Ans ansPro;
    public Graph makeSCCGraph(){
        Traveling.Ans ans=this.traveling.scc();
        this.ansPro=ans;
        String[] s=new String[ans.ans.size()];
//        for(String s_:s){
//            s_
//        }
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        String name="";
        for(int i=0;i<s.length;i++){
            name+='a';
            s[i]=name;
            map.put(s[i],i);
        }
        Graph graph_=new Graph(ans.ans.size(),s,map);
        for(String s_:s){
            graph_.initNode(s_,new String[this.vertexCount],new ArrayList<Integer>(),new ArrayList<Integer>());
        }
        for(int i=0;i<ans.ans.size();i++){
            for(int j=0;j<ans.ans.get(i).size();j++){
                Trie.Node node=this.trie.search(this.nameVertex[ans.ans.get(i).get(j)]);
                int temp;
                for(int counter=0;counter<node.mapAdj.size();counter++){
                    temp=ans.ans_.get(this.nameVertex[counter]);
                    if(temp!=i){
                        graph_.addEdage(i,temp,node.wieght.get(counter));
                    }
                }
            }
        }
        //disjoint set i think better

        return graph_;
    }
    public void problemTwo(String[][] capacity){
        Graph g=this.makeSCCGraph();
        HashMap<String,Integer> mapCapacity=new HashMap<String, Integer>();//name shahr va capacity
        for(int counter=0;counter<capacity.length;counter++){
            mapCapacity.put(g.nameVertex[this.ansPro.ans_.get(capacity[counter][0])],Integer.valueOf(capacity[counter][1]));
        }

        //TODO
    }
    public void shortestPath(String start,String finish,int sts){
        int start_=this.map.get(start);
        int finish_=this.map.get(finish);
        Traveling.Ans ans=this.traveling.scc();
        if(ans.ans_.get(start).equals(ans.ans_.get(finish))){
            HashMap<Integer,Integer> map__=this.traveling.spp(start_);//1->vertex number 2->shoretst path
            System.out.println(map__.get(finish_));
        }else{
            if(sts==1) {
                Graph gTemp = this.makeSCCGraph();
                HashMap<Integer, Integer> map__ = gTemp.traveling.spp(ans.ans_.get(start));
                System.out.println (map__.get(ans.ans_.get(finish)));
            }else{
                System.out.println(this.traveling.spp(start_).get(finish));
            }
        }

    }
    public void initNode(String name, String[] edage, ArrayList<Integer> map,ArrayList<Integer> wieght){
//        for(int i=0;i<edage.length;i++){
//            this.graph[this.map.get(name)][this.map.get(edage[i])]=wieght[i];
//        }
        this.trie.addVertex(name,this.map.get(name),map,wieght);
//        this.vertexName=names;
//        int counter=0;
//
//        for(String s:names) {
//            this.nameVertex[counter]=s;
//           // this.trie1.addVertex(s, counter);
//            this.trie.addVertex(String.valueOf(counter),s);
//            counter++;
//        }
//        for(String[] strs:edage){
//            this.graph[(Integer) this.trie1.search(strs[0])][(Integer) this.trie1.search(strs[1])]=Integer.parseInt(strs[2]);
//        }

    }

}
