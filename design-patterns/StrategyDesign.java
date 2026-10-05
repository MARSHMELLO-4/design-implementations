interface Walkable {
    public void walk();
}

class NormalWalk implements Walkable {
    @Override 
    public void walk () {
        System.out.println("Normal Walking...");
    }
}

class NonWalk implements  Walkable {
    @Override 
    public void walk(){
        System.out.println("Not walking...");
    }
}

interface Talkable {
    public void talk();
}


class NormalTalk implements Talkable {
    @Override 
    public void talk(){
        System.out.println("Normal Talking");
    }
}


class NoTalk implements  Talkable {
    @Override 
    public void talk(){
        System.out.println("No Talking");
    }
}

interface Flyble {
    public void fly();
}


class NormalFly implements  Flyble {
    @Override 
    public void fly(){
        System.out.println("Normal FLying");
    }
}


class NoFly implements  Flyble {
    @Override 
    public void fly() {
        System.out.println("No Flying");
    }
}


//after defining the strategy we have to create the main class 

class Robot{

    private Walkable walkable;
    private Talkable talkable;
    private Flyble flyble;

    public Robot(
        Walkable walkable,
        Talkable talkable,
        Flyble flyble
    ) {
        this.walkable = walkable;
        this.flyble = flyble;
        this.talkable = talkable;
    }


    //now we have to define the methds

    void walk () {
        walkable.walk();
    }

    void talk(){
        talkable.talk();
    }

    void fly() {
        flyble.fly();
    }

}

public class StrategyDesign {
    public static void main(String[] args) {
        Robot r1 = new Robot(new NormalWalk(), new NormalTalk(), new NoFly());

        r1.walk();
        r1.talk();
        r1.fly();
    }
}



