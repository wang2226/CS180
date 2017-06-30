public class Exam
{
    private int x;
    public Exam() {x = 0;}
    public Exam(int x) {setX(x);}
    public Exam(Exam e) {x = e.getX();}
    public int getX() {return x + 10;}
    public void setX(int x) {this.x = x;}
    public void modifyX(Exam e) {this.x = e.getX() + 10;}
    public static void main(String[] args) {
        Exam e1 = new Exam();
        Exam e2 = new Exam(e1);
        e2.modifyX(e1);
        System.out.println(e1.getX() + "::" + e2.getX());
    }
}