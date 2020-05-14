package laba;
import java.util.ArrayDeque;
import java.util.Deque;

public class ProcessDispatcher {
	private TimeManager timeManager;
	private static final int processWorkingTime = 7;
	private Deque<Process> processes;
	private Deque<IOImitation> ioArrayDeque;
	private int generalTime = 0;
	private boolean blockingModeOn;
	private static final int quant = 5;
	private static final int processNumber = 5;
	
	public void withoutBlocking(Process process, Deque<Process> processes, Deque<IOImitation> ioArrayDeque){
		IOImitation ioWork = ioArrayDeque.poll();
		generalTime += ioWork.getWorkingTime();
		process = ioWork.getProcess();
		process.setBlock(false);
		processes.addFirst(process);
		System.out.printf("Process id " + process.getId() +  " suspended with I/O resource\n");
	}
	public ProcessDispatcher(boolean blockingModeOn) {
    	this.blockingModeOn = blockingModeOn;
    	timeManager = new TimeManager(quant);
    	int number = processNumber;
    	processes = new ArrayDeque<>();
    	ioArrayDeque = new ArrayDeque<>();
    	for (int i = 0; i < number; i++) { 
        	processes.add(new Process(i,  Math.abs(Main.numb.nextInt()% quant + processWorkingTime)));
        	System.out.print(processes.getLast().getTime() + " ");
    	}
		System.out.print("\n");

	}
	public void run() { 
    	while (!processes.isEmpty() || !ioArrayDeque.isEmpty()) {
        	Process process = processes.poll();
			if (process != null && !process.getAppealByIO() && Main.numb.nextInt() % 2 == 0){ 
				process.setBlock(true);
				process.setAppealByIO(true);
				ioArrayDeque.add(new IOImitation(process, 7*processWorkingTime));
				if(!blockingModeOn) { 
					withoutBlocking(process, processes, ioArrayDeque);
				}
			}
			else {
				if (process != null) {
					generalTime += process.getTime();
					process.move(quant);
					if (timeManager.checkIfProcessFinished(process) == State.WORKING) {
						processes.add(process);
					} else {
						System.out.printf("Process id %s finished\n", process.getId());
					}
				}
				else {
					generalTime += quant;
				}
				if (!ioArrayDeque.isEmpty()) {
					if (timeManager.compareTime(ioArrayDeque)) {
						ioArrayDeque.getFirst().tick(quant);
					}
					else {
						process = ioArrayDeque.poll().getProcess(); 
						processes.addFirst(process);
						System.out.printf("Process id %s suspended with I/O resource\n", process.getId());
						continue;
					}
				}
			}
		}
    	System.out.printf("_______________________________\n");
	}

	public int getGeneralTimeWork() {
		return generalTime;
	}
}

