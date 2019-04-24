package br.com.trabalho.estacionamento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class autenticacao {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	//String ver = "nothing";
	
	conexaoBD banco = new conexaoBD();
	Estacionamento park = new Estacionamento();
	Scanner leitura = new Scanner(System.in);
	Scanner leituraString = new Scanner(System.in);
	
	
	System.out.println("Digite a id que deseja atualizar: ");
	park.setId(leitura.nextInt());
	boolean recebe = banco.verUpdate(park);
	if(recebe) {
		System.out.println("Este é o id que deseja editar?");
		String ver = leituraString.nextLine();
			if(ver.equals("sim") || ver.equals("s")) {
		
				System.out.println("Digite o modelo: ");
				park.setModelo(leituraString.nextLine());
				System.out.println("Digite o marca: ");
				park.setMarca(leituraString.nextLine());
				System.out.println("Digite o cor: ");
				park.setCor(leituraString.nextLine());
				System.out.println("Digite o placa: ");
				park.setPlaca(leituraString.nextLine());
				banco.update(park);
		}
		else {
		System.out.println("nenhuma ação necessária!");
		}
	
		}else {
		System.out.println("no action required!");
		}
	
		}
	
	}

