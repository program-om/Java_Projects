import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int framesSize = Integer.parseInt(args[1]);

        ArrayList<String> files = new ArrayList<>();
        files = getFiles(args[0]);
        //go over the file

        for (String thisFile:
             files) {

            ArrayList<Page> pagesList = new ArrayList<>();
            System.out.println(thisFile);

            File fileName = new File(thisFile);
            Scanner input = new Scanner(System.in);
            try {
                input = new Scanner(fileName);
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
            }

            FIFO fifo = new FIFO(pagesList);
            LRU lru = new LRU(pagesList);
            OPT opt = new OPT(pagesList);

            System.out.println("Page fault in FIFO strategy: " + fifo.pageFaults(framesSize));
            System.out.println("Page fault in LRU strategy: " + lru.pageFaults(framesSize));
            System.out.println("Page fault in OPT strategy: " + opt.pageFaults(framesSize));
            System.out.println();

        }

    }

    private static ArrayList<String> getFiles(String filesFile){

        ArrayList<String> files = new ArrayList<>();
        File file = new File(filesFile);
        Scanner input = new Scanner(System.in);
        try {
            input = new Scanner(file);
        }catch (Exception e){
            e.getMessage();
        }

        String line;

        while(input.hasNextLine()){


            line = input.nextLine();

            files.add(line);
        }

        return files;
    }
}