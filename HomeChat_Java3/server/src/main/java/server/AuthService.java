package server;

public interface AuthService {
    /**
     * Метод проверки наличия учетки
     **/
    String getNicknameByLoginAndPassword(String login, String password);


    /**
     * Метод регистрации новой учетной записи
     **/
    boolean registration(String login, String password, String nickname);
}
