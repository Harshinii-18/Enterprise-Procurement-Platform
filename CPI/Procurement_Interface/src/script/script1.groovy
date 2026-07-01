import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {

    String method = message.getHeader("CamelHttpMethod", String)
    String query  = message.getHeader("CamelHttpQuery", String)

    String resource = ""
    String id = ""

    if(query != null){

        query.split("&").each { param ->

            def pair = param.split("=")

            if(pair.size() == 2){

                switch(pair[0]){

                    case "resource":
                        resource = pair[1]
                        break

                    case "id":
                        id = pair[1]
                        break
                }
            }
        }
    }

    message.setProperty("HTTP_METHOD", method)
    message.setProperty("RESOURCE", resource)
    message.setProperty("ID", id)

    return message
}