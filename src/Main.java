
import java.util.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("-------------------- ************************* --------------------");
        System.out.println("-------------------- ONLY ENTER FOUR DOCUMENTS --------------------");
        System.out.println("-------------------- ************************* --------------------");

        cosineSimilarity cosine = new cosineSimilarity();
        cosine.buildData(new String[]{
                "docs2\\100.txt",
                "docs2\\101.txt",
                "docs2\\102.txt",
                "docs2\\103.txt",
        });
        cosine.main();

    }
}
