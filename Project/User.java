package Project;

public class User {
	
	int id;
    String name;
    String password;
    String phoneNo;
    String address;
    String city;
    String state;
    String country;

    public User(int id, String name, String password, String phoneNo, String address, String city, String state, String country) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    
    public int getId() 
    { 
    	return id; 
    	
    }
    public String getName() 
    { 
    	return name; 
    	
    }
    public String getPassword() 
    { 
    	return password; 
    	
    }
}


