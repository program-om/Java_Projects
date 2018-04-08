import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String fileName = args[0];
        int framesSize = Integer.parseInt(args[1]);

        ArrayList<Page> pagesList = new ArrayList<>();

        //go over the file
        File file = new File(args[0]);
        Scanner input = new Scanner(System.in);
        try {
            input = new Scanner(file);
        }catch (Exception e){
            e.getMessage();
        }


        String line;

        while(input.hasNextLine()){

            Page page = new Page();
            line = input.nextLine();
            String[] comps = line.split(" ");
            page.processNum = Integer.parseInt(comps[0]);
            page.pageNum = Integer.parseInt(comps[1]);
            pagesList.add(page);
            //System.out.println(line);
        }

        for (Page pg:
             pagesList) {
            System.out.println(pg.processNum + " " + pg.pageNum);
        }



//        FIFO fifo = new FIFO(pagesList);
//        LRU lru = new LRU(pagesList);
        OPT opt = new OPT(pagesList);

//        System.out.println("Page fault in FIFO strategy: " + fifo.pageFaults(framesSize));
//        System.out.println("Page fault in LRU strategy: " + lru.pageFaults(framesSize));
        System.out.println("Page fault in OPT strategy: " + opt.pageFaults(framesSize));

    }
}