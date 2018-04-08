import java.util.ArrayList;

public class FIFO {
    private ArrayList<Page> pages;

    public FIFO(ArrayList<Page> pagesList) {
        pages = pagesList;
    }

    public int pageFaults(int frameSize){
        int pFaults = 0;
        Memory memory = new Memory(frameSize);

        return pFaults;
    }
}
