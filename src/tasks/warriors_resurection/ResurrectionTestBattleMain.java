package tasks.warriors_resurection;

public class ResurrectionTestBattleMain {

    public static void main(String[] args) throws InterruptedException {
        battleOfTheLightAndDarknessTest();
    }

    private static void battleOfTheLightAndDarknessTest() throws InterruptedException {
        Sith dartSidius = new Sith("Dart Sidius");
        Jedi.yoda = new Jedi("Yoda");

        dartSidius.kill(Jedi.yoda);
    }
}
