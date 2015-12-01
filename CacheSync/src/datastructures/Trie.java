package datastructures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Tarana
 */
public class Trie {
    Node root = new Node(null, "", 0);
    int nodeCount=1;
    int wordCount=0;
    
    private class Node{
        Node parent;
        String letter;
        Integer freq;
        boolean EOWflag;
        List<Node> children =new LinkedList<> ();
        
        Node(Node parent, String phrase, Integer freq){
            this.parent = parent;
            this.letter=phrase;
            this.freq=freq;
            this.EOWflag=false;
            nodeCount+=1;
        } 
    
    }
    
   //METHODS
    public int getNodeCount(){
        return this.nodeCount;
    }
    public int getWordCount(){
        return this.wordCount;
    }
    public void add(String phrase, Integer freq){
        add_help(root, phrase, freq);
    }
    public void add_help(Node n1, String phrase, Integer freq){
        for(Node c : n1.children){
            if(phrase.substring(0, 1).equals(c.letter)){
                if(phrase.length()==1){
                    c.EOWflag=true;
                    c.freq=freq;
                    wordCount+=1;
                }
                else add_help(c, phrase.substring(1),freq);//add rest of letters in phrase
               return;
            }
        }
        Node c_new=new Node(n1, phrase.substring(0, 1), 0);
        n1.children.add(c_new);
        if(phrase.length()==1){
            c_new.EOWflag=true;
            c_new.freq=freq;
            wordCount+=1;
        }
        else add_help(c_new, phrase.substring(1),freq);
        return;
    } 
    
    
    public List<String> search(String prefix){
        List<Node> max=new ArrayList<Node>(Arrays.asList(root,root, root, root));//list check
        List<String> result=new ArrayList<String>(Arrays.asList("","","",""));
        int min=0;
        /*max.add(root);
        max.add(root);
        max.add(root);
        max.add(root);*/
        Node source=findEnd(prefix, root);
        if(source==null){
            System.out.print("Prefix not in Trie");
            return result;//prefix not in trie
        }
        //System.out.print("My node letter is "+ source.letter);
        else {//BFS search
            Queue<Node> bfsQueue=new ArrayDeque<>();
            bfsQueue.add(source);
            while(!bfsQueue.isEmpty()){
                Node n=bfsQueue.poll();
                for(Node c: n.children){
                    bfsQueue.add(c);
                    if(c.EOWflag){
                        for(Node comp: max){//find node with min freq, replace with c
                            if(comp.freq < max.get(min).freq)
                            {
                                min=max.indexOf(comp);
                                //System.out.print(min);
                            }
                        }
                        System.out.print(Integer.toString(min)+ "\n");
                        if(c.freq > max.get(min).freq){//at min index add c
                            max.remove(min);
                            System.out.print(max.get(min).letter);
                            max.set(min,c);
                        }
                    }                  
                }            
            }
            int ii=0;
            for(Node n : max){
                result.add(ii,findWord(n,""));
                ii++;
            }
         return result;         
        }
    }
        public List<String> list_all(){
            List<Node> allNodes=new ArrayList<Node>();
            List<String> allWords=new ArrayList<String>();
            Node source=root;
            Queue<Node> bfsQueue=new ArrayDeque<>();
            bfsQueue.add(source);
            while(!bfsQueue.isEmpty()){
                Node n=bfsQueue.poll();
                for(Node c: n.children){
                    bfsQueue.add(c);
                    if(c.EOWflag){
                        allNodes.add(c);
                    }                  
                }           
            }
            int ii=0;
            for(Node n : allNodes){
                allWords.add(ii,findWord(n,""));
                ii++;
            }
            return allWords;         
        }


    private Node findEnd(String prefix, Node source){//Find end node of prefix
        for(Node child : source.children){
            if(prefix.substring(0, 1).equals(child.letter)){
                if(prefix.length()==1){
                    return child;
                }
                else return findEnd(prefix.substring(1), child);
            }
        }
        return null; //string is not in Trie
    }
        
        private String findWord(Node n, String s){//Find end node of prefix
         String result= n.letter+s;  
         if (n.parent==root)
               return result;
         return findWord(n.parent, result);
        }  
   
    public int getFreq(String s){
           Node n=findEnd(s, root);
           if(n==null)return 0;
           return n.freq;
        }   
   
     
        
    
    public static void main(String[] args) {
        Trie entries=new Trie();
        
        entries.add("hellllooo", 2);
        entries.add("hell", 10);
        entries.add("halo", 6);
        entries.add("helly", 4);
        entries.add("helo", 12);
        entries.add("herl", 1);
        entries.add("hello", 3);
        entries.add("helllllly", 7);
        entries.add("ok", 2);
        entries.add("this", 4);
        int myCount=entries.getNodeCount();
        
        List<String> matches= new ArrayList<>();
        matches=entries.search("hel");
//        matches=entries.list_all();
        System.out.println("Number of Nodes: "+ myCount);
        for(String s: matches){
            System.out.println(s);
        }
        
        
    }
     
}
