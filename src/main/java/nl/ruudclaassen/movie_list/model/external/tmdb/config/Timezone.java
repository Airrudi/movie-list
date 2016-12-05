package nl.ruudclaassen.movie_list.model.external.tmdb.config;



import nl.ruudclaassen.movie_list.model.external.tmdb.core.AbstractJsonMapping;

public class Timezone extends AbstractJsonMapping {

    private String name;
    private String country;


    public Timezone(String name, String country) {
        this.name = name;
        this.country = country;
    }



	public String getCountry() {
        return country;
    }
    

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }
}
