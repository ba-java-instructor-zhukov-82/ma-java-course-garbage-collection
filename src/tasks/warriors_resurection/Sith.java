package tasks.warriors_resurection;

public class Sith extends  AbstractWarrior {

    public Sith(String name) {
        super(name);
    }

    @Override
    public void kill(AbstractWarrior victim) {

        System.out.format("%s named %s just ends %s with name %s!%n",
                this.getClass().getSimpleName(), this.getName(),
                victim.getClass().getSimpleName(), victim.getName());

        if(victim instanceof Jedi) {

            Jedi resurrection = null;
            try {
                resurrection = Jedi.yoda.clone();

                resurrection = null;
                System.gc();

                Thread.sleep(1000);
            } catch (CloneNotSupportedException | InterruptedException e) {
                e.printStackTrace();
            }

            Jedi.yoda.kill(this);
        }
    }
}
