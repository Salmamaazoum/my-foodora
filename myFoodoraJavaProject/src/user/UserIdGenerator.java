package user;

public class UserIdGenerator {
    /**
     * Singleton instance of the UserIdGenerator
      */
    private static UserIdGenerator instance = null;

    private int num = 1;

    private UserIdGenerator(){}

    public static UserIdGenerator getInstance() {
        if (instance==null) {
            instance = new UserIdGenerator();
        }
        return instance;
    }

    public int getNextUserId(){
        return num++;
    }
}
