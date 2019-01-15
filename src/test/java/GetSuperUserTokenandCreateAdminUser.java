import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;


public class GetSuperUserTokenandCreateAdminUser extends Setup {

    private String getAndCompareTimeStamps(List<String> tokenLines) {
        Pattern pattern = Pattern.compile(Resources.returnRegExPattern("timestamp"), Pattern.DOTALL);
        List <LocalDateTime> TimeStamps = new ArrayList<>();
        System.out.println("Getting timestamps");
        for (int i =0;i<tokenLines.size();i++) {
            Matcher matcher = pattern.matcher(tokenLines.get(i));
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .parseStrict()
                    .appendPattern("uuuu-MM-DD HH:mm:ss")
                    .toFormatter()
                    .withResolverStyle(ResolverStyle.STRICT);
            while (matcher.find()) {
                TimeStamps.add(LocalDateTime.parse(matcher.group(0), formatter));
            }
        }
        LocalDateTime LatestTimeStamp = TimeStamps.get(0);
        int LatestTimeStampIndex;
        System.out.println("Getting latest line");
        for (int i=1; i<TimeStamps.size(); i++) {
            if(TimeStamps.get(i).isAfter(LatestTimeStamp)) {
                LatestTimeStamp = TimeStamps.get(i);
            }
        }
        LatestTimeStampIndex = TimeStamps.indexOf(LatestTimeStamp);
        System.out.println(LatestTimeStampIndex);
        System.out.println(LatestTimeStamp);
        return tokenLines.get(LatestTimeStampIndex);

    }

    @BeforeSuite
    @BeforeTest
    public void setup(){
        Setup.getEnvironment();
    }


    @Test
    public void getSuperUserTokenandCreateAdminUser() {
        List<String> tokenLines = new ArrayList<>();
        Scanner scanner = null;
        String keyLine = null;
        String SuperUserToken = null;
        try {
            scanner = new Scanner(new File("/opt/teamcity/logs/teamcity-server.log"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("Super user authentication token:")) {
                System.out.println(line);
                tokenLines.add(line);
            }
        }
        if (tokenLines.size() > 1) {
                keyLine = getAndCompareTimeStamps(tokenLines);
        }
        else {
            keyLine = tokenLines.get(0);
        }
        System.out.println(keyLine);
        Pattern pattern = Pattern.compile(Resources.returnRegExPattern("token"), Pattern.DOTALL);
        Matcher matcher = pattern.matcher(keyLine);
        while (matcher.find()) {
            SuperUserToken = matcher.group(0);

        }
        System.out.println("GetSuperUserTokenandCreateAdminUser is " + SuperUserToken);
        System.out.println("createAdminNewUser is running");
        RestAssured.baseURI = prop.getProperty("HOST");
        System.out.println(RestAssured.baseURI);
        Response res = given().auth().basic("",SuperUserToken).
                body(Resources.postAdminUserBody()).contentType(ContentType.JSON).header("Origin",prop.getProperty("HOST")).
                when().
                post(Resources.postNewUser()).
                then().
                extract().response();
        System.out.println(res.asString());
        }

}