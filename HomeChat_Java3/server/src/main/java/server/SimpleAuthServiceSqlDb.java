package server;

import java.util.ArrayList;
import java.util.List;

public class SimpleAuthServiceSqlDb implements AuthService {
    private class UserData {
        String login;
        String password;
        String nickname;

        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }

    private List<UserData> user;
    private WorkWithSql ws = new WorkWithSql();

    public SimpleAuthServiceSqlDb() {
        this.user = new ArrayList<>();
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return ws.returnNickname(login, password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return ws.addRegistration(login, password, nickname);
    }

}