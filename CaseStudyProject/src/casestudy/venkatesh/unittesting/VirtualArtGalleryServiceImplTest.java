package casestudy.venkatesh.unittesting;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import casestudy.venkatesh.dao.VirtualArtGalleryServiceImpl;
import casestudy.venkatesh.dbconnection.ConnectionFactory;
import casestudy.venkatesh.dto.Artwork;
import casestudy.venkatesh.myexceptions.ArtWorkNotFoundException;
import casestudy.venkatesh.myexceptions.UserNotFoundException;

public class VirtualArtGalleryServiceImplTest {

	 private static VirtualArtGalleryServiceImpl service;
	 private static Connection myconnection;

   @BeforeClass
   public static void setupClass() {
        myconnection = ConnectionFactory.getConnection();
        service = new VirtualArtGalleryServiceImpl(myconnection);
	    }
	
	@Test
	public void testAddArtwork() {
		Artwork artwork = new Artwork(2, "Juego Bonito", "A portrait of Neymar Jr", "2019-04-05", "Oil on poplar panel", "imageurl.com/monalisa");
        assertTrue(service.addArtwork(artwork));
        }

	@Test
	public void testRemoveArtwork() {
        assertTrue(service.removeArtwork(5));
	}
	
    @Test
    public void testSearchArtworks() {
        List<Artwork> artworks = service.searchArtworks("Klopp");
        assertNotNull(artworks);
    }
    
    @Test
    public void testgetArtworkbyId() {
    	Artwork artwork;
		try {
			artwork = service.getArtworkById(1);
	        assertNotNull(artwork);
		} catch (ArtWorkNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void testAddArtworkToFavorite() {
        assertTrue(service.addArtworkToFavorite(1, 3)); 
    }
    
    @Test
    public void testRemoveArtworkFromFavorite() {
            try {
				assertTrue(service.removeArtworkFromFavorite(1, 4));
			} catch (ArtWorkNotFoundException e) {
				e.printStackTrace();
			}
    }
    
    @Test
    public void testListArtworks() {
        List<Artwork> artworks = service.listArtworks();
        assertNotNull(artworks);
    }
	
    @Test
    public void testGetUserFavoriteArtworks() {
            List<Artwork> favoriteArtworks;
			try {
				favoriteArtworks = service.getUserFavoriteArtworks(1);
	            assertNotNull(favoriteArtworks);
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			} 
       
    }
	
    @Test
    public void testUpdateArtwork() {
        Artwork artwork = new Artwork(4, "Arne Slot", "Reminiscing his time at Feyenoord", "2024-05-18", "Oil on canvas", "https://url.com//ijkWSDFC1234&*ijnb");
        assertTrue(service.updateArtwork(artwork));
    }
    
    @AfterClass
    public static void teardown() {
    	try {
			myconnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
 

}
