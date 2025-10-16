public class time {

    private long start_time;

    public void StartTime(){
        start_time = System.nanoTime();

    }

    public long EndTime(){
        long end_time = System.nanoTime();
        //end_time = System.currentTimeMillis();
        return end_time -  start_time;
    }

}


