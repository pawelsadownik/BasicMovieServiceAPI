package domain;



import java.util.List;


public class Movie {


    private String name;
    private int id;

    private String comment;
    private int rate;

    private List <Actor> actors;
    private List <Comment> comments;
    private List <Rate> rates;

    public List<Rate> getRates() {

        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {


        //return rates.stream().mapToInt(a -> a).average().orElse(0);


        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }



    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }




}
