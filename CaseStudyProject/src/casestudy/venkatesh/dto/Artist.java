package casestudy.venkatesh.dto;


public class Artist {

	    private int artistID;
	    private String name;
	    private String biography;
	    private String birthDate;
	    private String nationality;
	    private String website;
	    private String contactInformation;
	    
	    public Artist() {
	    }
	    
	    public Artist(int artistID, String name, String biography, String birthDate, String nationality, String website, String contactInformation) {
	        this.artistID = artistID;
	        this.name = name;
	        this.biography = biography;
	        this.birthDate = birthDate;
	        this.nationality = nationality;
	        this.website = website;
	        this.contactInformation = contactInformation;
	    }
	    
	    public int getArtistID() {
	        return artistID;
	    }
	    
	    public void setArtistID(int artistID) {
	        this.artistID = artistID;
	    }
	    
	    public String getName() {
	        return name;
	    }
	    
	    public void setName(String name) {
	        this.name = name;
	    }
	    
	    public String getBiography() {
	        return biography;
	    }
	    
	    public void setBiography(String biography) {
	        this.biography = biography;
	    }
	    
	    public String getBirthDate() {
	        return birthDate;
	    }
	    
	    public void setBirthDate(String birthDate) {
	        this.birthDate = birthDate;
	    }
	    
	    public String getNationality() {
	        return nationality;
	    }
	    
	    public void setNationality(String nationality) {
	        this.nationality = nationality;
	    }
	    
	    public String getWebsite() {
	        return website;
	    }
	    
	    public void setWebsite(String website) {
	        this.website = website;
	    }
	    
	    public String getContactInformation() {
	        return contactInformation;
	    }
	    
	    public void setContactInformation(String contactInformation) {
	        this.contactInformation = contactInformation;
	    }

		@Override
		public String toString() {
			return "Artist [artistID=" + artistID + ", name=" + name + ", biography=" + biography + ", birthDate="
					+ birthDate + ", nationality=" + nationality + ", website=" + website + ", contactInformation="
					+ contactInformation + "]";
		}
	    
	}



