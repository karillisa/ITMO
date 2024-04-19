package process;

import params.Job;

public class Work {
    private String name;
    private Status status;
    private final Job type;

    public Work(String name, Status status, Job type) {
        this.name = name;
        this.status = status;
        this.type = type;
    }

    public Job getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public void finishWork() {
        this.status = Status.FINISH;
    }

    public void startWork() {
        this.status = Status.START;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
