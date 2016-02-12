import java.io.*;
import java.util.*;

public class PrimAlgorithm {

    static class graph
       {   
           Map<Integer,Map<Integer,Integer>> adj; 
           public int[] dist;
           public int[] par;
           public int V;
           public graph(int v)
           {
               V=v;
               adj=new HashMap<Integer,Map<Integer,Integer>>();
               par=new int[V];
               dist=new int[V];
               for(int i=0;i<V;i++)
                    dist[i]=Integer.MAX_VALUE;   
           }
           public void add_edge(int v,int w,int wt)
           {   
                if(adj.containsKey(v))
                {    for (Map.Entry<Integer,Map<Integer,Integer>> entry : adj.entrySet())
                     {
                        if(entry.getKey()==v)
                            {
                                Map<Integer,Integer> m=entry.getValue();
                                int val=-1;
                                for(Map.Entry<Integer,Integer> ent:m.entrySet())
                                    if(ent.getKey()==w)
                                      {
                                           val=ent.getValue();
                                           break;
                                        }             
                                        
                                if(val==-1||val>wt)
                                {
                                    m.put(w,wt);
                                    adj.put(v,m);
                                }
                                break;            
                            }
                       
                     }
                }
                else
                {
                    Map<Integer,Integer> m1=new HashMap<Integer,Integer>();
                    m1.put(w,wt);
                    adj.put(v,m1);
                }
           }
        public int prim(int st)
        {
            List<Integer> mstSet=new ArrayList<Integer>();
            dist[st]=0;
            while(mstSet.size()<(V))
            {   int val=-1,mind=Integer.MAX_VALUE;
                for(int i=0;i<V;i++)
                    if(!mstSet.contains(i) && dist[i]<mind)
                     {
                             val=i;
                             mind=dist[i];    
                     }   
                mstSet.add(val);
                for (Map.Entry<Integer,Map<Integer,Integer>> entry : adj.entrySet())
                {
                    if(entry.getKey()==val)
                    {
                        Map<Integer,Integer> m=entry.getValue();
                        for(Map.Entry<Integer,Integer> ent:m.entrySet())
                        {
                             if((ent.getValue()<dist[ent.getKey()])&&(!mstSet.contains(ent.getKey())))
                             {
                                 dist[ent.getKey()]=ent.getValue();
                             }
                        }
                    }
                }           
            }
            int s=0;
            for(int i=0;i<V;i++)
                s+=dist[i];
            return s;
        }
    }
    public static void main(String[] args) {
       Scanner scan=new Scanner(System.in);
       
           int N=scan.nextInt();
           int M=scan.nextInt();
           graph g=new graph(N);
           for(int j=0;j<M;j++)
           {   
               int x=scan.nextInt();
               int y=scan.nextInt();
               int w=scan.nextInt();
               g.add_edge(x-1,y-1,w);
               g.add_edge(y-1,x-1,w);
           }
           int source=scan.nextInt();
           int f=g.prim(source-1);
           System.out.println(f);
       
    }
}
