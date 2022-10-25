import Agent.Agent;

public class StudyRoomSemaphore implements Runnable {

    public StudyRoomSemaphore(Agent[] agents) {}

    private void preProtocol() {

    }

    private void criticalSection() {
    }

    private void postProtocol() {

    }

    @Override
    public void run() {
        preProtocol();
        criticalSection();
        postProtocol();
    }
}
