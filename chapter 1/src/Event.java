public class Event {
    private long timestamp;

    public Event(){
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp(){
        return timestamp;
    }
}
