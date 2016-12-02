package com.csci5448.accounts;

import com.csci5448.content.Player;
import com.csci5448.content.Team;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserAccount extends Account {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "teams_users", joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> favoriteTeams = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "players_users", joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> favoritePlayers = new HashSet<>(0);

    public UserAccount() {}

    public UserAccount(String username, String password, boolean activated) {
        super(username, password, activated);
    }

    public void addFavoriteTeam(Team team) {
        favoriteTeams.add(team);
    }

    public void removeFavoriteTeam(Team team) {
        favoriteTeams.remove(team);
    }

    public void addFavoritePlayer(Player player) {
        favoritePlayers.add(player);
    }

    public void removeFavoritePlayer(Player player) {
        favoritePlayers.remove(player);
    }

    public Set<Team> getFavoriteTeams() {
        return favoriteTeams;
    }

    public void setFavoriteTeams(Set<Team> favoriteTeams) {
        this.favoriteTeams = favoriteTeams;
    }

    public Set<Player> getFavoritePlayers() {
        return favoritePlayers;
    }

    public void setFavoritePlayers(Set<Player> favoritePlayers) {
        this.favoritePlayers = favoritePlayers;
    }

}
