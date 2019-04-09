package br.com.db1.pedidos.pedidosapi.infraestrutura;

import java.util.Objects;

public class Checker {
		
	public static void notNull(Object value, String field) {
		if (value == null) {
			throw new RuntimeException("Campo " + field + " � obrigat�rio.");
		}
	}
	
	public static void notEmpty(Object value, String field) {
		notNull(value, field);
		if (field.isEmpty()) {
			throw new RuntimeException("Campo " + field + " n�o pode ser vazio.");
		}
	}
	
	public static void checkCpf(String cpf) {
		if (Objects.isNull(cpf) || cpf.isEmpty() || cpf.length() != 11) {
			throw new RuntimeException("CPF " + cpf + " inv�lido.");
		}
	}
	
	public static void biggerThanZero(Double valor, String field) {
		if (valor == null || valor <= 0) {
			throw new RuntimeException("Campo " + field + " deve ser maior que zero.");
		}
	}
}
