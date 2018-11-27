import com.atlassian.jira.component.ComponentAccessor
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.apache.log4j.Logger

Logger log = Logger.getLogger("com.acme.Task.Log_Sonucu:")

def remote = new HTTPBuilder("http://localhost:8081")
def issueJson = remote.request(Method.GET) { req ->
    uri.path = "/rest/api/latest/issue/AZY-251"
    headers.'Authorization' =
        "Basic ${"admin:123456".bytes.encodeBase64().toString()}"

    response.success = { resp, json ->
        json ?: [:]
        log.error(json)
        log.error("json classı: " + json.getClass())
        log.error("Proje Keyi: "  + json.get("key"))
        log.error("Toplam izin: " + json.get("fields").get("customfield_11000"))
        
    }
}
