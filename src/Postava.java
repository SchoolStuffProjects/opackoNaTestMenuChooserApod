public class Postava {
    private String name;
    private int cost;
    private int star;
    private boolean headLiner;

    public Postava(String name, int cost, int star, boolean headLiner) {
        this.name = name;
        this.cost = cost;
        this.star = star;
        this.headLiner = headLiner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public boolean isHeadLiner() {
        return headLiner;
    }

    public void setHeadLiner(boolean headLiner) {
        this.headLiner = headLiner;
    }
}
