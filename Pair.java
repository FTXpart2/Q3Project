 public class Pair<A, B> {
    private A item1;
    private B item2;
  
    public Pair(A item1, B item2) {
      this.item1 = item1;
      this.item2 = item2;
    }
  
    public A getItemA() {
      return item1;
    }
  
    public B getItemB() {
      return item2;
    }
  
}
