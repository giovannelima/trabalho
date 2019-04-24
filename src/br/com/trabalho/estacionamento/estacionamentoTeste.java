package br.com.trabalho.estacionamento;

import java.sql.SQLException;
import java.util.Scanner;

public class estacionamentoTeste {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		conexaoBD banco = new conexaoBD();
		Estacionamento parking = new Estacionamento();
		Scanner leitura = new Scanner(System.in);
		Scanner leituraString = new Scanner(System.in);
		int opc, opc1, opc2, up;
		String recebeBanco = null, mod = null, ma = null, co = null, pl = null;
		String like = null, like2 = null, like3 = null, like4 = null;

		do {

			System.out.println("\nMENU\n");
			System.out.println("1 - Cadastro de veiculos ");
			System.out.println("2 - Atualizar veiculos ");
			System.out.println("3 - Deletar veiculos ");
			System.out.println("4 - Visualizar veiculos ");
			System.out.println("5 - Sair ");
			System.out.println("\nDigite a opcao : ");

			opc = leitura.nextInt();

			try {
				switch (opc) {

				case 1:
					System.out.println("MENU - > inserir cadastro \n");
					System.out.println("Digite o modelo: ");
					parking.setModelo(leituraString.nextLine());
					System.out.println("Digite o marca: ");
					parking.setMarca(leituraString.nextLine());
					System.out.println("Digite o cor: ");
					parking.setCor(leituraString.nextLine());
					System.out.println("Digite o placa: ");
					parking.setPlaca(leituraString.nextLine());

					banco.insert(parking);

					// chaveGerada = banco.insert(u);
					// ystem.out.println("Codigo gerado para esse registro: " + chaveGerada);
					break;
				case 2:
					// System.out.println("Digite a id que deseja atualizar: ");
					// int up = leitura.nextInt();
					System.out.println("Digite a id que deseja atualizar: ");
					up = parking.setId(leitura.nextInt());
					boolean recebe = banco.verUpdate(up);
					if (recebe) {
						System.out.println("\nEste é o id que deseja editar? (s/n)");
						String ver = leituraString.nextLine();
						if (ver.equals("sim") || ver.equals("s") || ver.equals("S") || ver.equals("SIM")) {

							System.out.println("\n MENU -> Atualizar veiculos\n");
							System.out.println("1- Modelo: ");
							System.out.println("2- Marca: ");
							System.out.println("3- Cor: ");
							System.out.println("4- Placa: ");
							System.out.println("\nDigite a opção para atualizar:  ");
							opc1 = leitura.nextInt();

							if (opc1 == 1) {
								
								recebeBanco = "car_modelo";
								System.out.println("Digite o Modelo: \n");
								mod = parking.setModelo(leituraString.nextLine());
								banco.update(recebeBanco, mod, up);
								banco.verUpdate(up);

							} else if (opc1 == 2) {
								
								recebeBanco = "car_marca";
								System.out.println("Digite o Marca: ");
								ma = parking.setMarca(leituraString.nextLine());
								banco.update(recebeBanco, ma, up);
								banco.verUpdate(up);

							} else if (opc1 == 3) {
							
								recebeBanco = "car_cor";
								System.out.println("Digite a Cor: ");
								co = parking.setCor(leituraString.nextLine());
								banco.update(recebeBanco, co, up);
								banco.verUpdate(up);

							} else if (opc1 == 4) {

								recebeBanco = "car_placa";
								System.out.println("Digite Placa: ");
								pl = parking.setMarca(leituraString.nextLine());
								banco.update(recebeBanco, pl, up);
								banco.verUpdate(up);
							
							}

						} else {

							System.out.println("nenhuma ação necessária!");
						}

					} else {

						System.out.println("no action required!");
					}

					break;

				case 3:

					System.out.println("MENU -> Deletar veiculo\n");
					System.out.println("\nDigite o codigo do veiculo para deletar: ");
					int del = parking.setId(leitura.nextInt());
					banco.deletar(del);
					// chaveGerada = banco.delete(u);

					System.out.println("Codigo excuido desse registro: " + parking.getId());
					break;

				case 4:
					// criar para selest de um id e para todos

					System.out.println("\n Menu -> Visualizar veiculos \n");
					System.out.println("1- Visualizar por ID: ");
					System.out.println("2- Visualizar por Modelo: ");
					System.out.println("3- Visualizar por Marca: ");
					System.out.println("4- Visualizar por Cor: ");
					System.out.println("5- Visualizar por Placa: ");
					System.out.println("6- Visualizar TODOS: \n");
					System.out.println("Digite a opção desejada:");
					opc2 = leitura.nextInt();

					if (opc2 == 1) {

						System.out.println("\nDigite o codigo do veiculo: ");
						int veiculo = parking.setId(leitura.nextInt());
						banco.selectID(veiculo);

					} else if (opc2 == 2) {
						System.out.println("\nDigite o modelo veiculo: ");
						like = parking.setModelo(leituraString.next());
						banco.seleclLike(like, like2, like3, like4);
					} else if (opc2 == 3) {
						System.out.println("\nDigite a marca do veiculo: ");
						like2 = parking.setMarca(leituraString.next());
						banco.seleclLike(like, like2, like3, like4);
					}

					else if (opc2 == 4) {

						System.out.println("\nDigite a cor do veiculo: ");
						like3 = parking.setCor(leituraString.next());
						banco.seleclLike(like, like2, like3, like4);

					} else if (opc2 == 5) {

						System.out.println("\nDigite a placa do veiculo: ");
						like4 = parking.setPlaca(leituraString.next());
						banco.seleclLike(like, like2, like3, like4);

					} else if (opc2 == 6) {

						System.out.println("Visualização de todos os veiculos \n");
						banco.selectAll();
					}

					break;

				case 5:

					System.out.println("FIM DE EXECUÇÃO!");
					System.exit(0);
					break;

				}
			} catch (ClassNotFoundException e) {
				System.err.println("Driver do banco não foi encontrado");// TODO: handle exception
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println("Erro ao inserir o registro" + e.getMessage());
			}
		} while (opc != 5);
	}

}
