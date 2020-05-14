package laba;

import java.util.Deque;

public class TimeManager {
	
	private int quant;
	
	public TimeManager(int quant)
	{
		this.quant = quant;
	}
	
	public State checkIfProcessFinished(Process process){
		int time = process.getTime();
		if (time <= 0){
			return State.FINISHED;
		}
		return State.WORKING;
	}
	
	public boolean compareTime(Deque<IOImitation> ioArrayDeque){
		if (ioArrayDeque.getFirst().getWorkingTime() > quant){
			return true;
		}
		return false;
	}
}
