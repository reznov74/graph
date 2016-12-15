import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by roozbeh on 12/13/2016.
 */

public class FileProcessing {
    String path;
    BufferedReader br = null;
    FileReader fr = null;
    public FileProcessing(String path){
        this.path=path;
        try {
            fr = new FileReader(this.path);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(this.path));
        } catch (FileNotFoundException e) {
            System.out.println("file not found ! ");
            System.exit(0);
        }
    }
    int count=0;
    int edage=0;
    public int getCount(){
        String s= null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] ss=s.split("\\s+");
        count=Integer.valueOf(ss[0]);
        this.edage=Integer.valueOf(ss[1]);
//        System.out.println("V : " + this.count + " E : "+this.edage);
        return count;
    }
    public String[] getName(){
        String[] ss=new String[count];
        for(int i=0;i<count;i++){
            try {
                ss[i]=br.readLine().trim();
//                System.out.println("vertex name : "+ ss[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ss;
    }
    public String[][] getQuery() throws IOException {
        int count=Integer.valueOf(br.readLine());
        String[][] ss=new String[count][2];
        String line;
        for(int counter=0;counter<count;counter++){
            line=br.readLine();
            System.out.println(line);
            ss[counter][0]=line.split("\\s+")[0].trim();
            ss[counter][1]=line.split("\\s+")[1].trim();
        }
        return ss;
    }
    public String[][] getCapacity() throws IOException {
        String[][] ans=new String[count][2];
        String line;
        int counter=0;
        while((line=br.readLine())!=null){
            ans[counter][0]=line.split("\\s+")[0].trim();
            ans[counter][1]=line.split("\\s+")[1].trim();
            counter++;
        }
        return ans;
    }
    public String[][] getEdage() throws IOException {
        String[][] sss=new String[this.edage][3];
        int counter=0;
        String s="";
        String[] ss=new String[3];
//        String line="";

//            System.out.println(this.edage + " $$");
            for(int i=0;i<this.edage;i++) {
                s =br.readLine();
                sss[i][0] = s.split("\\s+")[0].trim();
                sss[i][1] = s.split("\\s+")[1].trim();
                sss[i][2] = s.split("\\s+")[2].trim();
//                sss[i][0]=s.split("\\s+")[0].trim();
//                System.out.println("######");
//                System.out.println(ss[0] + " " + ss[1] + " " + ss[2] );
//                System.out.println(i);
//                sss[i] = ss;
                //counter++;
            }

        return sss;
    }
}
