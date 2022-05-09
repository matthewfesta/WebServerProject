# Project Documentation

Author: Matthew Festa


# Problem-Solving Lifecycle:

# 1. Describe the problem:
This project simulates a program for the system administrator of a College website
to extract some information from the webserver log files. 

The system administrator wants to know:
    1. some of the HTTP responses for certain countries,
    2. the number of requests by the operating system,
    3. and the request with the longest elapsed time.

The output should look like this:

HTTP RESPONSE CODES BY COUNTRY:
HTTP 200 Responses for US:92
HTTP 404 Responses for IE:4
HTTP 500 Responses for FR:6

REQUEST COUNTS BY OPERATING SYSTEM:
Windows:290
Mac:206
Linux:109
iOS:297
Android:98
Unknown:0

LONGEST ELAPSED TIME IN MILLISECONDS:4989
Date/Time:2021-11-15T09:29:43.745
IP Address:150.187.34.37
URL:logo.jpg
Content Length in Bytes:450
User:mack982




# 2. Analyze the problem:

a. What are the inputs?
    i. The input is the the name of the log file to be parsed that is accepted
as a command line argument.



b. What are the outputs that should made by the solution to the problem?
    i. The output is the String array that displays the outputs regarding the
HTTP response codes by country, the request counts by operating system, and the
longest elapsed time in miliseconds.


c. How are the inputs and outputs related?
    i. The log file arguments convert into a string array and are provided to
the main() function as a string array argument displayed as the desired output.

d. Are there any constraints on the input?
    i. The input must be a String that is the name of the file to be parsed. If
the file name is not provided, the program will output  "File name not provided
on command-line" and will exit the program.

# 3. Design the algorithm to solve the problem
    1. Prompt user for command line argument input for the file name.
        i. If no argument is provided, the program will print out "File name not
        provided on command-line" and exit the program.
        ii. If it is provided, continue:
    2. Declare a new String object variable from args[0 called fileNme.
    3. Parse the inputted file name into an array of strings via the readLinesByFile
    method.
        i. If there are no lines, print out "The file was not found".
        ii. In the method, create a file object based on the file name provided.
        iii. Create a try / catch.
        iv. If the file is not found, catch the FileNotFoundException and print the error message.
        v. If the file is found, continue:
        vi. Create a new scanner and use the file instead of input.
        vii. Go through one line of the fila and if there is a next line, add that.
        viii. Return a string array called arrayOfLines.
    3. Call the findHttpResponseCountByCountry method with the parameters lines, countryCode,
    and the HTTP code.
        i. In the method, initialize count to zero.
        ii. Declare a log array string for the whole array.
        iii. Create a regex pattern for the country code and http code.
        iv. Create a Pattern variable and Matcher variable.
        v. While the Matcher finds the country code and http code that is passed as the
        arguments, the counter will increment by one until the pattern cannot be found anymore.
        vi. If the pattern is no longer found, return the country code, http response and count
        as a stirng format.
        vii. Output the string to main for the user.
    4. Call the calculateOSCount method with the parameters lines, and osChar.
        i. In the method, initialize count to zero.
        ii. Declare a log array string for the whole array.
        iii. Create a regex pattern for the OS character.
        iv. Create a Pattern variable and Matcher variable.
        v. While the Matcher finds the os character that is passed as the
        arguments, the counter will increment by one until the pattern cannot be found anymore.
        vi. If the pattern is no longer found, return the os character and count
        as a stirng format.
        vii. Output the string to main for the user.
    5. Call the findLongestElapsedTime method with the parameter lines.
        i. In the method, initialize longest time and time to zero.
        ii. Declare a log array string for the whole array.
        iii. Create a regex pattern for the string with the elapsed time.
        iv. Create a Pattern variable and Matcher variable.
        v. While the Matcher finds the patterns of elapsed time, it will parse
        the tume as an integer.
        vi. If time is greater than the previous longest time, time will be assigned
        to longest time.
        vii. If there are no more times that are greater than the previous longest time,
        return that time.
        vii. Output the string to main for the user.
    6. Call the findLongestElapsedTimeIndex method with the parameter lines and the string longest time.
        ii. In the method, initialize i.
        iii. In a for loop, i will be equal to zero, it will end at the end of the length
        of the array, and i will increment by one.
            a. If the current iterated index string contains the longest time, capture that string
            as elapsedTimeString.
            b. Split the string by its indices to return the date/time, IP address,
            URL, content length in bytesm and user name.
        iv. Print the related information out to the user.



# 4. Test the correctness of algorithm using a trace:

| Source            | Parameter 1  | Parameter 2    | Expected Output    | Actual Output    |
|-----              |--            |-----           |-----               |-----|
|  Sample String 1  | US           |  200           |  0                 |   0              |
|  Sample String 2  | US           |  200           |  1                 |   1              |
|  Sample String 1  | W            |  N/A           |  1                 |   1              |
|  Sample String 2  | W            |  N/A           |  0                 |   0              |
|  Sample String 1  | String Array |  N/A           |   1444             |  1444            |
|  Sample String 2  | String Array |  N/A           |   4809             | 4809             |

