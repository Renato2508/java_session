package sessionHandler;

import java.util.HashMap;

public class Session{

    String id;
    HashMap <String,Object> data = new HashMap<String,Object>();
    SessionHandler sh;

    public Session(String id, HashMap<String, Object> data, SessionHandler sh) {
        this.id = id;
        this.data = data;
        this.sh = sh;
    }

    public Session(){

    }

    public Session(String id,SessionHandler sh){
        this.id=id;
        this.sh=sh;
    }

    public void setAttribute(String str, Object o){
        this.data.put(str,o);
        this.sh.write(this);
    }

    public Object getAttribute(String st){
        return this.data.get(st);
    }

    public void removeAttribute(String stri){
        this.data.remove(stri);
    }

    public void invalidate(){
        
        this.sh.destroy(this);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public SessionHandler getSh() {
        return sh;
    }

    public void setSh(SessionHandler sh) {
        this.sh = sh;
    }


}