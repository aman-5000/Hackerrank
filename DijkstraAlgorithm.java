import java.io.*;
import java.util.*;

public class DijkstraAlgorithm{

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
                    dist[i]=10000;   
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
           public void dijkstra(int s)
           {   
               List<Integer> l=new ArrayList<Integer>();
               for(int i=0;i<V;i++)
                   l.add(i);
               dist[s]=0;
               while(!l.isEmpty())
               {   
                   int min=Integer.MAX_VALUE,temp_i=-1;
                   for(int i=0;i<l.size();i++)
                   {
                       if(dist[l.get(i)]<min)
                       {
                           min=dist[l.get(i)];
                           temp_i=l.get(i);
                       }
                   }
                   if(temp_i!=-1)
                        l.remove(l.indexOf(temp_i));
                   else
                   {
                       if(!l.isEmpty())
                       {
                          temp_i=l.get(0);
                          l.remove(l.indexOf(temp_i)); 
                       }
                   }
                   for (Map.Entry<Integer,Map<Integer,Integer>> entry : adj.entrySet())
                       {    
                            if(entry.getKey()==temp_i)
                            {
                                Map<Integer,Integer> m1=new HashMap<Integer,Integer>();
                                m1=entry.getValue();
                                for(Map.Entry<Integer,Integer> ent:m1.entrySet())
                                {  int alt=dist[temp_i]+ent.getValue();
                                   if(alt<dist[ent.getKey()])
                                        dist[ent.getKey()]=alt; 
                                }
                            }
                       }                 
               } 
               for(int i=0;i<V;i++)
                   if(dist[i]==10000)
                       dist[i]=-1;
           }
       }
    public static void main(String[] args) {
       Scanner scan=new Scanner(System.in);
       int T=scan.nextInt();
       for(int i=0;i<T;i++)
       {
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
           g.dijkstra(source-1);
           for(int a=0;a<g.dist.length;a++)
             if(a!=(source-1))  
               System.out.print(g.dist[a]+" ");
           System.out.println();
       }
    }
}
