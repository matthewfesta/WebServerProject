import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class takes a log text file from the command line argument and extracts
 * data for analysis.
 * @mfesta
 */


public class LogFileParser {


    static final boolean DEBUG = false; // make this false before submitting

    public static void main(String[] args) {
        String fileName = ""; // the fileName should come from the command line arguments

        // add command-line logic here
        if (args.length == 0) {
            System.out.println("File name not provided on command-line.");
            return;
        }
        fileName = new String(args[0]);
        String[] lines = readLinesFromFile(fileName);
        if (lines == null) {
            System.out.println("The file was not found.");
        }
        // print out each line of the file to confirm program reads file properly
        // you should make the DEBUG flag false before you submit
        if (DEBUG) {
            for (String line : lines) {
                System.out.println(line);
            }
        }
        // Output the HTTP codes by country.
        System.out.println();
        System.out.println("HTTP RESPONSE CODES BY COUNTRY: ");
        System.out.println(findHttpResponseCountByCountry(lines, "US", 200));
        System.out.println(findHttpResponseCountByCountry(lines, "IE", 404));
        System.out.println(findHttpResponseCountByCountry(lines, "FR", 500));

        // Output the request counts by operating system.
        System.out.println();
        System.out.println("REQUEST COUNTS BY OPERATING SYSTEM: ");
        System.out.println(calculateOSCount(lines, 'W'));
        System.out.println(calculateOSCount(lines, 'M'));
        System.out.println(calculateOSCount(lines, 'L'));
        System.out.println(calculateOSCount(lines, 'i'));
        System.out.println(calculateOSCount(lines, 'A'));
        System.out.println(calculateOSCount(lines, 'U'));

        // Output longest elapsed time, Date/Time, IP Address, URL, Content length(bytes), user.
        System.out.println();
        System.out.print("LONGEST ELAPSED TIME IN MILLISECONDS: ");
        System.out.print(findLongestElapsedTime(lines));
        System.out.println();
        int longestInt = findLongestElapsedTime(lines);
        String logArray = Arrays.toString(lines);
        String longestTime = Integer.toString(longestInt);
        findLongestElapsedTimeIndex(lines, longestTime);
    }

    /**
     * This method counts certain HTTP response codes by certain countries.
     *
     * @param arrayOfLines
     * @param countryCode
     * @param httpResponseCode
     * @return The formatted string with the correct number of code counts.
     */
    public static String findHttpResponseCountByCountry(String[] arrayOfLines, String countryCode,
                                                        int httpResponseCode) {
        int count = 0;
        String logArray = Arrays.toString(arrayOfLines);
        String countryRegex = "\\|([A-Z][A-Z])\\|([0-9]+)\\|";
        Pattern countryPattern = Pattern.compile(countryRegex);
        Matcher countryMatcher = countryPattern.matcher(logArray);
        while (countryMatcher.find()) {
            String countryMatch = countryMatcher.group(1);
            String httpMatchString = countryMatcher.group(2);
            int httpMatch = Integer.parseInt(httpMatchString);
            if (countryCode.equals(countryMatch) && httpResponseCode == httpMatch) {
                count++;
            }
        }
        return String.format("HTTP %03d Responses for %s: %d ", httpResponseCode, countryCode, count);
    }

    /**
     * This method calculates the number and type of operating systems in the
     * data.
     *
     * @param arrayOfLines
     * @param osChar
     * @return
     */
    public static String calculateOSCount(String[] arrayOfLines, char osChar) {
        int count = 0;
        String osString = "";
        String logArray = Arrays.toString(arrayOfLines);
        String osRegex = "\\|OS:([a-zA-Z])\\|";
        Pattern osPattern = Pattern.compile(osRegex);
        Matcher osMatcher = osPattern.matcher(logArray);
        while (osMatcher.find()) {
            String osMatchString = osMatcher.group(1);
            char osMatch = osMatchString.charAt(0);
            if (osChar == osMatch) {
                count++;
            }
            if (osChar == 'W') {
                osString = "Windows";
            }
            if (osChar == 'M') {
                osString = "Mac";
            }
            if (osChar == 'L') {
                osString = "Linux";
            }
            if (osChar == 'i') {
                osString = "iOS";
            }
            if (osChar == 'A') {
                osString = "Android";
            }
            if (osChar == 'U') {
                osString = "Unknown";
            }
        }
        return String.format("%s: %d", osString, count);
    }

    /**
     * This method searches the data and finds the integer that represents the
     * longest elapsed time in milliseconds.
     *
     * @param arrayOfLines
     * @return
     */
    // Create function called findLongestElapsedTimeIndex (copy below)
    // find index of the string in which the l
    // use substring method
    public static int findLongestElapsedTime(String[] arrayOfLines) {
        String logArray = Arrays.toString(arrayOfLines);
        String timeRegex = "\\|elapsedTimeInMilliseconds:([0-9]+)\\|";
        Pattern timePattern = Pattern.compile(timeRegex);
        int longestTime = 0;
        int time = 0;
        Matcher matchTime = timePattern.matcher(logArray);
        while (matchTime.find()) {
            time = Integer.parseInt(matchTime.group(1));
            if (time > longestTime) {
                longestTime = time;
                return longestTime;
            }
        }
        return longestTime;
    }

    /**
     * This method takes the following parameters and uses them to find the index of the string
     * with the longest elapsed time. It also prints out additional related information.
     * @param arrayOfLines
     * @param longestTime
     */
    public static void findLongestElapsedTimeIndex(String[] arrayOfLines, String longestTime) {
        int i;
        for (i = 0; 0 < arrayOfLines.length; i++) {
            if (arrayOfLines[i].contains(longestTime)) {
                String elapsedTimeString = arrayOfLines[i];
                // split strings here to get related information.
                String[] splitStringArray = elapsedTimeString.split("\\[|\\]|\\||\\;");
                String dateTime = splitStringArray[1];
                String ip = splitStringArray[3];
                String contentLength = splitStringArray[7];
                String byteNum = contentLength.substring(15);
                String url = splitStringArray[9];
                String userString = splitStringArray[13];
                String userName = userString.substring(7);

                // Output the related information
                System.out.println("Date/Time: " + dateTime);
                System.out.println("IP Address: " + ip);
                System.out.println("URL: " + url);
                System.out.println("Content Length in Bytes: " + byteNum);
                System.out.println("User: " + userName);
                break;
            }
            else {
                System.out.println("Item not found");
            }
        }
    }

    /**
     * Utility method that will read the file provided and parse into an array of Strings
     *
     * @param fileName String of the file name to parse
     * @return String[] - array of Strings - one String per line from the file.
     */
    public static String[] readLinesFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        // create a File object based on the fileName provided
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file); // use the file instead of the keyboard for input
            // go through each line of the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line); // add each line to a list
            }
        } catch (FileNotFoundException ex) {
            // something went wrong with the file.
            System.out.println(ex.getMessage());
            return new String[0];
        }
        // convert the list to an array of Strings and return it
        String[] arrayOfLines = lines.toArray(new String[0]);
        return arrayOfLines;
    }
}
