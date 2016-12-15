import javax.sound.midi.SysexMessage;
import java.util.*;

/**
 * Created by roozbeh on 12/6/2016.
 */

//TODO bfs agar connect nabashd chi mishe ?!?!
//2 trie
public class Traveling {
    class Info{
        int number;
        int color;
        public Info(int number,int color){
            this.number=number;this.color=color;
        }
    }
    class Ans{
        ArrayList<ArrayList<Integer>> ans;
        HashMap<String,Integer> ans_;
        public Ans(ArrayList<ArrayList<Integer>> ans,HashMap<String,Integer> ans_){
            this.ans=ans;
            this.ans_=ans_;
        }
    }
    Graph graph;
    public Traveling(Graph graph){
        this.graph=graph;
    }
    public int[] BFS(int source){
        int[] ans=new int[this.graph.vertexCount];
        boolean visited[] = new boolean[this.graph.vertexCount];
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[source]=true;
        queue.add(source);
        int counter=0;
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            source = queue.poll();
            System.out.print(graph.nameVertex[source] +" ");
            ans[counter]=source;
            counter++;
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Trie.Node node =this.graph.trie.search(graph.nameVertex[source]);
            ArrayList<Integer> set=node.mapAdj;
            Iterator<Integer> it=set.iterator();
            while (it.hasNext())
            {
                int n = it.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
//            for(int i=0;i<set.size()-1;i++)
//            {
//                int n = set.get(i+1);
//                if (!visited[n])
//                {
//                    visited[n] = true;
//                    queue.add(n);
//                }
//            }
        }
        return ans;
    }
    int counter__=0;
    public Integer[] DFSUtil(int source,boolean[] visited,Integer[] ans){
        visited[source]=true;
        //visited[source]=true;
        ans[this.counter__]=source;
        System.out.print(this.graph.nameVertex[source] + " ");
        counter__++;
//        System.out.println( "$$$$ "+ this.graph.nameVertex[source] + "   " + source);
        Trie.Node node=this.graph.trie.search(this.graph.nameVertex[source]);
       // System.out.println(node + " LLLLLLL");
        //System.out.println(" ###### "+node + " " + node.vertexNumber + " " + graph.nameVertex[node.vertexNumber]);
        if(node.mapAdj!=null) {
            Iterator<Integer> it = node.mapAdj.iterator();

            while (it.hasNext()) {
                int n = it.next();
                if (!visited[n])
                    //System.out.print(n);
                    DFSUtil(n, visited, ans);
            }
        }
//        for(int i=0;i<node.mapAdj.size();i++){
//            int n=i+1;
//            if(!visited[n]){
//                DFSUtil(n,visited,ans,counter);
//            }
//        }
        return ans;
    }
    public int allSee(boolean[] b){
        for(int i=0;i<b.length;i++){
            if(b[i]==false){
                return i;
            }
        }
        return -1;
    }
    public Integer[] dfs(int source){//0 ->> sefid ,1->>khakestari 2->>siyah
        boolean visited[] =new boolean[this.graph.vertexCount];
        Integer[] ans=new Integer[this.graph.vertexCount];
        //int counter__=0;
        DFSUtil(source, visited, ans);
        int i;
        while((i=allSee(visited))!=-1) {
            //
            DFSUtil(i,visited, ans);
        }
        return ans;
    }
//    public void bfs(int start){
//        boolean visited[]=new boolean[this.graph.vertexCount];
//        this.queue.add(start);
//        while(queue.size()!=0){
//            start=(int)queue.poll();
//            //system
//            int counter=0;
//            for(int adj :this.graph.graph[start]){
//                if(adj!=-1){
//                    if(!visited[counter]){
//                        visited[counter]=true;
//                        queue.add(counter);
//                    }
//                }
//                counter++;
//            }
//        }
//    }
    Graph getTranspose(){
        Graph g=new Graph(this.graph.vertexCount,this.graph.nameVertex,this.graph.map);
        for(String s:this.graph.nameVertex){
            g.initNode(s,new String[g.vertexCount],new ArrayList<Integer>(),new ArrayList<Integer>());
        }
        for(int v=0;v<this.graph.vertexCount;v++){
            Trie.Node node=this.graph.trie.search(this.graph.nameVertex[v]);
            ArrayList<Integer> set=node.mapAdj;
            Iterator<Integer> it=set.iterator();
            int counter=0;
            while (it.hasNext()){
                g.addEdage(it.next(),v,this.graph.trie.search(this.graph.nameVertex[v]).wieght.get(counter));
                counter++;
//                System.out.println("WWWWWWWWWWWWWWW");
//                for(String s__:g.nameVertex){
//                    System.out.print(s__ +" : ");
//                    for(int i:g.trie.search(s__).mapAdj){
//                        System.out.print(g.nameVertex[i] + "  ");
//                    }
//                    System.out.println();
//                }
            }
          //  System.out.println("########");
//            for (int i:g.trie.search(g.nameVertex[v]).mapAdj){
//                System.out.print(g.nameVertex[i]);
//            }
            //System.out.println(g.trie.search(g.nameVertex[v]).mapAdj);
            //String[] s=new String[set.size()];
//            for(int counter=0;counter<set.size();counter++){
//                g.addEdage(v,counter,this.graph.trie.search(this.graph.nameVertex[v]).wieght.get(counter));
//            }
        }
        //System.out.println("!!!!!!!!!!!!!!!");
//        for(String s__:g.nameVertex){
//            System.out.print(s__ +" : ");
//            for(int i:g.trie.search(s__).mapAdj){
//                System.out.print(g.nameVertex[i] + "  ");
//            }
//            System.out.println();
//        }
        return g;
    }
    public void fillOrder(int v,boolean[] visited,Stack stack){
        visited[v]=true;
        Trie.Node node=this.graph.trie.search(this.graph.nameVertex[v]);
        ArrayList<Integer> set=node.mapAdj;
        Iterator<Integer> it=set.iterator();
//        for(int i=0;i<node.mapAdj.size();i++){
//            int n=i+1;
//            if(!visited[n]){
//                fillOrder(n,visited,stack);
//            }
//        }
        while(it.hasNext()){
            int n=it.next();
            if(!visited[n]){
                fillOrder(n,visited,stack);
            }
        }
        stack.push(v);
    }
    public Traveling.Ans scc(){//trojan vs default TODO masire beyne scc ha chi hast ?!?!?! yani masir ham bede :((((((((( + indexing name haye tu ye scc
        System.out.println("<< strongly connected component is : >>");
        this.counter__=0;
        Stack stack=new Stack();
        HashMap<String,Integer> ans_=new HashMap<String,Integer>();
        ArrayList<ArrayList<Integer>> ans=new ArrayList<ArrayList<Integer>>();
        boolean[] visited =new boolean[this.graph.vertexCount];
        for(int i=0;i<this.graph.vertexCount;i++){
            visited[i]=false;
        }
        for(int i=0;i<this.graph.vertexCount;i++){
            if(visited[i]==false){
                fillOrder(i,visited,stack);
            }
        }
        Graph graphAns=getTranspose();
        for(int i=0;i<this.graph.vertexCount;i++){
            visited[i]=false;
        }
        while(stack.empty()==false){
            int v=(int)stack.pop();
            if(visited[v]==false){
                Integer[] pass=new Integer[graph.vertexCount];
                graphAns.traveling.DFSUtil(v,visited,pass);
                this.counter__=0;
                Integer[] ss=pass;
                ArrayList<Integer> arrayList=new ArrayList<>();
                for(Integer i:ss){
                    if(i!=null)
                        arrayList.add(i);
                    //System.out.println(i);
                }
               // ArrayList<Integer> arrayList =new ArrayList<Integer>(Arrays.asList(graphAns.traveling.DFSUtil(v,visited,new Integer[graph.vertexCount],0)));
                ans.add(arrayList);
                for(Integer i:arrayList){
//                    if(i!=null) {
                        //System.out.println(ans.size() + " @@@@@ " + ans_.size());
                        ans_.put(graphAns.nameVertex[i], ans.size() - 1);
//                    }
                }
                System.out.println();
            }
        }
        //System.out.println(((Integer[][])ans.toArray()) +" "+ans_  + " ####");
       // System.out.print(graphAns.trie.search("ali").mapAdj.get(0) + "  %%%%%%%%%%");
        return new Traveling.Ans(ans,ans_);
    }
    MinHeap heap;
    public HashMap spp(int source){
        HashMap<Integer,Integer> ans=new HashMap<Integer, Integer>();
        int[] wiegth=new int[graph.vertexCount];
        for(int i=0;i<wiegth.length;i++){
            if(i==source){
                wiegth[i]=0;
            }else{
                wiegth[i]=Integer.MAX_VALUE;
            }
        }

        this.heap = new MinHeap(graph.map, graph.nameVertex, wiegth, graph.vertexCount);
        //System.out.print("#");
        //int counter=0;
        System.out.println("<< shortest path problem >>");
       // System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
        while(!heap.isEmpty()) {
            MinHeap.Node node=this.heap.min();
            ans.put(node.vertexNumber,node.value);
            System.out.println(" Vertex Name is : " + node.vertexName + " which value is " +node.value);
            Trie.Node node_ = graph.trie.search(node.vertexName);
            //System.out.println(node + ""+ node_ );
            for(int i=0;i<node_.mapAdj.size();i++){
                if(heap.mapWi.get(this.graph.nameVertex[node_.mapAdj.get(i)])!=null) {
                    String s=graph.nameVertex[node_.mapAdj.get(i)];
                    if (node.value + node_.wieght.get(i) < heap.minHeap.get(heap.mapWi.get(graph.nameVertex[node_.mapAdj.get(i)])).value && node.value!=Integer.MAX_VALUE) {//vazne hale hazare node
                        MinHeap.Node nn=heap.minHeap.get(heap.mapWi.get(graph.nameVertex[node_.mapAdj.get(i)]));
                        nn.value= node.value + node_.wieght.get(i);
                        //this.heap.minHeap.remove(heap.mapWi.get(graph.nameVertex[node_.mapAdj.get(i)]));
                        //this.heap.minHeap.add(heap.mapWi.get(graph.nameVertex[node_.mapAdj.get(i)]),nn);
                        int q=heap.mapWi.get(graph.nameVertex[node_.mapAdj.get(i)]);
                        heap.exchange(q,heap.count-1);
                        heap.heapsort(q);
                        heap.heapup(heap.mapWi.get(s));
                    }
                }
            }
        }
        return ans;
    }
    public void makeMaxCapicity(HashMap<String,Integer> capacity){
        int sumMin=0;

    }
}
