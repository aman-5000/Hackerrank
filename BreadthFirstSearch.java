import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BreadthFirstSearch {
    
   static class graph
   {
      public int V;//no of vert
      public List<Integer> adj[];
      public int[] dist; 
      public int[] par;
      public int edge_val; 
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
          edge_val=6;
      }
      void adde(int v,int w)
      {
          adj[v].add(w); 
          adj[w].add(v);
      }
     
      void bfs(int s)
      {   //s->source
          boolean[] visited=new boolean[V];
          visited[s]=true;
          for(int i=0;i<V;i++)
                  dist[i]=Integer.MAX_VALUE;
          List<Integer> q=new ArrayList<Integer>();
          q.add(s);
          dist[s]=0;
         // int level=0;
          while(!q.isEmpty())
          {
              int u=q.remove(0);
              for(int i:adj[u])
              { if(visited[i]==false)
                {
                  visited[i]=true;
                  if(dist[i]==Integer.MAX_VALUE)
                    { 
                      dist[i]=dist[u]+6;
                      par[i]=u;
                      q.add(i);
                    }
                }
             }
          }
          for(int i=0;i<dist.length;i++)
              if(dist[i]==Integer.MAX_VALUE)
                  dist[i]=-1; 
      }
      
   }
   public static void main(String[] args)
   {       Scanner scan=new Scanner(System.in);
           int T=scan.nextInt();
           for(int i=0;i<T;i++)
           {   int N=scan.nextInt();
               int M=scan.nextInt();
               graph g=new graph(N);
               for(int j=0;j<M;j++)
               {
                  int x=scan.nextInt();
                  int y=scan.nextInt();
                  g.adde(x-1,y-1);
               }
            int source=scan.nextInt();
            g.bfs(source-1); 
            for(int k=0;k<N;k++)
              if(k!=(source-1))  
                System.out.print(g.dist[k]+" ");
            System.out.println();
           }
           
   }
}
