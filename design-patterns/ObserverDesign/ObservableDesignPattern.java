import java.util.ArrayList;
import java.util.List;

interface ISubscriber {
    public void update();
}

interface IChannel {
    public void subscribe(ISubscriber subscribe);
    public void unsubscribe(ISubscriber subscriber);
    public void notifySubscribers();
}

class Channel implements IChannel {
    private List<ISubscriber> subscribers;
    private String name;
    private String latestVideo;

    public Channel(String name){
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    @Override 
    public void subscribe(ISubscriber subscriber){
        if(!subscribers.contains(subscriber)){
            subscribers.add(subscriber);
        }
    }

    @Override 
    public void unsubscribe(ISubscriber subscriber){
        subscribers.remove(subscriber);
    }

    @Override 
    public void notifySubscribers(){
        for(ISubscriber sub : subscribers){
            sub.update();
        }
    }

    public void uploadVideo(String title){
        latestVideo = title;
        System.out.println("\n[" + name + " uploaded \"" + title + "\"]");
        notifySubscribers();
    }

    public String getVideoData(){
        return "\nCheckout our new Video : " + latestVideo + "\n";
    }
}


class Subscriber implements ISubscriber {
    private String name;
    private Channel channel;

    public Subscriber(String name, Channel channel){
        this.name = name;
        this.channel = channel;
    }

    @Override 
    public void update(){
        System.out.println("Hey " + name + "," + channel.getVideoData());
    }
}


public class ObservableDesignPattern {
    public static void main(String[] args) {
        //creating the channel first 
        Channel channel = new Channel("Aman's YT Channel");

        //now create the subscribers
        Subscriber s1 = new Subscriber("Aman", channel);
        Subscriber s2 = new Subscriber("Harsh", channel);


        //subscribe to the channel now 
        channel.subscribe(s1);
        channel.subscribe(s2);

        //now upload the video 
        channel.uploadVideo("Learn how to drive car");

        channel.unsubscribe(s1);

        channel.uploadVideo("Learn how to drive bike");
    }
}