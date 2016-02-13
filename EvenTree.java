import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class EvenTree {
    
   static class graph
   {
      public int V;//no of vert
      public List<Integer> adj[];
      public int[] dist; 
      public int[] par; 
      graph(int v)
      {
         V=v;
         adj=new ArrayList[V];
         for(int i=0;i<v;i++)
             adj[i]=new ArrayList<Integer>();
         dist=new int[V]; 
          par=new int[V];
         for(int i=0;i<V;i++)
             dist[i]=-1;
          for(int i=0;i<V;i++)
             par[i]=-1;
      }
      void adde(int v,int w)
      {
          //adj[v].add(w); 
          adj[w].add(v);
          par[v]=w;
      }
      public int max_del(int root)
      {  int m=0;
         for(int i:child(root))
         {
             if(num_child(i)%2!=0)
             {   
                 m+=(1+max_del(i));
             }
             else
             {
                  m+=max_del(i);    
             }
         }
         return m;  
      }
      public List<Integer> child(int root)
      {  List<Integer> l=new ArrayList<Integer>();
         for(int i=0;i<V;i++)
             if(par[i]==root)
                l.add(i);
         return l;    
      }
      public int num_child(int p)
      {  int tot=0;
         if(p!=-1)
         for(int i=0;i<V;i++)
         {
             if(par[i]==p)
             {  
                tot+=(1+num_child(i));
             }
         }
         return tot; 
       }
     }
   public static void main(String[] args)
   {       Scanner scan=new Scanner(System.in);
               int N=scan.nextInt();
               int M=scan.nextInt();
               graph g=new graph(N);
               for(int j=0;j<M;j++)
               {
                  int x=scan.nextInt();
                  int y=scan.nextInt();
                  g.adde(x-1,y-1);
               }
               int root=0;
               for(int i=0;i<g.V;i++)
                   if(g.par[i]==-1)
                    {
                        root=i;
                        break;
                    } 
               System.out.println(g.max_del(root));
            //System.out.println(g.num_child(1));
          //           for(int i=0;i<g.V;i++)
            // System.out.print(g.par[i]+" ");
   }
}
