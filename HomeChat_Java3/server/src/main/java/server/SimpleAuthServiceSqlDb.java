package server;

public class SimpleAuthServiceSqlDb implements AuthService {

    private WorkWithSql ws = new WorkWithSql();

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return ws.returnNickname(login, password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return ws.addRegistration(login, password, nickname);
    }

}