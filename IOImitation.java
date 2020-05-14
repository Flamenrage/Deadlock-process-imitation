package laba;

public class IOImitation {
    private Process process;
    private int workingTime;

    public IOImitation(Process process, int workingTime) {
        this.process = process;
        this.workingTime = workingTime;
    }

    public Process getProcess() {
        return process;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void tick(int time) {
        	workingTime -= time;
    }
}

