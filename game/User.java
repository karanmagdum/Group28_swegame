package game;

public class User {

    private String username = "";
    //See if enum could be used
    private String type = "";

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

//    User(String name, String type){
//        this.username = name;
//        this.type = type;
//    }

    public String getUsername()
    {
        return username;
    }


    public void setUsername(String username) {
		this.username = username;
	}

}
