public class Singleton {
    
    private static Singleton singleton = null;

    public String name;

    private Singleton(){
        name = "PayPal";
    }

    public static synchronized Singleton getInstance(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
        System.out.println(s.name);
    }
    
}
