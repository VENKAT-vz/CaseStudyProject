package casestudy.venkatesh.dto;

public class Artwork {

    private int artworkID;
    private String title;
    private String description;
    private String creationDate;
    private String medium;
    private String imageURL;
    
    public Artwork() {
    }
    

	public Artwork(int artworkID, String title, String description, String creationDate, String medium, String imageURL) {
        this.artworkID = artworkID;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.medium = medium;
        this.imageURL = imageURL;
    }
    
    public int getArtworkID() {
        return artworkID;
    }
    
    public void setArtworkID(int artworkID) {
        this.artworkID = artworkID;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
    public String getMedium() {
        return medium;
    }
    
    public void setMedium(String medium) {
        this.medium = medium;
    }
    
    public String getImageURL() {
        return imageURL;
    }
    
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    @Override
	public String toString() {
		return "Artwork [artworkID=" + artworkID + ", title=" + title + ", description=" + description
				+ ", creationDate=" + creationDate + ", medium=" + medium + ", imageURL=" + imageURL + "]";
	}

}



