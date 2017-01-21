package tasks.warriors_resurection;

public abstract class AbstractWarrior implements Killer {

    private String name;

    public String getName() {
        return name;
    }

    public AbstractWarrior(String name) {
        this.name = name;
    }
}
