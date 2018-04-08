import java.util.ArrayList;

public class LRU {
    private ArrayList<Page> pages;

    public LRU(ArrayList<Page> pagesList) {
        pages = pagesList;
    }

    public int pageFaults(int frameSize){
        int pFaults = 0;

        Memory memory = new Memory(frameSize);


        int pageOrder = 1;
        for (Page thisPage:
                pages) {

            int pageInMemory = memory.IsInMemory(thisPage);
            if(pageInMemory == -1) { //if NOT in memory
                int minOrder = findMinOrder(memory.frames);
                memory.frames[minOrder].page = thisPage;
                memory.frames[minOrder].orderOfLastAccess = pageOrder;
                pFaults++;
            } else{//if in memory

                memory.frames[pageInMemory].orderOfLastAccess = pageOrder;
            }

            System.out.println("-----------");
            for (Frame f:
                    memory.frames) {
                System.out.println(f.page.processNum + " " + f.page.pageNum);
            }

            pageOrder++;
        }

        return pFaults;
    }

    int findMinOrder(Frame[] frames){
        int minIndex = 0;
        int min = frames[minIndex].orderOfLastAccess;

        for (int i=0; i < frames.length; i++){
            if(frames[i].orderOfLastAccess < min){
                min = frames[i].orderOfLastAccess;
                minIndex = i;
            }
        }
        return minIndex;
    }
}
