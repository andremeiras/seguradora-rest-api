package com.seguradora.utils;

import java.util.ArrayList;
import java.util.Random;


public class Utils {
	
	static int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	
	public static String randomNovo(ArrayList<String> setVerificar) {
		
		Random rnd = new Random(); // instancia o Random
		
		int number = rnd.nextInt(10); // Gera um Novo
		
		//for responsável por iterar o set, no lugar de used.size vc vai colocar o Set retornado do banco
		for (int i = 0; i < setVerificar.size(); i++) {
		    
			while (number == Integer.valueOf(setVerificar.get(i))) {
		        number = rnd.nextInt(400);				
			}
			
		}
		System.out.println(number);
		return String.valueOf(number); //retorna o numero gerado
	}
	
	private static int calcularDigito(String str, int[] peso) {
	      
		int soma = 0;
	    
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	   }
	
	public static boolean isValidCPF(String cpf) {
	      if ((cpf==null) || (cpf.length()!=11)) return false;

	      Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
	      Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
	      return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	   }

}
