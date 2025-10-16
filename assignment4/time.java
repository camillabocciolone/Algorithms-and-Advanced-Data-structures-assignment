public class time {

    private long start_time;

    public void StartTime(){
        start_time = System.nanoTime();


    }

    public long EndTime(){
        long end_time = System.nanoTime();
        if (end_time < start_time) {
            throw new IllegalStateException("System clock was adjusted during measurement.");
        }
        //end_time = System.currentTimeMillis();
        return end_time -  start_time;
    }

}

