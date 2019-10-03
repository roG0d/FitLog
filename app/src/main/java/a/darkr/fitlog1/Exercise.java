package a.darkr.fitlog1;
public class Exercise {


    private String name;
    private Integer Reps;
    private  Integer Series;
    private Double kg;

    public Exercise(String name, Integer reps, Integer series, Double kg) {

        this.name = name;
        Reps = reps;
        Series = series;
        this.kg = kg;
    }

    public Exercise() {

        this.name="";
        this.Reps=0;
        this.Series=0;
        this.kg=0.0;

    }

    public String getName() {
        return name;
    }



    public Integer getReps() {
        return Reps;
    }

    public Integer getSeries() {
        return Series;
    }

    public Double getKg() {
        return kg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReps(Integer reps) {
        Reps = reps;
    }

    public void setSeries(Integer series) {
        Series = series;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }

}
