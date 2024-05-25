package casestudy.venkatesh.mainmethod;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import casestudy.venkatesh.dao.VirtualArtGalleryServiceImpl;
import casestudy.venkatesh.dbconnection.ConnectionFactory;
import casestudy.venkatesh.dto.Artwork;
import casestudy.venkatesh.myexceptions.ArtWorkNotFoundException;
import casestudy.venkatesh.myexceptions.UserNotFoundException;

public class Mainmod {

	public static void main(String args[]) throws ArtWorkNotFoundException, UserNotFoundException {
		Connection myconnection=ConnectionFactory.getConnection();
		VirtualArtGalleryServiceImpl service=new VirtualArtGalleryServiceImpl(myconnection);
		
		List<Artwork> artwork1=new ArrayList<>();

		Scanner input = new Scanner(System.in);
        boolean entry=true;

        while (entry) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Add Artwork");
            System.out.println("2. Update Artwork");
            System.out.println("3. Remove Artwork");
            System.out.println("4. Get Artwork by ID");
            System.out.println("5. Search Artworks");
            System.out.println("6. Add Artwork to Favorite");
            System.out.println("7. Remove Artwork from Favorite");
            System.out.println("8. Get User Favorite Artworks");
            System.out.println("9. List Artwork");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");

            int choice = input.nextInt();

            switch (choice) {

                case 1:
                	System.out.println("Enter Artwork ID:");
                	 int artworkID1=input.nextInt();
                	 input.nextLine();
	                System.out.println("Enter title:");
            	     String title1=input.nextLine();
	                System.out.println("Enter description:");
            	     String description1=input.nextLine();
	                System.out.println("Enter new creation date (yyyy-MM-dd):");
            	     String creationDate1=input.nextLine();
		            System.out.println("Enter medium:");
            	     String medium1=input.nextLine();
		            System.out.println("Enter image url:");
            	     String imageURL1=input.nextLine();
         	        Artwork artwork = new Artwork(artworkID1,title1,description1,creationDate1,medium1,imageURL1);
            	     boolean addedToArtwork=service.addArtwork(artwork);
                     System.out.println("Artwork added");
                     if (addedToArtwork) {
	                        System.out.println("Artwork added.");
	                    } else {
	                        System.out.println("Failed to add artwork.");
	                    }
                    break;
                    
                case 2:
                    System.out.println("Enter Artwork ID:");
                    int artworkIdToUpdate = input.nextInt();
                    input.nextLine(); 
                    
                    System.out.println("Enter new title:");
                    String newTitle = input.nextLine();
                    
                    System.out.println("Enter new description:");
                    String newDescription = input.nextLine();
                    
                    System.out.println("Enter new creation date (yyyy-MM-dd):");
                    String newCreationDate = input.nextLine();

                    
                    System.out.println("Enter new medium:");
                    String newMedium = input.nextLine();
                    
                    System.out.println("Enter new image URL:");
                    String newImageURL = input.nextLine();
                    
                    Artwork updatedArtwork = new Artwork(artworkIdToUpdate, newTitle, newDescription, newCreationDate, newMedium, newImageURL);
                    boolean artworkUpdated = service.updateArtwork(updatedArtwork);
                    if (artworkUpdated) {
                        System.out.println("Artwork updated successfully.");
                    } else {
                        System.out.println("Failed to update artwork. ");
                    }
                    break;
                    
                case 3:
                    System.out.println("Enter Artwork ID:");
                    int artworkIdToRemove1 = input.nextInt();
                    input.nextLine(); 
                    
                    boolean artworkRemoved = service.removeArtwork(artworkIdToRemove1);
                    if (artworkRemoved) {
                        System.out.println("Artwork removed successfully.");
                    } else {
                        System.out.println("Failed to remove artwork.");
                    }
                    break;
                    
                case 4:
                	 System.out.println("Enter Artwork ID:");
	                    int artworkIdToget = input.nextInt();
	                    input.nextLine(); 
						Artwork artworkIdTogot = null;
						artworkIdTogot = service.getArtworkById(artworkIdToget);

	                    if (artworkIdTogot!=null) {
	                            System.out.println(artworkIdTogot);
	                        }
	                     else {
	                        System.out.println("No artworks found matching: " + artworkIdToget);
	                    }
	                    break;
                    
                case 5:
                    System.out.println("Enter keyword to search:");
                    input.nextLine();
                    String keyword = input.nextLine();
                    
                    List<Artwork> searchResults = service.searchArtworks(keyword);
                    if (!searchResults.isEmpty()) {
                        System.out.println("Search results:");
                        for (Artwork result : searchResults) {
                            System.out.println(result);
                        }
                    } else {
                        System.out.println("No artworks found matching the keyword: " + keyword);
                    }
                    break;
                    
                case 6:
                	System.out.println("Enter User ID:");
                    int userId = input.nextInt();
                    input.nextLine(); 
                    
                    System.out.println("Enter Artwork ID:");
                    int artworkId = input.nextInt();
                    input.nextLine(); 
                    
                    boolean addedToFavorites = service.addArtworkToFavorite(userId, artworkId);
                    if (addedToFavorites) {
                        System.out.println("Artwork added to favorites.");
                    } else {
                        System.out.println("Failed to add artwork to favorites. Please check the input.");
                    }
                    break;
                    
                case 7:
                    System.out.println("Enter User ID:");
                    int userIdToRemove = input.nextInt();
                    input.nextLine(); 
                    
                    System.out.println("Enter Artwork ID:");
                    int artworkIdToRemove = input.nextInt();
                    input.nextLine(); 
                    
                    boolean removedFromFavorites = false;
					removedFromFavorites = service.removeArtworkFromFavorite(userIdToRemove, artworkIdToRemove);
					
                    if (removedFromFavorites) {
                        System.out.println("Artwork removed from favorites.");
                    } else {
                        System.out.println("Failed to remove artwork from favorites. Please check the input.");
                    }
                    break;
                    
                case 8:
                    System.out.println("Enter User ID:");
                    int userIdToGetFavorites = input.nextInt();
                    input.nextLine(); 
                    
					List<Artwork> userFavorites = null;

					userFavorites = service.getUserFavoriteArtworks(userIdToGetFavorites);

                    if (!userFavorites.isEmpty()) {
                        System.out.println("Favorite artworks for user " + userIdToGetFavorites + ":");
                        for (Artwork favorite : userFavorites) {
                            System.out.println(favorite);
                        }
                    } else {
                        System.out.println("No favorite artworks found for user " + userIdToGetFavorites);
                    }
                    break;
                case 9:
                	artwork1=service.listArtworks();
                	for(Artwork artworkk:artwork1) {
                		System.out.println(artworkk);
                	}
                	break;
                case 0:
                	entry=false;
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 9.");
            }
            System.out.println("-----------------------------------------------------------------");
        }
        input.close();
    }
}
