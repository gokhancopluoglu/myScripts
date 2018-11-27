import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.*
import java.io.BufferedReader
import groovy.json.JsonBuilder
import java.io.InputStream
import javax.json.*
import org.apache.log4j.Logger

def log = Logger.getLogger("com.onresolve.jira.groovy")

URL jiraREST_URL = new URL("http://localhost:8080/jira/rest/api/2/issue/");
String jiraUserName = "gokhan"
String password = "gkhn0011"
String authenticationInfo = jiraUserName+":"+password

URLConnection urlConnection = jiraREST_URL.openConnection();
urlConnection.setDoInput(true);
HttpURLConnection conn = (HttpURLConnection) jiraREST_URL.openConnection();
conn.setDoOutput(true);
conn.setDoInput(true);

String input = generateJsonBuilder("DEVO","Sum","Task")

String encodedData = URLEncoder.encode(input,"UTF-8");
conn.setRequestMethod("POST");
conn.setRequestProperty("Authorization", "Basic " + Base64.encode(authenticationInfo.getBytes(), 0));
conn.setRequestProperty("Content-Type", "application/json");
conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
OutputStream os = conn.getOutputStream();
BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
writer.write(input)
writer.flush();


conn.getResponseMessage()

String generateJsonBuilder(String projectKey,String summaryName,String issueTypeName){
    def builder = new JsonBuilder()
	
    def root = builder.fields{
        project{       
            key projectKey
        }
        summary summaryName
        issuetype {
            name issueTypeName
        }
	}

	return builder.toString()
}
