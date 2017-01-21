package tasks.warriors_resurection;

public class Jedi extends AbstractWarrior implements Cloneable {

    public static Jedi yoda;

    private AbstractWarrior victim;

    public Jedi(String name) {
        super(name);
    }

    @Override
    public void kill(AbstractWarrior victim) {

        if(this.victim == null)
            this.victim = victim;

        System.out.format("%s named %s just ends %s with name %s!%n",
                this.getClass().getSimpleName(), this.getName(),
                victim.getClass().getSimpleName(), victim.getName());


    }

    @Override
    protected void finalize() throws  Throwable {
        yoda = this;
        System.out.format("%n\t%s %s is resurrected " +
                          "in the name of the Light!%n%n",
                          this.getClass().getSimpleName(), this.getName());


    }

    @Override
    public Jedi clone() throws CloneNotSupportedException {
        return (Jedi) super.clone();
    }
}
