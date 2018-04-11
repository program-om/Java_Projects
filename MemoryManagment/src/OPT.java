import java.util.ArrayList;

public class OPT {
    private ArrayList<Page> pages;
    public OPT(ArrayList<Page> pagesList) {
        pages = pagesList;
    }


    public int pageFaults(int frameSize){
        int pFaults = 0;

        Memory memory = new Memory(frameSize);

        int pageOrder = 1;
        int j =0 ;
        Page thisPage;
        for (int i=0; i < pages.size(); i++){

            thisPage = pages.get(i);
            int indexInMemory = memory.IsInMemory(thisPage);
            int memorySize = memory.frames.length;

            if(indexInMemory == -1){ //Not in the memory
                if (j < memory.frames.length){//there is free frame
                    memory.frames[j].page = thisPage;
                    j++;
                    pFaults++;
                } else { //there is no free frame - replacement
                    int farAway = lessLikelyInFutre(memory.frames, memory, i);
                    memory.frames[farAway].page = thisPage;
                    pFaults++;
                }
            }

            System.out.println("-----------");
            for (Frame f:
                    memory.frames) {
                System.out.println(f.page.processNum + " " + f.page.pageNum);
            }

        }

        return pFaults;
    }

    private int lessLikelyInFutre(Frame[] frames, Memory mem, int startIndex){

        int min, minIndex=0;
        int futurePages = frames.length - startIndex - 1;//number of pages in the future that
                                                        //that we need to search in
        int[] lastSeen = new int[frames.length];
        for (int each:
             lastSeen) {
            each = -1;
        }

        //for(int j=0; j < frames.length; j++) {
        //here each page in the frame will know where is the last time it will be seen
        for (int i = pages.size() - 1; i >= startIndex; i--) {
            //is this page in memory
            int indexInMemory = mem.IsInMemory(pages.get(i));
            if (indexInMemory != -1) {
                lastSeen[indexInMemory] = i;
                break;
            }
        }
        //}

        min = lastSeen[minIndex];
        for (int i=0; i < lastSeen.length; i++) {
            if (lastSeen[i] < min){
                min = lastSeen[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
