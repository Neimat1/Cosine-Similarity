import java.io.*;
import java.util.*;
public class cosineSimilarity {
    public int TheMainNumberOfDocs  =4;
    private  ArrayList<ArrayList<String>> allFiles = new ArrayList<ArrayList<String>>();
    private  ArrayList<String> allWords = new ArrayList<String>();

    public void buildData(String[] files) {
        for (String fileName : files) {
            ArrayList<String> fileOfStrings = new ArrayList<String>();
            try ( BufferedReader file = new BufferedReader(new FileReader(fileName))) {
                String ln;
                while ((ln = file.readLine()) != null) {
                    String[] words = ln.split("\\W+");
                    for (String word : words) {
                        word = word.toLowerCase();
                        // check to see if the word is not in the data
                        if (!this.allWords.contains(word)) {
                            this.allWords.add(word);
                        }
                        fileOfStrings.add(word);
                    }
                }
                this.allFiles.add(fileOfStrings);

            } catch (IOException e) {
                System.out.println("File " + fileName + " not found. Skip it");
            }
        }
    }
    public HashMap<Integer,ArrayList<Integer>> buildEachDocWithFrequenciesOfWords(){
        HashMap<Integer,ArrayList<Integer>> eachDocWithFrequenciesOfWords = new HashMap<>();
        for (int i=0;i<this.allFiles.size();i++){
            ArrayList<Integer> occurrWords = new ArrayList<Integer>();
            for (int k=0;k<this.allWords.size();k++){
                int occurrences = Collections.frequency(this.allFiles.get(i), this.allWords.get(k));
                occurrWords.add(occurrences);
            }
            eachDocWithFrequenciesOfWords.put(i,occurrWords);
        }

        return eachDocWithFrequenciesOfWords;
    }
    public double calculateSimilarity(ArrayList<Integer>doc1,ArrayList<Integer>doc2){
        int dotProduct = 0;
        ///calculate first equation dot product
        for(int i=0;i<doc1.size();i++)
            dotProduct+=(doc1.get(i)*doc2.get(i));


        int sumDoc1=0,sumDoc2=0;
        ///calculate second equation
        for(int i=0;i<doc1.size();i++) {
            sumDoc1 += (doc1.get(i) * doc1.get(i));
            sumDoc2 += (doc2.get(i) * doc2.get(i));
        }

        ////calculate main equation for Similarity
        double Similarity = dotProduct/(Math.sqrt(sumDoc1)*Math.sqrt(sumDoc2));
        return Similarity;

    }

    public void printFilesContent(){
        System.out.println("\n\n-------------------- Documents content --------------------\n");
        for(int i =0;i<this.allFiles.size();i++){
            System.out.print( (i) + ": ");
            for (int j=0;j<this.allFiles.get(i).size();j++){
                System.out.print(this.allFiles.get(i).get(j) + ' ');
            }
            System.out.println();
        }
    }

    public void printAllWords(){
        System.out.println("\n\n-------------------- All words in documents --------------------\n");
        System.out.print("All words are = [ " );
        for(int i =0;i<this.allWords.size();i++){
            System.out.print(this.allWords.get(i)+" ");
        }
        System.out.print("] \n");

    }

    public void printEachDocWithFrequenciesOfWords(HashMap<Integer, ArrayList<Integer>> eachDocWithFrequenciesOfWords ){
        System.out.println("\n\n-------------------- Each Document with the list of word frequencies --------------------\n");
        for (Map.Entry<Integer, ArrayList<Integer>> set :
                eachDocWithFrequenciesOfWords.entrySet()) {
            // Printing all elements of a Map
            System.out.print(set.getKey() + " = [ " );
            for (int i = 0 ;i<set.getValue().size();i++){
                System.out.print(set.getValue().get(i)+" ");
            }
            System.out.print( "  ] \n" );

        }
    }

    public void main(){
        printFilesContent();
        printAllWords();
        /*--------------------------------------------------------------------------------------------------------------*/
        HashMap<Integer, ArrayList<Integer>> eachDocWithFrequenciesOfWords = buildEachDocWithFrequenciesOfWords();
        printEachDocWithFrequenciesOfWords(eachDocWithFrequenciesOfWords);
        /*--------------------------------------------------------------------------------------------------------------*/

        List<SimilarityInOrder> list = new ArrayList<>();
        for (int i =0;i<TheMainNumberOfDocs;i++){
            for (int j = i+1;j<TheMainNumberOfDocs;j++){
                double similarityResult=calculateSimilarity(eachDocWithFrequenciesOfWords.get(i),eachDocWithFrequenciesOfWords.get(j));
                String docName = "Document "+i+ " && "+j;
                list.add(new SimilarityInOrder(docName , similarityResult));
            }
        }
        Collections.sort(list, Comparator.comparingDouble(SimilarityInOrder::getCosineSimilarity));
        Collections.reverse(list);
        System.out.println("\n\n-------------------- Ordered Similarities --------------------\n");
        list.forEach(order -> System.out.println(order));
    }






}
