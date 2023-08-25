package curdrepo;

public abstract class curdRepo {

    public abstract void createdb(String query);
    public abstract void createTable(String query);
    public abstract void deleteTable(String query);
    public abstract void insertInto(String query);
    public abstract void select(String query);

    public abstract void update(String query);

    public abstract boolean signInSignup();
}
