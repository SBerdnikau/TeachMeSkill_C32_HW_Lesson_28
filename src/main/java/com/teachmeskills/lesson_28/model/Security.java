package com.teachmeskills.lesson_28.model;

public class Security {
    private int id;
    private String login;
    private String password;
    private int idUserFk;

    public Security() {
    }

    public Security(int id, String login, String password, int idUserFk) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.idUserFk = idUserFk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUserFk() {
        return idUserFk;
    }

    public void setIdUserFk(int idUserFk) {
        this.idUserFk = idUserFk;
    }
}
