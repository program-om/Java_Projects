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

        int minIndex = 0;
        int min = frames[minIndex].orderOfLastAccess;

        for (int i=pages.size()-1; i >= startIndex ; i--){
            //is this page in memory
            int indexInMemory = mem.IsInMemory(pages.get(i));
            if(indexInMemory != -1){
                return indexInMemory;
            }
        }
        //not found in the future
        return 0; //will replace the page on top!
    }
}
