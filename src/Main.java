
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by roozbeh on 12/6/2016.
 */
public class Main {
    public static void main( String[] args ) throws IOException {
        System.out.println("<< Enter File Absolute Path >>");
        Scanner scanner=new Scanner(System.in);
        String path="C:\\Users\\roozbeh\\Desktop\\input.txt";
        FileProcessing FP=new FileProcessing(path);
        int count=FP.getCount();
        String[][] nameAdj=new String[count][count];
        int[] counter__=new int[count];
        ArrayList<ArrayList<Integer>> wiegth=new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer>[] mapAdj=new ArrayList[count];
        HashMap<String,Integer> name=new HashMap<String,Integer>();
        String[] s=FP.getName();
        String[][] ss=FP.getEdage();
        int counter=0;
        Graph graph=new Graph(count,s,name);
        for(int i=0;i<count;i++){
            mapAdj[i]=new ArrayList<Integer>();
            wiegth.add(new ArrayList<>());
        }
        for(String name_:s){
            name.put(name_,counter);
            counter++;
        }
        for(String[] strs:ss){
            nameAdj[name.get(strs[0])][counter__[name.get(strs[0])]]=strs[1];//////
            wiegth.get(name.get(strs[0])).add(Integer.parseInt(strs[2]));
            counter__[name.get(strs[0])]++;
            mapAdj[name.get(strs[0])].add(name.get(strs[1]));
        }
        for(String name_:s){
            graph.initNode(name_,nameAdj[name.get(name_)],mapAdj[name.get(name_)],wiegth.get(name.get(name_)));
        }


//        for(String s__:graph.nameVertex){
//            System.out.print(s__ +" : ");
//            for(int i:graph.trie.search(s__).mapAdj){
//                System.out.print(graph.nameVertex[i] + "  ");
//            }
//            System.out.println();
//        }
        /////spp ok ->>>>>
       // System.out.println(graph.trie.search("mamad").vertexNumber);
//        Graph g=graph.makeSCCGraph();
//        for(String s__:g.nameVertex){
//            System.out.print(s__ +" : ");
//            for(int i:g.trie.search(s__).mapAdj){
//                System.out.print(g.nameVertex[i] + "  ");
//            }
//            System.out.println();
//        }
       // graph.traveling.spp(0);
//        String[][] ss_=FP.getQuery();
//        int co=0;
//        System.out.println("^^^^^^^^^^ "+ss_.length);
//        for(String[] ss__:ss_){
//            System.out.print(" ^ "+co+" ^ ");
//            graph.shortestPath(ss__[0],ss__[1],0);
//            co++;
//        }
        String[][] ans=FP.getCapacity();

        //Traveling.Ans ans=graph.traveling.scc();
        //System.out.println("OKKKKKKKKKKKKKK");
//        ans.ans.get(2).get(2);
//        for(int i=0;i<ans.ans.size();i++){
//            for(int j=0;j<ans.ans.get(i).size();j++){
//                System.out.print(graph.nameVertex[ans.ans.get(i).get(j)] + " ");
//            }
//            System.out.println();
//        }
  //      System.out.println("BFS is :");
//        graph.traveling.spp(0);//TODO gheyre behine va bedone filtering
     //   graph.shortestPath("ali","roozbeh");//TODO behine va okey
    }
}
