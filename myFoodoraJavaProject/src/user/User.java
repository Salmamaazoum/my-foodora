package user;

public abstract class User {
	
	
	protected int id;
	protected String name;
	protected String username;
	protected String password;
	
    
    public User(String name, String username, String password) {
    	UserIdGenerator instance = UserIdGenerator.getInstance();
    	this.id = instance.getNextUserId();
        this.name = name;
        this.username = username;
        this.password = password;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}
	

}
