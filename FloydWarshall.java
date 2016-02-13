import java.io.*;
import java.util.*;
public class FloydWarshall 
{ 
  static class graph
  {
      int V=0;
      int[][] distance;
      graph(int v)
      {
           V=v;
           distance=new int[V][V];
          for(int i=0;i<V;i++)
              for(int j=0;j<V;j++)
                        distance[i][j]=-1;
      }
      public void floyd()
      {
          for(int i=0;i<V;i++)
              for(int j=0;j<V;j++)
                        if(i==j)
                            distance[i][j]=0;
              
            for (int k = 0; k < V; k++)
                 for (int i = 0; i < V; i++)
                       for (int j = 0; j < V; j++)
                            if (distance[i][k] >= 0 && distance[k][j] >= 0
            && (distance[i][j] == -1 || distance[i][j] > distance[i][k] + distance[k][j])) 
                    {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        }   
      }
  }
  public static void main(String[] args)
  {
       Scanner scan=new Scanner(System.in);
           int N=scan.nextInt();
           int M=scan.nextInt();
           graph g=new graph(N);
           for(int j=0;j<M;j++)
           {   
               int x=scan.nextInt();
               int y=scan.nextInt();
               int w=scan.nextInt();
               g.distance[x-1][y-1]=w;
           }
           
           int Q=scan.nextInt();
           g.floyd();
           for(int j=0;j<Q;j++)
           {
               int a=scan.nextInt();
               int b=scan.nextInt();
               System.out.println(g.distance[a-1][b-1]);
           }  
      
  }
    
}
