public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
        if(rest == null){
           return 1;
        }
		return 1 + this.rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
        IntList p = this;
        int size = 0;
        while(p != null){
            size += 1;
            p = p.rest;
        }
		return size;
	}

	/** Returns the ith value in this list.*/
	public int get(int i) {
        IntList p = this;
        if(i == 0){
            return p.first;
        }
        
		return p.rest.get(i - 1);
	}

    public static IntList incrListIterative(IntList L, int x) {  
        IntList p = new IntList(L.first + x , null);//not recursion so just execute once
        IntList dummy = p;// linkedList should know the first node and read the last till null automatlly
        IntList y = L;//keep original L first node immutable
        while (y.rest != null){
            y = y.rest;
        //  L = L.rest;
        //  L.first = L.first + x;
        //  p.rest = L;
        //  p = p.rest;// should move p 
        p.rest = new IntList(y.first + x, null);
        p = p.rest;
        }
        return dummy;
    }
 
    // non-destructive
    public static IntList incrListRecursion(IntList L, int x) {
      
        IntList y = L;//keep original L first node immutable
        if (y == null){
            return null;
        }
        
        // IntList p = new IntList(y.first + x, null);//create the first point
        // IntList dummy = p;// linkedList should know the first node and read the last till null automatlly
        // p = p.rest;
        // incrListRecursion(y.rest, x); //instantlizad the rest 
        return new IntList(L.first + x,incrListRecursion(L.rest,x) );
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrListIterative(IntList L, int x) {
        IntList p = L;
        while(p != null){
            p.first = p.first + x;
            p = p.rest;
        }
       
        return L;
    }

    public static IntList dincrListRecursion(IntList L, int x) {
    
        if (L == null){
            return null;
        }
        L.first = L.first + x;
        dincrListRecursion(L.rest, x);   
        return L;
    }
        

             
    

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        System.out.println(L.size());
        System.out.println(L.iterativeSize());

        System.out.println(L.get(1));

         //System.out.println(incrListIterative(L, 3));       
         //System.out.println(incrListRecursion(L, 3));
        //System.out.println(dincrListRecursion(L, 3));       
        System.out.println(dincrListIterative(L, 3));       
        
        

    }


} 