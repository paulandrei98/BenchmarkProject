package CPU;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import Benchmark.IBenchmark;

public class CPUCompression implements IBenchmark{
	String str;
	static ArrayList<String> words = new ArrayList<String>();
	
	public void initialize(String str) {
		this.str=str;	
	}

	@Override
	public void run() {
		
		
	}

	@Override
	public void run(Object... objects) {
		int option = (Integer) objects[0];
		
		switch(option) {
		case 1:
			for(int i=0; i<words.size(); i++) {
				new HuffmanCompressor(words.get(i));
			}
		}
	}


		
		
	@Override
	public void warmUp() {
		this.run(1);
	}



	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

	
	public class HuffmanCompressor {
		private  Node root;
	    private String string;
	    private int[] arr;
	    private HashMap<Character, String> dic;
	    
	    public HuffmanCompressor(String string) {
	        root=null;
	        this.string=string;
	        this.arr=getFrequencies();
	        this.buildPrefixTree();
	        dic=new HashMap<>();
	    }

	    private int[]  getFrequencies () {
	        int[] freqArray=new int[65536];
	        for (char c : string.toCharArray())
	            freqArray[c]++;
	        return freqArray;
	    }

	    private void buildPrefixTree () {
	    	PriorityQueue<Node> q=new PriorityQueue<Node>(new NodeComparator());
	        for (int i=0; i<arr.length; i++) {
	            if (arr[i]!=0) {
	                Node n = new Node(arr[i], (char) i);
	                q.offer(n);
	            }
	        }
	        while (q.size()!=1) {
	            Node x,y,z;
	            x=q.poll();
	            y=q.poll();
	            z=new Node(x.getFrequency()+y.getFrequency());
	            z.setLchild(x);
	            z.setRchild(y);
	            q.add(z);
	        }
	        this.root=q.poll();
	    }

	    private void showCodesUtil (Node x, String s) {
	        if (x.getLchild()==null && x.getRchild()==null){
	            System.out.println(x.getC()+ ": " + s);
	            dic.put(x.getC(),s);
	        }
	        if (x.getLchild()!=null)
	            showCodesUtil(x.getLchild(),s+"0");
	        if (x.getRchild()!=null)
	            showCodesUtil(x.getRchild(),s+"1");
	    }

	    public void showCodes () {
	        showCodesUtil(root,"");
	    }

	    public String compress () {
	        StringBuffer compressed= new StringBuffer();
	        for (char c : string.toCharArray()) {
	           compressed.append(dic.get(c));
	        }
	        return compressed.toString();
	    }

	    public Node getRoot() {
	        return root;
	    }
	}

	class Node {
	    private int frequency;
	    private char c=0;
	    private Node lchild, rchild;

	    public Node (int f) {
	        this.frequency=f;
	        this.lchild=null;
	        this.rchild=null;
	    }
	    
	    public Node (int f, char c) {
	        this.frequency=f;
	        this.c=c;
	        this.lchild=null;
	        this.rchild=null;
	    }

	    public char getC() {
	        return c;
	    }

	    public int getFrequency() {
	        return frequency;
	    }

	    public void setLchild(Node lchild) {
	        this.lchild = lchild;
	    }

	    public void setRchild(Node rchild) {
	        this.rchild = rchild;
	    }

	    public Node getLchild() {
	        return lchild;
	    }

	    public Node getRchild() {
	        return rchild;
	    }

	    @Override
	    public String toString() {
	        return c+" "+frequency;
	    }
	}

	class NodeComparator implements Comparator<Node> {
		@Override
	    public int compare(Node node, Node t1) {
	        if (node.getFrequency()-t1.getFrequency()==0)
	            return node.getC()-t1.getC();
	        return node.getFrequency()-t1.getFrequency();
	    }
	}

	@Override
	public void initialize(ArrayList<String> words) {
		this.words=words;
		
	}

	@Override
	public void initialize(int size) {
		// TODO Auto-generated method stub
		
	}
}
