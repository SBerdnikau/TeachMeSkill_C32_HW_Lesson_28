package com.teachmeskills.lesson_28.model;

import java.sql.Date;

public class Task {
    private long id;
    private String description;
    private Date created;
    private Date changed;
    private int idUserFk;

    public Task() {
    }

    public Task(long id, String description, Date created, Date changed, int idUserFk) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.changed = changed;
        this.idUserFk = idUserFk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getChanged() {
        return changed;
    }

    public void setChanged(Date changed) {
        this.changed = changed;
    }

    public int getIdUserFk() {
        return idUserFk;
    }

    public void setIdUserFk(int idUserFk) {
        this.idUserFk = idUserFk;
    }


}
