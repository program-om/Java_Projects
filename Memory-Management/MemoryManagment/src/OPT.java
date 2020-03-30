import java.util.ArrayList;

public class OPT {
    private ArrayList<Page> pages;
    public OPT(ArrayList<Page> pagesList) {
        pages = pagesList;
    }


    public int pageFaults(int frameSize){
        int pFaults = 0;

        Memory memory = new Memory(frameSize);

        int allocatedFrames = 0;
        Page thisPage;
        for (int i=0; i < pages.size(); i++){

            thisPage = pages.get(i);
            int indexInMemory = memory.IsInMemory(thisPage);

            if(indexInMemory == -1){ //Not in the main memory
                if (allocatedFrames < memory.frames.length){//there is free frame
                    memory.frames[allocatedFrames].page = thisPage;
                    allocatedFrames++;
                } else { //there is no free frame - replacement
                    int furthestPage = lessLikelyInFuture(memory.frames, memory, i+1);
                    memory.frames[furthestPage].page = thisPage;
                }
                pFaults++;
            }

        }

        return pFaults;
    }

    private int lessLikelyInFuture(Frame[] frames, Memory mem, int startIndex){

        int max, maxIndex=0;
        int[] earliestSeen = new int[frames.length];

        for(int i=0; i < earliestSeen.length; i++){
            earliestSeen[i] = -1;
        }

        for (int i = startIndex; i < pages.size(); i++) {
            //is this page in memory
            int indexInMemory = mem.IsInMemory(pages.get(i));

            if (indexInMemory != -1 && earliestSeen[indexInMemory] == -1) {
                earliestSeen[indexInMemory] = i;
            }
        }

        max = earliestSeen[maxIndex];
        for (int i=0; i < earliestSeen.length; i++) {

            if(earliestSeen[i] == -1){
                return i;
            } else if (earliestSeen[i] > max){
                max = earliestSeen[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
