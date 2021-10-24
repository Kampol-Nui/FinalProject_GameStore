package service;


import game.Game;


public interface AdminService {
    public abstract boolean addGameFrom(AdminAccount admin, Game game);
    public abstract boolean removeGame(AdminAccount admin, Game game);
 
}
