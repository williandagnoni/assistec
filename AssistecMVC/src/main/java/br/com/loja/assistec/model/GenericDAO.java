package br.com.loja.assistec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.loja.assistec.dal.ConexaoBD;

//Classe abstrata não pode ser instanciada, é um modelo para as classes que irão herdar seu comportamento
//Esta classe facilita para que não precisemos em todas as Classes DAO executar todo este código, 
//iremos apenas passar os parâmetros
public abstract class GenericDAO {
	private Connection conexao;

	// Protected pois pertencem a esta classe, somente podem ser usadas por classes
	// que herdam desta
	protected GenericDAO() {
		this.conexao = ConexaoBD.conectar();
	}

	// Método que retorna a conexaao
	protected Connection conectarDAO() {
		this.conexao = ConexaoBD.conectar();
		return conexao;
	}

	// Método para salvar
	protected void save(String insertSql, Object... parametros) throws SQLException {
		PreparedStatement pstmt = conectarDAO().prepareStatement(insertSql);

		for (int i = 0; i < parametros.length; i++) {
			pstmt.setObject(i + 1, parametros[i]);
		}

		pstmt.execute();
		pstmt.close();
		conexao.close();
	}

	// Método para atualizar
	protected void update(String updateSql, Object id, Object... parametros) throws SQLException {
		PreparedStatement pstmt = conectarDAO().prepareStatement(updateSql);
		for (int i = 0; i < parametros.length; i++) {
			pstmt.setObject(i + 1, parametros[i]);
		}
		pstmt.setObject(parametros.length + 1, id);
		pstmt.execute();
		pstmt.close();
		conexao.close();
	}

	// Método para deletar
	protected void delete(String deleteSql, Object... parametros) throws SQLException {
		PreparedStatement pstmt = conectarDAO().prepareStatement(deleteSql);

		for (int i = 0; i < parametros.length; i++) {
			pstmt.setObject(i + 1, parametros[i]);
		}

		pstmt.execute();
		pstmt.close();
		conexao.close();
	}
}
