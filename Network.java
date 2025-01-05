/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }

    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {
        for(int i=0;i<users.length;i++){
            if(users[i] != null){
                String userName = users[i].getName();
                if(userName.equals(name)){
                    return users[i];
                }
            }
        }
        return null;
    }

    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
        if(users[users.length -1] != null){
            System.out.println("This network list is already full");
            return false;
        }
        if(getUser(name) == null){
            int n = 0;
            while(n < users.length){
                if(users[n] != null){
                    n++;
                }
                else{
                    break;
                }
            }
            User newUser = new User(name);
            users[n] = newUser;
            return true;
        }
        return false;
    }

    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {
        int index = 10;
        if(getUser(name1) == null || getUser(name2) == null){
            return false;
        }
        for(int i=0;i<users.length;i++){
            if(users[i] != null){
                String userName = users[i].getName();
                if(userName.equals(name1)){
                    index = i;
                }
            }
        }
        if(index < 10){
            users[index].addFollowee(name2);
            return true;
        }
        else{
            return false;
        }
    }
    
    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
        User mostRecommendedUserToFollow = null;
        int maxFollows = 0;
        User userName = getUser(name);
        for(int i=0;i<users.length;i++){
            if(users[i] != null){
                if(users[i].getName().equals(name)){
                    continue;
                }
                else{
                    int count = 0;
                    for(int j=0;j<users[i].getfFollows().length;j++){
                        String tempName = users[i].getfFollows()[j];
                        if(userName.follows(tempName)){
                            count++;
                        }
                    }
                    if(count > maxFollows){
                        maxFollows = count;
                        mostRecommendedUserToFollow = users[i];
                    }
                }                
            }
        }
        return mostRecommendedUserToFollow.getName();
    }

    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
        String mostPopularUser = null;
        int maxFollowers = 0;
        for(int i=0;i<users.length;i++){
            User candidate = users[i];
            if(candidate == null){
                break;
            }
            String candidateName = candidate.getName();
            int followerCount = 0;    
            for (int j = 0; j < users.length; j++) {
                if(users[j] == null){
                    break;
                }
                String[] followList = users[j].getfFollows();
                if (followList != null) {
                    for (int k = 0; k < followList.length; k++) {
                        if (followList[k] == null) {
                            break;
                        }
                        if (candidateName.equals(followList[k])) {
                            followerCount++;
                            break;
                        }
                    }
                }
            }
            if (followerCount > maxFollowers) {
                maxFollowers = followerCount;
                mostPopularUser = candidateName;
            }
        }
        return mostPopularUser;
    }

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        int count = 0;
        for(int i=0;i<users.length;i++){
            if(users[i] != null){
                if(users[i].follows(name)){
                    count++;
                }
            }
        }
        return count;
    }

    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
        String ans = "";
        for(int i=0;i<users.length;i++){
            if(users[i] != null){
                ans = ans + users[i] + " ";
            }
            
        }
        return ans;
    }
}

