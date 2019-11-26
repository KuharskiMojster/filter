package si.kuharskimojster.api.model;

public class ResponseModel {

    private Object body;
    private int statusCode;

    public ResponseModel(Object body, int statusCode) {
        this.body = body;
        this.statusCode = statusCode;
    }

    public ResponseModel(String body) {
        this.body = body;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
