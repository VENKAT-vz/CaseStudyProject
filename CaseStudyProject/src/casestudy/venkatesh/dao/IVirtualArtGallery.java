package casestudy.venkatesh.dao;

import java.util.List;

import casestudy.venkatesh.dto.Artwork;
import casestudy.venkatesh.myexceptions.ArtWorkNotFoundException;
import casestudy.venkatesh.myexceptions.UserNotFoundException;


public interface IVirtualArtGallery {

		    boolean addArtwork(Artwork artwork);
		    
		    boolean updateArtwork(Artwork artwork);
		    
		    boolean removeArtwork(int artworkID);
		    
		    Artwork getArtworkById(int artworkID) throws ArtWorkNotFoundException;
		    
		    List<Artwork> searchArtworks(String keyword);
		    
		    boolean addArtworkToFavorite(int userId, int artworkId);
		    
		    boolean removeArtworkFromFavorite(int userId, int artworkId) throws ArtWorkNotFoundException;
		    
		    List<Artwork> getUserFavoriteArtworks(int userId) throws UserNotFoundException;
		}

