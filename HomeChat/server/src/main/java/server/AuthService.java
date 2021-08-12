package server;

public interface AuthService {
    /**
     * Метод проверки наличия учетки
     **/
    String getNicknameByLoginAndPassword(String login, String password);
}
