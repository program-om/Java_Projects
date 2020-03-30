### High level Discussion:

There are three main classes which named FIFO, LRU, and OPT, a class for each strategy. The Memory class represent the main memory which has a number of frames and each frame stores a page. The Page class is storing the data for each page. There are two integers in the Page class which are representing the page’s process number and the page’s number. The Frame class stores a page that is allocated in that frame and has the integer `orderOfLastAccess` which indicate when the last time this frame is used.

In all the three classes the function `pageFaults` will return the number of page faults have occurred during optimal algorithm. The function will go through all the pages and it will check if there is free frame, if it found a free frame it will allocate it to the current page. If there is no free frame, it will use the function `findMinOrder` or `lessLikelyInFuture` to find the page that will be replaced. In the case of OPT class, the function `lessLikelyInFuture` will return the index of the first page in the memory that would not be used in the future. If all the pages will be used in the future it will return the index of the page that will be used in the furthest future. 

​	In all the three classes, the arrayList *pages* contains all the pages that will be allocated in the memory. All the pages in the memory are stored in the array frames which is a member of `Memory` class. The frames array is a list of objects of `Page` class. The `Page` class has two data members which represent the page’s process number and the page’s number.





## Results

#### 10 frames:

num processes | algorithm | s=98,o=98 | s=98,o=95 | s=95,o=98 | s=95,o=95
------------|-----------|-----------|-----------|-----------|----------      
    1       | FIFO      |      46   |       56  |      81   |     133
            | LRU       |      46   |       54  |      73   |	 128
            | OPT       |      39   |       47  |      66   |	 118       
    10      | FIFO      |      175  |       202 |      321  |	 320
            | LRU       |      167  |       200 |      318  |	 313
            | OPT       |      115  |       134 |      233  |	 230     
    50      | FIFO      |      234  |       246 |      325  |	 362
            | LRU       |      234  |       246 |      324  |	 362
            | OPT       |      190  |       201 |      292  |	 316     
    100     | FIFO      |      235  |       250 |      331  |	 372
            | LRU       |      235  |       250 |      330  |	 370
            | OPT       |      206  |       221 |      294  |	 335     



#### 50 frames:

num processes | algorithm | s=98,o=98 | s=98,o=95 | s=95,o=98 | s=95,o=95
------------|-----------|-----------|-----------|-----------|----------      
    1       | FIFO      |      38   |      47   |      61   |	 111
            | LRU       |      38   |      47   |      61   |	 111
            | OPT       |      38   |      47   |      61   |	 111    
    10      | FIFO      |      64   |      97   |      165  |	 202
            | LRU       |      61   |      94   |      154  |	 199
            | OPT       |      59   |      87   |      108  |	 145     
    50      | FIFO      |      185  |      198  |      299  |	 320
            | LRU       |      181  |      195  |      295  |	 316
            | OPT       |      133  |      154  |      228  |	 254    
    100     | FIFO      |      206  |      235  |      302  |	 346
            | LRU       |      204  |      235  |      301  |	 345
            | OPT       |      186  |      200  |      258  |	 285     



#### 100 frames:

num processes | algorithm | s=98,o=98 | s=98,o=95 | s=95,o=98 | s=95,o=95
------------|-----------|-----------|-----------|-----------|----------      
    1       | FIFO      |      38   |      47   |      61   |	 111
            | LRU       |      38   |      47   |      61   |	 111
            | OPT       |      38   |      47   |      61   |	 111     
    10      | FIFO      |      59   |      87   |      110  |	 146
            | LRU       |      59   |      87   |      107  |	 146
            | OPT       |      59   |      87   |      107  |	 142     
    50      | FIFO      |      144  |      163  |      261  |	 289
            | LRU       |      139  |      163  |      254  |	 286
            | OPT       |      133  |      152  |      223  |	 254     
    100     | FIFO      |      193  |      215  |      292  |	 309
            | LRU       |      193  |      214  |      289  |	 309
            | OPT       |      186  |      200  |      258  |	 285      

