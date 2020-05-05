package game;

public class Game {

    private String title;
    private double specialprice;
    private double normalprice;
    private GameStatus status;

    public Game(String title, double price) {

        this.title = title;
        this.specialprice = price;
        this.normalprice = price;
        this.status = GameStatus.ONSALED;

    }

//    public void genID(){
//        if(DBmanager.incrementLastGameID()==1){
//            this.id = 200000000;
//            DBmanager.keepGameInfo(this);
//        }else{
//            this.id=DBmanager.incrementLastGameID();
//            DBmanager.keepGameInfo(this);
//        }
//        //this.uniqueId = 1000000;
//    }
    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GameStatus getStatus() {
        return status;
    }

    public double getSpecialPrice() {
        return specialprice;
    }

    public double getNormalprice() {
        return normalprice;
    }

    @Override
    public String toString() {
        return "Game{title=" + title + ", price=" + specialprice + '}';
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(double price) {
        this.specialprice = price;
    }

//    public long getId() {
//        return id;
//    }
}
