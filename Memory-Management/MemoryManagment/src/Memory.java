public class Memory {
    public Frame[] frames;

    public Memory(int framesSize) {
        this.frames = new Frame[framesSize];

        for (int i=0; i < this.frames.length; i++) {
            this.frames[i] = new Frame();
        }
    }

    //this function will return the index of this page's position
    // will return -1 if not in memory
    int IsInMemory(Page page){
        int foundIndex = -1;
            for(int i=0; i < frames.length; i++){
                if(frames[i].page.processNum == page.processNum &&
                        frames[i].page.pageNum == page.pageNum){
                    return i;
                }
        }
        return foundIndex;
    }
}
