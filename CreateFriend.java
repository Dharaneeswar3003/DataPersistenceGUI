import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CreateFriend {

    public static ArrayList<Friends> createAllFriends(String fileName) throws IOException {
        ArrayList<Friends> friends = new ArrayList<>(); // Create a new list for each file
        
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            parseFriend(line, friends); // Pass the list to parseFriend method
        }

        br.close();
        fr.close();

        return friends;
    }

    private static void parseFriend(String string, ArrayList<Friends> friends) {
        String[] parts = string.split(";"); // Split the string by semicolon

        for (String part : parts) {
            String[] friendDetails = part.split(","); // Split each part by comma

            if (friendDetails.length != 4) {
                System.err.println("Invalid format: " + part);
                continue; // Skip this friend and proceed to the next one
            }

            String fname = friendDetails[0].trim(); // First part is the first name
            String lname = friendDetails[1].trim(); // Second part is the last name
            int age = Integer.parseInt(friendDetails[2].trim()); // Third part is the age
            String pnum = friendDetails[3].trim(); // Fourth part is the phone number

            // Create a new Friends object and add it to the friends list
            friends.add(new Friends(fname, lname, age, pnum));
        }
    }
}
