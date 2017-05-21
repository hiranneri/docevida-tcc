package br.com.doceVida.dao;

import java.util.List;


public interface DAOGenerico <T> {
	public String inserir(T tipo);
	public int editar(T tipo);
	public int excluir(int tipo);
	public T findById(String id);
	public List<T> listar(String param, String valor);
}
