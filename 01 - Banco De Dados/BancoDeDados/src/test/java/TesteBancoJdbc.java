import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.Userposjava;
import org.junit.Test;

import java.util.List;

public class TesteBancoJdbc {

    @Test
    public void initBanco(){
        UserPosDAO userPosDAO = new UserPosDAO();
        Userposjava userposjava = new Userposjava();

        userposjava.setId(5L);
        userposjava.setNome("Silvio Santos");
        userposjava.setEmail("mahoi@gmail.com");

        userPosDAO.salvar(userposjava);
    }

    @Test
    public void initListar(){
        UserPosDAO userPosDAO = new UserPosDAO();
        try {
            List<Userposjava> list = userPosDAO.listar();
            for (Userposjava userposjava : list) {
                System.out.println(userposjava);
                System.out.println("---------------------------------");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void initBuscar(){
        UserPosDAO userPosDAO = new UserPosDAO();
        try {
            Userposjava userposjava = userPosDAO.buscar(5L);
            System.out.println(userposjava);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void initAtualizar(){
        try {
            UserPosDAO userPosDAO = new UserPosDAO();
            Userposjava objetoBanco = userPosDAO.buscar(5L);
            objetoBanco.setNome("Jhonie Bravo");
            userPosDAO.atualizar(objetoBanco);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
