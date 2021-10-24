package service;


public interface CustomerService {
    public abstract boolean addGameToCart(GameStore gameStore, String title);
    public abstract boolean removeGameFromCart(String title);

}
