import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.Userposjava;
import org.junit.Test;

public class TesteBancoJdbc {

    @Test
    public void initBanco(){
        UserPosDAO userPosDAO = new UserPosDAO();
        Userposjava userposjava = new Userposjava();

        userposjava.setId(3L);
        userposjava.setNome("Lindon Jhonson");
        userposjava.setEmail("lindao@gmail.com");

        userPosDAO.salvar(userposjava);
    }
}
