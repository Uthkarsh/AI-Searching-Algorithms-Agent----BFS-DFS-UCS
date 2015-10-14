import java.math.*;
import java.util.*;
import java.io.*;
class Node implements Comparable<Node>
{
int number;
String state;
int depth;
int type;
int cost;
Node parent=null;
public Node(int no,String s,int d,int c,Node n,int t)
{
	number=no;
	state=s;
	depth=d;
	cost=c;
	parent=n;
	type=t;
}
public int compareTo(Node obj1)
{
	if(this.type==3 && obj1.type==3)
	{
		if(this.cost>obj1.cost)return 1;
		else if(this.cost<obj1.cost)return -1;
		else return 0;
	}
return this.state.compareTo(obj1.state);
}
public boolean equals(Object o)
{
	if(this.state.compareTo(((Node)o).state)==0) return true;
	else return false;
	}
}
public class agent {

	public static void main(String s[])
	{
		Implementation i=new Implementation();
		
	}
}

class Implementation
{

int typeOfSearch,noOfNodes;
String source,destination;
int adj[][];
BufferedReader bf;
int count=0,row=0,row1;
String rowEles[],rowEles1[];
String names[],temp[];
int tempo=0;
public Implementation()
	{
	try{
		bf=new BufferedReader(new FileReader("input.txt"));
		String line = null;
		while ((line = bf.readLine()) != null) {
			//System.out.println(line);
			switch(count)
			{
			case 0: typeOfSearch=Integer.parseInt(line);
					//System.out.println("typeOfSearch="+typeOfSearch);
					break;
			case 1:	source=line.toLowerCase();
					//System.out.println("source="+source);
					break;
			case 2: destination=line.toLowerCase();	
					//System.out.println("destination="+destination);
					break;
			case 3: noOfNodes=Integer.parseInt(line);   
					names=new String[noOfNodes];
					temp=new String[noOfNodes];
					
					rowEles=new String[noOfNodes];
					rowEles1=new String[noOfNodes];
					adj=new int[noOfNodes][noOfNodes];
					//System.out.println("no of nodes="+noOfNodes);
					break;
			default:break; 
			}
			if(count>=4 && count<(4+noOfNodes))
				{
				names[count-4]=line.toLowerCase();
			//	System.out.println(names[count-4]);
					if(count==(3+noOfNodes))
						{
						for(int i=0;i<noOfNodes;i++)temp[i]=names[i];
						Arrays.sort(temp);
					//	Arrays.sort(temp,Collections.reverseOrder());
						//System.out.println("the sorted temp array is and count="+count);
						//for(int i=0;i<noOfNodes;i++)System.out.println(temp[i]);
						}
				}
			if(count>=(4+noOfNodes))
			{
		
				rowEles=line.split(" ");
				for(int i=0;i<noOfNodes;i++)
				{
					if(typeOfSearch==2){//System.out.println("="+Math.abs(Arrays.binarySearch(temp,names[i])-noOfNodes+1));
					rowEles1[Math.abs(Arrays.binarySearch(temp,names[i])-noOfNodes+1)]=rowEles[i];
					}
					else
						rowEles1[Arrays.binarySearch(temp,names[i])]=rowEles[i];

				}
				
			/*	Arrays.sort(rowEles,new Comparator<String>()
						{
					public int compare(String o1, String o2) {
						System.out.println("tempo="+tempo);
						return names[tempo].compareTo(names[++tempo]);
		             //   return (o2).compareTo(o1);
		                }
					
						});*/
			//	System.out.println("After the sorting the array is");
			/*	for(int i=0;i<noOfNodes;i++)
					System.out.print(rowEles1[i]);*/
				tempo=0;
				//System.out.println("\n");
				for(int col=0;col<noOfNodes;col++){
					if(col==0){
						if(typeOfSearch==2)
							row1=Math.abs(Arrays.binarySearch(temp,names[row++])-noOfNodes+1);
						else
							row1=Arrays.binarySearch(temp,names[row++]);

					}
					
					adj[row1][col]=Integer.parseInt(rowEles1[col]);
				}
				
			}
			count++;
			
			//System.out.println(line+" "+count);
		    // ...
			
		}
	//if(typeOfSearch==2)	Arrays.sort(temp,Collections.reverseOrder());
	
		/*for(int i=0;i<noOfNodes;i++)
		{
			for(int j=0;j<noOfNodes;j++)
			{
				System.out.print(adj[i][j]);
			}
			System.out.println();
		}*/
}
		catch(NumberFormatException e)
		{
			System.out.println("Format Exception thrown:"+e+" in line "+count);
		}
		catch(IOException e)
		{
			System.out.println("File opening error :"+e);
		}
		catch(Exception e)
		{
			System.out.println("Exception other than IO or format:"+e+"count="+count);
		}
	switch(typeOfSearch)
	{
	case 1:bfs();
			break;
	case 2:dfs();
			break;
	case 3:ucs();
			break;
	}
	
	}
private void ucs() {
	try
	{
		LinkedList<Node> list = new LinkedList<Node>();
		LinkedList<Node> explored=new LinkedList<Node>();
		LinkedList<Node> sort=new LinkedList<Node>();
		LinkedList<Node> log=new LinkedList<Node>();
		LinkedList<Node> path=new LinkedList<Node>();
		int curCost=0;
		int sourceIndex=Arrays.binarySearch(temp, source);
		int destinationIndex=Arrays.binarySearch(temp, destination);
		//System.out.println("Source="+source+sourceIndex+" Destination="+destination+destinationIndex);
		list.add(new Node(sourceIndex,source,2,0,null,3));
		explored.add(list.peek());
		while(true)
		{
			if(list.size()==0)
			{
				int tempSize=log.size();

String fileName="output.txt";

PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName,false)));

/*for(int i=0;i<tempSize;i++)

{

pw.print(log.remove().state);

if(i!=(tempSize-1))

pw.print("-");

}

pw.println();*/

pw.println("NoPathAvailable");

pw.close();

//System.out.println("No path exists");

System.exit(0);
			}
			Node obj=list.remove();
			//System.out.println("First object removed="+obj.state);
			curCost=obj.cost;
			log.add(obj);
			if(obj.state==temp[destinationIndex]){
				solution(log,obj,path);
				return;
			}
			for(int i=0;i<noOfNodes;i++)
			{
				if(adj[obj.number][i]>0)
				{
					Node tempObj=new Node(i,temp[i],curCost+adj[obj.number][i],curCost+adj[obj.number][i],obj,3);
					for(int j=0;j<list.size();j++)
						{if(tempObj.state==list.get(j).state)
						{
							//System.out.println("Here inside first if");
							if(tempObj.cost<list.get(j).cost)
							{
							//	System.out.println("Here inside second if");
								list.remove(j);
								list.add(tempObj);
							}
						}}
						 if(!explored.contains(tempObj))
					{
						list.add(tempObj);
					//	System.out.println("Added to list="+tempObj.state+" And whose parent node is="+tempObj.parent.state);
						explored.add(tempObj);
					}
			
				}
			}
			for(int i=0;i<list.size();i++)
				list.get(i).type=3;
		Collections.sort(list);
		for(int i=0;i<list.size();i++)
			list.get(i).type=0;
		//Now the list has been sorted according to the costs of the paths.
		//We should now make sure that the the top two nodes with equal costs have been alphabetically sorted
		if(list.size()>1 && list.get(0).cost==list.get(1).cost)
			{
			
						//sort.add(obj);
						while(list.size()>1 && list.get(0).cost==list.get(1).cost)
						{
						//Node obj=(Node)I.next();//check the iterator popping
			
						//obj=()
			
						Node obj1=list.remove();
						sort.add(obj1);
					//	System.out.println("added "+sort.getLast().state);
						}
				//	System.out.println("here");
					Collections.sort(sort);
					/*for(int i=0;i<sort.size();i++)
						System.out.print(sort.get(i).state);*/
		
				//	System.out.println("sort size="+sort.size());
					int tempS=sort.size();
					for(int i=0;i<tempS;i++)
						{
							//System.out.println("\nadded to list="+sort.getLast().state);
							list.addFirst(sort.removeLast());
							//System.out.println("size="+list.size());
						}
			}
		

		}
		
		
	}catch(Exception e)
	{
		System.out.println("Runtime Error"+e);
	}
	// TODO Auto-generated method stub
	
}
private void dfs() {
	// TODO Auto-generated method stub
	try{
		LinkedList<Node> list = new LinkedList<Node>();
		LinkedList<Node> explored=new LinkedList<Node>();
		LinkedList<Node> sort=new LinkedList<Node>();
		LinkedList<Node> log=new LinkedList<Node>();
		LinkedList<Node> path=new LinkedList<Node>();
		int sourceIndex=Math.abs(Arrays.binarySearch(temp, source)-noOfNodes+1);
		int curDepth=0;
		int destinationIndex=Math.abs(Arrays.binarySearch(temp, destination)-noOfNodes+1);
		Arrays.sort(temp,Collections.reverseOrder());
	//	System.out.println("Source="+source+sourceIndex+" Destination="+destination+destinationIndex);
		list.add(new Node(sourceIndex,source,2,0,null,2));
		explored.add(list.peek());
		while(true)
		{
			if(list.size()==0)
			{
int tempSize=log.size();

String fileName="output.txt";

PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName,false)));

/*for(int i=0;i<tempSize;i++)

{

pw.print(log.remove().state);

if(i!=(tempSize-1))

pw.print("-");

}

pw.println();*/

pw.println("NoPathAvailable");

pw.close();

//System.out.println("No path exists");

System.exit(0);
			}
			Node obj=list.removeFirst();
			log.add(obj);
			curDepth=obj.depth;
			//for(int i=0;i<temp.length;i++)System.out.print(temp[i]+"\t");
			if(obj.state==temp[destinationIndex]){
				solution(log,obj,path);
				return;
			}
			//System.out.println("Here");
			for(int i=0;i<noOfNodes;i++)
			{
				if(adj[obj.number][i]>0)
				{
					Node tempObj=new Node(i,temp[i],curDepth+1,adj[obj.number][i],obj,2);
					if(!explored.contains(tempObj))
					{
						list.addFirst(tempObj);
					//	System.out.println("Added to list="+tempObj.state);
						explored.add(tempObj);
					}
					
			
				}
			}
			
			

		}
		
		
	}catch(Exception e)
	{
		System.out.println("Runtime error:"+e);
		
	}
	
}
private void bfs() 
{
	// TODO Auto-generated method stub
// Use a linked list to store the elements inside the queue. Whenever you would want to dequeue some node from the queue, 
	// just create a loop comparing the cost of the nodes. As long as they are equal, keep on comparing the alphabetical order.
	try
	{
	LinkedList<Node> list = new LinkedList<Node>();
	LinkedList<Node> explored=new LinkedList<Node>();
	LinkedList<Node> sort=new LinkedList<Node>();
	LinkedList<Node> log=new LinkedList<Node>();
	LinkedList<Node> path=new LinkedList<Node>();
	int curDepth=10000;
	int sourceIndex=Arrays.binarySearch(temp, source);
	int destinationIndex=Arrays.binarySearch(temp, destination);
//	System.out.println("Source="+source+sourceIndex+" Destination="+destination+destinationIndex);
	list.add(new Node(sourceIndex,source,2,0,null,1));
	explored.add(list.peek());
	/*if(list.peek().state==source)  {
		Node temp=list.pop();
				log.add(temp);
		path.add(temp);
		solution(log,path);

	}*/
	/*Test code*/
	/*list.add(new Node(2,"c",2,0,null));
	list.add(new Node(4,"b",2,0,null));
	list.add(new Node(3,"a",2,0,null));
	list.add(new Node(5,"d",3,0,null));
	list.add(new Node(6,"e",4,0,null));*/
	//System.out.println("added");
	
	/*Test code ends*/
	while(true)
	{
		if(list.size()==0)
		{
			int tempSize=log.size();

String fileName="output.txt";

PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName,false)));

/*for(int i=0;i<tempSize;i++)

{

pw.print(log.remove().state);

if(i!=(tempSize-1))

pw.print("-");

}

pw.println();*/

pw.println("NoPathAvailable");

pw.close();

//System.out.println("No path exists");

System.exit(0);
		}
		//int count=0,cost=0;
		Node obj=list.remove();
		//System.out.println("First object removed="+obj.state);
		curDepth=obj.depth;
	/*	if(list.size()>=1)
		{
			System.out.println("Depths of top two nodes are="+obj.depth+" "+list.get(0).depth);
		System.out.println(obj.state.compareTo(list.get(0).state));
		}*/
		if(list.size()>=1 && obj.depth==list.get(0).depth /*&& obj.state.compareTo(list.get(0).state)>0*/)//Check this condition (The third one)
			{
			
						sort.add(obj);
						while(list.size()!=0 && list.peek().depth==curDepth)
						{
						//Node obj=(Node)I.next();//check the iterator popping
			
						//obj=()
			
						Node obj1=list.remove();
						sort.add(obj1);
						//System.out.println("added "+sort.getLast().state);
						}
				//	System.out.println("here");
					Collections.sort(sort);
					/*for(int i=0;i<sort.size();i++)
						System.out.print(sort.get(i).state);
		
					System.out.println("sort size="+sort.size());*/
					int tempS=sort.size();
					for(int i=0;i<tempS;i++)
						{
							//System.out.println("\nadded to list="+sort.getLast().state);
							list.addFirst(sort.removeLast());
							//System.out.println("size="+list.size());
						}
					obj=list.remove();
					
				}
		log.add(obj);
		if(obj.state==temp[destinationIndex]){
			solution(log,obj,path);
			return;
		}
			for(int i=0;i<noOfNodes;i++)
				{
					if(adj[obj.number][i]>0)
					{
						Node tempObj=new Node(i,temp[i],curDepth+1,adj[obj.number][i],obj,1);
						if(!explored.contains(tempObj))
						{
							list.add(tempObj);
						//	System.out.println("Added to list="+tempObj.state);
							explored.add(tempObj);
						}
				
					}
				}
			}
		/*System.out.println("The sorted list is "+list.size());
		tempS=list.size();
		for(int j=0;j<tempS;j++){
			System.out.print(list.pop().state+"\t");
			}*/
		
	
	}
	catch(Exception e)
	{
		
		System.out.println(e);
	}
	}
	
private void solution(LinkedList<Node> log,Node obj,LinkedList<Node> path) {
	try{
		int UCScost=0;
	String fileName="output.txt";
	
	PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName,false)));
	
	// TODO Auto-generated method stub
	//System.out.println("Solution Found");
	int tempSize=log.size(),cost=0;
	//System.out.println("The path is");
	path.add(obj);
	for(int i=0;i<tempSize;i++)
	{
		cost+=obj.cost;
		if(obj.parent==null)break;
		int index=log.indexOf(obj.parent);
		path.add(log.get(index));
		obj=log.get(index);
			}
	for(int i=0;i<tempSize;i++)
		{
		pw.print(log.remove().state);
		if(i!=(tempSize-1))
			pw.print("-");
				}
	tempSize=path.size();
	pw.println();
	for(int i=0;i<tempSize;i++)
		{
		UCScost=path.peek().cost;
		pw.print(path.removeLast().state);
		if(i!=tempSize-1)
			pw.print("-");
		}
	if(typeOfSearch==3)pw.println("\n"+UCScost);
	else pw.println("\n"+cost);
	pw.close();
	}catch(FileNotFoundException e){
		System.out.println("File not found:"+e);
	}
	catch(IOException e){
		System.out.println("IO error"+e);
		
	}
}

}
