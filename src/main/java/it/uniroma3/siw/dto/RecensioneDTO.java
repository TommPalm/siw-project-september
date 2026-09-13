package it.uniroma3.siw.dto;

public class RecensioneDTO {

    private Long id;
    private String text;
    private Integer vote;
    private String date;
    private String filmTitle;

    public RecensioneDTO(
            Long id,
            String text,
            Integer vote,
            String date,
            String filmTitle) {

        this.id = id;
        this.text = text;
        this.vote = vote;
        this.date = date;
        this.filmTitle = filmTitle;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Integer getVote() {
        return vote;
    }

    public String getDate() {
        return date;
    }

    public String getFilmTitle() {
        return filmTitle;
    }
}
