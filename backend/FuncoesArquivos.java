package backend;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class FuncoesArquivos {

    public static void criarArquivo(String nomeArquivo){
        try{
            File arquivo = new File(nomeArquivo);
            if (arquivo.createNewFile()){
                System.out.println("arquivo criado: " + arquivo.getName());
            }
            else{
                System.out.println("arquivo ja existe!");
            }
        }
        catch (IOException e){
            System.out.println("Erro: ");
            e.printStackTrace();
        }
    }

    public static void escreverArquivo(String nomeArquivo, String linha){
        try{
            FileWriter escritorArquivo = new FileWriter(nomeArquivo);
            escritorArquivo.write(linha);
            escritorArquivo.close();
        }
        catch (IOException e){
            System.out.println("Erro: ");
            e.printStackTrace();
        }
    }

    public static void lerArquivo(String nomeArquivo){
        try{
            File arquivo = new File(nomeArquivo);
            Scanner leitorArquivo = new Scanner(arquivo);
            while (leitorArquivo.hasNextLine()){
                String linha = leitorArquivo.nextLine();
                System.out.println(linha);
            }
            leitorArquivo.close();
        }
        catch (IOException e){
            System.out.println("Erro: ");
            e.printStackTrace();
        }
    }

    public static ArrayList<String> listaLinhas(File arquivo){
        ArrayList<String> listaLinhas = new ArrayList<String>();

        try{
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            String linha = br.readLine();

            while (linha != null){
                listaLinhas.add(linha);
                linha = br.readLine();
            }
            br.close();
            return listaLinhas;
        }
        catch (IOException e){
            System.out.println("erro");
            e.printStackTrace();
            return listaLinhas;
        }
        
        
    }

    public static String obterStringDeNullsCsv(int qntDeNulls){
        String[] arrayNulls = new String[qntDeNulls];

        for (int i = 0; i < qntDeNulls; i++){
            arrayNulls[i] = "null";
        }

        String stringDeNulls = String.join(",", arrayNulls);
        return stringDeNulls;
    }

    public void salvarObjetoParaArquivo(ArrayList<String> listaValoresAtributos, String nomeArquivo){
        
        /*
        função que recebe um arrayList de Strings, 
        que contem os valores dos atributos do objeto,
        transforma esse arraylist em uma string e
        salva essa string como uma linha no arquivo especificado
        */
        
        String linhaParaArquivo = String.join(",", listaValoresAtributos);

        try{
            FileWriter fw = new FileWriter (nomeArquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(linhaParaArquivo);
            bw.newLine();
            bw.close();
        }
        catch(Exception e){
            System.out.println("erro, nao foi possivel realizar a operação");
            e.printStackTrace();
        }
    }

    public static void alterarInfoArquivo(String nomeArquivo, String infoReferencia, int posColunaInfo, String novaInfo){
        File arquivoAntigo = new File(nomeArquivo);
        File temp = new File("temp.txt");

        try{
            FileReader fr = new FileReader(arquivoAntigo);
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter(temp);
            BufferedWriter bw = new BufferedWriter(fw);

            String linha = br.readLine();

            while (linha != null){
                String[] dadosLinha = linha.split(",");
                String nome = dadosLinha[0];
    
                if (nome.equals(infoReferencia)){
                    dadosLinha[posColunaInfo] = novaInfo;
                    linha = String.join(",", dadosLinha);
                }
                bw.write(linha);
                bw.newLine();
                linha = br.readLine();
            }
            br.close();
            bw.close();

            arquivoAntigo.delete();
            File destino = new File(nomeArquivo);
            temp.renameTo(destino);

        }
        catch (Exception e){
            System.out.println("erro, não foi possivel modificar o arquivo");
            e.printStackTrace();
        }
    }
}