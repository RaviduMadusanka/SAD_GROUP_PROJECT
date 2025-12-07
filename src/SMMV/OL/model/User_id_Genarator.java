package SMMV.OL.model;

import java.security.SecureRandom;

public class User_id_Genarator {
    
    private static final String CHARACTERS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + 
        "abcdefghijklmnopqrstuvwxyz" + 
        "0123456789";

    // Specify the desired length for the unique ID
    private static final int ID_LENGTH = 10;

    // Use SecureRandom for strong, cryptographically secure random number generation
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Generates a unique ID string of the specified length (10 characters).
     * * @return A 10-character unique ID.
     */
    public static String generateUniqueId() {
        // Use StringBuilder for efficient string concatenation
        StringBuilder sb = new StringBuilder(ID_LENGTH);

        // Loop to append the required number of random characters
        for (int i = 0; i < ID_LENGTH; i++) {
            // Get a random index within the CHARACTERS string length
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            
            // Append the character at the random index to the result
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        return sb.toString();
    }

    // --- Main method for testing ---
//    public static void main(String[] args) {
//        System.out.println("Generated Unique ID 1: " + generateUniqueId());
//        System.out.println("Generated Unique ID 2: " + generateUniqueId());
//        System.out.println("Generated Unique ID 3: " + generateUniqueId());
//    }
    
    public static String User_id_Genarator(){
        return generateUniqueId();
    }
    
}
