public class LCRS {
    
    public String data;
    public LCRS left;
    public LCRS right;

    public LCRS( String d){ 
        data = d; 
    }

    public void walk(){
        System.out.print(data + "\n");
        if(left != null){
            left.walk();
        }
        if(right != null){
            right.walk();
        }
    }

    public LCRS find_node(LCRS curr, String file){

        if (curr == null) {
            return null; 
        }
        if (curr.data.equals(file)) {
            return curr; 
        }
        LCRS left = find_node(curr.left, file);
        if (left != null) {
            return left;
        }
        return find_node(curr.right, file);
    }

    public LCRS add_child(String key){
        if(left == null){ //no children
            left = new LCRS(key);
            return left;
        }
        else{
            LCRS p = left;
            while(p.right != null){ //itearate over all the children
                p = p.right; 
            }
            p.right = new LCRS(key);
            return p.right;
        }
    }
}


