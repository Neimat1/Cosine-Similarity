/*
* this is a class SimilarityInOrder to reorder the Similarities
*/
class SimilarityInOrder implements Comparable<SimilarityInOrder> {
    public String docNames;
    public double cosineSimilarity;

    public int compareTo(SimilarityInOrder o1)
    {
        return docNames.compareTo(o1.docNames);
    }

    public SimilarityInOrder(String DocNames,double cosineSimilarity){
        super();
        this.docNames = DocNames;
        this.cosineSimilarity = cosineSimilarity;
    }

    @Override
    public String toString()
    {
        return
                docNames
                + " = " + cosineSimilarity ;
    }
    public String getDocNames()
    {
        return docNames;
    }

    public void setDocNames(String docNames)
    {
        this.docNames = docNames;
    }

    public double getCosineSimilarity()
    {
        return cosineSimilarity;
    }

    public void setCosineSimilarity(double cosineSimilarity)
    {
        this.cosineSimilarity = cosineSimilarity;
    }

}
