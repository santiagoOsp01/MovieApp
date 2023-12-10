package edu.eci.co.movieapp.network;

public class MovieDto {
    private String Title;
    private String Year;
    private String imdbIO;
    private String Type;
    private String Poster;

    public String getImdbIO() {return imdbIO;}
    public void setImdbIO(String imdbIO) {this.imdbIO = imdbIO;}
    public String getType() {return Type;}
    public void setType(String type) {Type = type;}
    public String getPoster() {return Poster;}
    public void setPoster(String poster) {Poster = poster;}
    public String getTitle() {return Title;}
    public void setTitle(String title) {Title = title;}
    public String getYear() {return Year;}
    public void setYear(String year) {Year = year;}

    @Override
    public String toString() {
        return "MovieDto{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", imdbIO='" + imdbIO + '\'' +
                ", Type='" + Type + '\'' +
                ", Poster='" + Poster + '\'' +
                '}';
    }
}
