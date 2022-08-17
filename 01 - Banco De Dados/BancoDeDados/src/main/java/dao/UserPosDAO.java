package dao;

import conexaojdbc.SingleConnection;
import model.Userposjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPosDAO {

    private Connection connection;

    public UserPosDAO() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(Userposjava userposjava){
        try {
            String sql = "insert into userposjava (nome, email) values (?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, userposjava.getNome());
            insert.setString(2, userposjava.getEmail());
            insert.execute();
            connection.commit();//salva no banco
        }catch (Exception e){
            try {
                connection.rollback();//se der erro reverte operação no BD
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Userposjava> listar() throws SQLException {
        List<Userposjava> list = new ArrayList<Userposjava>();

        String sql = "select * from userposjava";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()){
            Userposjava userposjava = new Userposjava();
            userposjava.setId(resultado.getLong("id"));
            userposjava.setNome(resultado.getString("nome"));
            userposjava.setEmail(resultado.getString("email"));
            list.add(userposjava);
        }
        return list;
    }

    public Userposjava buscar(Long id) throws SQLException {
        Userposjava retorno = new Userposjava();

        String sql = "select * from userposjava where id = "+ id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()){ //retorna apenas 1 ou nenhum
            Userposjava userposjava = new Userposjava();
            retorno.setId(resultado.getLong("id"));
            retorno.setNome(resultado.getString("nome"));
            retorno.setEmail(resultado.getString("email"));
        }
        return retorno;
    }

    public void atualizar(Userposjava userposjava) throws SQLException {
        try {
            String sql = "update userposjava set nome = ? where id = "+ userposjava.getId();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userposjava.getNome());
            statement.execute();
            connection.commit();
        }catch (Exception e){
            connection.rollback();
            e.printStackTrace();
        }
    }

    public void deletar(Long id) throws SQLException {
        try {
            String sql = "delete from userposjava where id ="+ id;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.commit();
        }catch (Exception e){
            connection.rollback();
            e.printStackTrace();
        }
    }
}
