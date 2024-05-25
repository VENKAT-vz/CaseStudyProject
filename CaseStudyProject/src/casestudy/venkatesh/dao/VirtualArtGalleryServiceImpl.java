package casestudy.venkatesh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import casestudy.venkatesh.dto.Artwork;
import casestudy.venkatesh.myexceptions.ArtWorkNotFoundException;
import casestudy.venkatesh.myexceptions.UserNotFoundException;



public class VirtualArtGalleryServiceImpl implements IVirtualArtGallery{

    private Connection connection;

    public VirtualArtGalleryServiceImpl(Connection connection) {
        this.connection = connection;
	}

	public boolean addArtwork(Artwork artwork) {
        String query = "INSERT INTO Artwork (ArtworkID, Title, Description, CreationDate, Medium, ImageURL) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, artwork.getArtworkID());
            statement.setString(2, artwork.getTitle());
            statement.setString(3, artwork.getDescription());
            statement.setString(4, artwork.getCreationDate());
            statement.setString(5, artwork.getMedium());
            statement.setString(6, artwork.getImageURL());
            
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateArtwork(Artwork artwork) {
        String query = "UPDATE Artwork SET Title = ?, Description = ?, CreationDate = ?, Medium = ?, ImageURL = ? WHERE ArtworkID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, artwork.getTitle());
            statement.setString(2, artwork.getDescription());
            statement.setString(3, artwork.getCreationDate());
            statement.setString(4, artwork.getMedium());
            statement.setString(5, artwork.getImageURL());
            statement.setInt(6, artwork.getArtworkID());
            
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeArtwork(int artworkID) {
        String query = "DELETE FROM Artwork WHERE ArtworkID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, artworkID);
            
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Artwork getArtworkById(int artworkID) throws ArtWorkNotFoundException {
        String query = "SELECT * FROM Artwork WHERE ArtworkID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, artworkID);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(resultSet.getInt("ArtworkID"));
                    artwork.setTitle(resultSet.getString("Title"));
                    artwork.setDescription(resultSet.getString("Description"));
                    artwork.setCreationDate(resultSet.getString("CreationDate"));
                    artwork.setMedium(resultSet.getString("Medium"));
                    artwork.setImageURL(resultSet.getString("ImageURL"));
                    return artwork;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ArtWorkNotFoundException("Artwork with ID " + artworkID + " not found.");
    }



    public List<Artwork> searchArtworks(String keyword) {
        List<Artwork> artworks = new ArrayList<>();
        String query = "SELECT * FROM Artwork WHERE Title LIKE ? OR Description LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(resultSet.getInt("ArtworkID"));
                    artwork.setTitle(resultSet.getString("Title"));
                    artwork.setDescription(resultSet.getString("Description"));
                    artwork.setCreationDate(resultSet.getString("CreationDate"));
                    artwork.setMedium(resultSet.getString("Medium"));
                    artwork.setImageURL(resultSet.getString("ImageURL"));
                    artworks.add(artwork);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artworks;
    }

    public boolean addArtworkToFavorite(int userId, int artworkId) {
        if (isArtworkInFavorites(userId, artworkId)) {
            System.out.println("Artwork is already in favorites for this user.");
            return false; 
        }

        String query = "INSERT INTO user_favorite_artwork (userid, artworkid) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, artworkId);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isArtworkInFavorites(int userId, int artworkId) {
        String query = "SELECT COUNT(*) FROM user_favorite_artwork WHERE userid = ? AND artworkid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, artworkId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }


    public boolean removeArtworkFromFavorite(int userId, int artworkId) throws ArtWorkNotFoundException {
        String query = "DELETE FROM user_favorite_artwork WHERE userid = ? AND artworkid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, artworkId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            } else {
                throw new ArtWorkNotFoundException("Artwork with ID " + artworkId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Artwork> getUserFavoriteArtworks(int userId) throws UserNotFoundException {
        List<Artwork> favoriteArtworks = new ArrayList<>();
        String query = "SELECT Artwork.* FROM Artwork JOIN user_favorite_artwork ON Artwork.ArtworkID = user_favorite_artwork.artworkid WHERE user_favorite_artwork.userid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(resultSet.getInt("ArtworkID"));
                    artwork.setTitle(resultSet.getString("Title"));
                    artwork.setDescription(resultSet.getString("Description"));
                    artwork.setCreationDate(resultSet.getString("CreationDate"));
                    artwork.setMedium(resultSet.getString("Medium"));
                    artwork.setImageURL(resultSet.getString("ImageURL"));
                    favoriteArtworks.add(artwork);
                }
                if (favoriteArtworks.isEmpty()) {
                    throw new UserNotFoundException("User with ID " + userId + " has no favorite artworks.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteArtworks;
    }
    
    public List<Artwork> listArtworks() {
        List<Artwork> artworks = new ArrayList<>();
        String query = "SELECT * FROM Artwork";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(resultSet.getInt("ArtworkID"));
                    artwork.setTitle(resultSet.getString("Title"));
                    artwork.setDescription(resultSet.getString("Description"));
                    artwork.setCreationDate(resultSet.getString("CreationDate"));
                    artwork.setMedium(resultSet.getString("Medium"));
                    artwork.setImageURL(resultSet.getString("ImageURL"));
                    artworks.add(artwork);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artworks;
    }

}


