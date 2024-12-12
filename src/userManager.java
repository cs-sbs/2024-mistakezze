import java.util.HashMap;
import java.util.Map;

    class UserManager {
    private Map<String, User> users = new HashMap<>();

    public void registerUser(String username, String password, String role) {
        if (users.containsKey(username)) {
            System.out.println("用户名已存在.");
        } else {
            users.put(username, new User(username, password, role));
            System.out.println("用户注册成功.");
        }
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
