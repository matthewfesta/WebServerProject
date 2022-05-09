import static org.junit.jupiter.api.Assertions.*;

class LogFileParserTest {

    @org.junit.jupiter.api.Test
    void findHttpResponseCountByCountry() {
        String[] sampleStringArray;
        sampleStringArray = new String[]{"[2021-11-16T21:36:39.958]|77.36.71.186|UK|404|Content-Type:text/plain|Content-Length:423|GET|robots.txt|elapsedTimeInMilliseconds:1444|OS:W|Cookie:sessionid=s317469;userid=mack747",
                "[2021-11-16T23:34:48.510]|159.42.31.168|CA|403|Content-Type:application/pdf|Content-Length:5117|GET|all_courses.pdf|elapsedTimeInMilliseconds:683|OS:i|Cookie:sessionid=s981075;userid=mack71"};
        String expectedUS = "HTTP 200 Responses for US: 0";
        String actualUS = LogFileParser.findHttpResponseCountByCountry(sampleStringArray, "US", 200);
        assertEquals(expectedUS, actualUS);

    }

    @org.junit.jupiter.api.Test
    void findHttpResponseCountByCountryAgain() {
        String[] sampleStringArray;
        sampleStringArray = new String[]{"[2021-11-16T01:22:05.525]|128.238.213.209|US|405|Content-Type:text/html|Content-Length:420|GET|/academics/index.html|elapsedTimeInMilliseconds:4809|OS:A|Cookie:sessionid=s102444;userid=mack38",
                "[2021-11-16T03:39:19.897]|128.238.213.209|US|200|Content-Type:text/css|Content-Length:4834|GET|merrimack.css|elapsedTimeInMilliseconds:3947|OS:i|Cookie:sessionid=s269495;userid=mack551"};
        String expectedUS = "HTTP 200 Responses for US: 1";
        String actualUS = LogFileParser.findHttpResponseCountByCountry(sampleStringArray, "US", 200);
        assertEquals(expectedUS, actualUS);

    }

    @org.junit.jupiter.api.Test
    void calculateOSCount() {
        String[] sampleStringArray;
        sampleStringArray = new String[]{"[2021-11-16T21:36:39.958]|77.36.71.186|UK|404|Content-Type:text/plain|Content-Length:423|GET|robots.txt|elapsedTimeInMilliseconds:1444|OS:W|Cookie:sessionid=s317469;userid=mack747",
                "[2021-11-16T23:34:48.510]|159.42.31.168|CA|403|Content-Type:application/pdf|Content-Length:5117|GET|all_courses.pdf|elapsedTimeInMilliseconds:683|OS:i|Cookie:sessionid=s981075;userid=mack71"};
        String expectedW = "Windows: 1";
        String actualW = LogFileParser.calculateOSCount(sampleStringArray, 'W');
        assertEquals(expectedW, actualW);


    }

    @org.junit.jupiter.api.Test
    void calculateOSCountAgain() {
        String[] sampleStringArray;
        sampleStringArray = new String[]{"[2021-11-16T01:22:05.525]|128.238.213.209|US|405|Content-Type:text/html|Content-Length:420|GET|/academics/index.html|elapsedTimeInMilliseconds:4809|OS:A|Cookie:sessionid=s102444;userid=mack38",
                "[2021-11-16T03:39:19.897]|128.238.213.209|US|200|Content-Type:text/css|Content-Length:4834|GET|merrimack.css|elapsedTimeInMilliseconds:3947|OS:i|Cookie:sessionid=s269495;userid=mack551"};
        String expectedW = "Windows: 0";
        String actualW = LogFileParser.calculateOSCount(sampleStringArray, 'W');
        assertEquals(expectedW, actualW);

    }

    @org.junit.jupiter.api.Test
    void findLongestElapsedTime() {
        String[] sampleStringArray;
        sampleStringArray = new String[]{"[2021-11-16T21:36:39.958]|77.36.71.186|UK|404|Content-Type:text/plain|Content-Length:423|GET|robots.txt|elapsedTimeInMilliseconds:1444|OS:W|Cookie:sessionid=s317469;userid=mack747",
                "[2021-11-16T23:34:48.510]|159.42.31.168|CA|403|Content-Type:application/pdf|Content-Length:5117|GET|all_courses.pdf|elapsedTimeInMilliseconds:683|OS:i|Cookie:sessionid=s981075;userid=mack71"};
        int longestExpected = 1444;
        int actualExpected = LogFileParser.findLongestElapsedTime(sampleStringArray);
        assertEquals(longestExpected, actualExpected);


    }

    @org.junit.jupiter.api.Test
    void findLongestElapsedTimeAgain() {
        String[] sampleStringArray;
        sampleStringArray = new String[]{"[2021-11-16T01:22:05.525]|128.238.213.209|US|405|Content-Type:text/html|Content-Length:420|GET|/academics/index.html|elapsedTimeInMilliseconds:4809|OS:A|Cookie:sessionid=s102444;userid=mack38",
                "[2021-11-16T03:39:19.897]|128.238.213.209|US|200|Content-Type:text/css|Content-Length:4834|GET|merrimack.css|elapsedTimeInMilliseconds:3947|OS:i|Cookie:sessionid=s269495;userid=mack551"};
        int longestExpected = 4809;
        int actualExpected = LogFileParser.findLongestElapsedTime(sampleStringArray);
        assertEquals(longestExpected, actualExpected);

    }

}