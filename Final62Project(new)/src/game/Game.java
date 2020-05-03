package game;


import java.util.Objects;


public class Game {
    private String id;
    private String title;
    private double specialprice;
    private double normalprice;
    private GameStatus status;

    public Game(String id, String title, double price) {
        this.id = id;
        this.title = title;
        this.specialprice = price;
        this.normalprice = price;
        this.status = GameStatus.ONSALED;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
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
        return "Game{" + "id=" + id + ", title=" + title + ", price=" + specialprice + '}' ;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(double price) {
        this.specialprice = price;
    }

    
    
}
