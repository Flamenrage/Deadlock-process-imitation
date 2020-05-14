package laba;
public class Process {
	private int id;
	private int time;
	private boolean blockOn;
	private boolean appealByIo;

	public Process(int id, int time) {
    	this.id = id;
    	this.time = time;
    	blockOn = false;
    	appealByIo = false;
	}


	public int getTime() {
    	return time;
	}

	public boolean getBlockOn() {
		return blockOn;
	}

	public boolean getAppealByIO() {
		return appealByIo;
	}

	public void setAppealByIO(boolean requestIO) {
		this.appealByIo = requestIO;
	}

	public void setBlock(boolean block) {
		this.blockOn = block;
	}

	public int getId() {
		return id;
	}

	public void move(int time) {
		this.time -= time;
	}
}
