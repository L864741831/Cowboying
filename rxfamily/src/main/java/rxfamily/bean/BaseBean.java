package rxfamily.bean;


import java.io.Serializable;

public class BaseBean implements Serializable{

    /**
     * status : 565404
     * message : cehgnofn
     */

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
