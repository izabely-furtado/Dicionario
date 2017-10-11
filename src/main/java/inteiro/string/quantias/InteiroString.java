/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteiro.string.quantias;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import taddic.TADDicChain;
import taddic.TADDicEA;

/**
 *
 * @author 20121bsi0040
 */
public class InteiroString {

    TADDicEA dicionario1 = new TADDicEA(new HashEngineIntStr());
    TADDicChain dicionario2 = new TADDicChain(new HashEngineIntStr());

    private static String inteiroExtenso99(int i) {
        String[] zeroA9 = {"zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove"};
        String[] dezA19 = {"dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"};
        String[] zeroA90 = {"", "dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"};
        int primeiraParte = i / 10;
        int segundaParte = i % 10;
        if (i >= 0 && i <= 9) {
            return zeroA9[i];
        } else if (i >= 10 && i <= 19) {
            return dezA19[segundaParte];
        } else if (primeiraParte > 0 && segundaParte == 0) {
            return zeroA90[primeiraParte];
        } else {
            return zeroA90[primeiraParte] + " e " + zeroA9[segundaParte];
        }
    }

    public static String inteiroPorExtenso(int i) {
        String[] zeroA900 = {"cem", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seicentos", "setecentos", "oitocentos", "novecentos"};

        if (i > 999 || i < 0) {
            return "Não é tratado número acima de 999 ou abaixo de 0";
        } 
        else if(i <= 99 && i >=0){
            return InteiroString.inteiroExtenso99(i);
        }
        else {
            int primeiraParte = i / 100;
            int segundaParte = i % 100;
            if (i == 100) {
                return zeroA900[0];
            } else {
                if (segundaParte == 0) {
                    return zeroA900[primeiraParte];
                } else {
                    return zeroA900[primeiraParte] + " e " + InteiroString.inteiroExtenso99(segundaParte);
                }
            }
        }
    }
    

    

    public static TADDicChain lendoArquivoMoeda() throws FileNotFoundException, IOException {
        TADDicChain dicionario = new TADDicChain(new HashEngineIntStr());
//        List<String[]> moedas = new ArrayList();
        FileInputStream stream = new FileInputStream("tabela_moedas.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        while(linha != null) {
            String[] moeda = linha.split(";");
            dicionario.insertItem(moeda[0], moeda[1]);
//            moedas.add(moeda);
//            String moeda = linha.substring(0, linha.indexOf(';'));
           //System.out.println(Arrays.toString(moeda));
           linha = br.readLine();
        }
        return dicionario;
    }
    
    public static String moedaPorExtenso(String moeda) throws IOException {
        TADDicChain dicionario = InteiroString.lendoArquivoMoeda();
        return (String) dicionario.findElements(moeda);
    }

    public void inserindoDicEA() {
        for (int i = 0; i <= 999; i++) {
            this.dicionario1.insertItem(i, InteiroString.inteiroPorExtenso(i));
        }
    }
    public void inserindoDicEA(int i) {
        this.dicionario1.insertItem(i, InteiroString.inteiroPorExtenso(i));
    }
    

    @Override
    public String toString() {
        String retorno = "";
        this.inserindoDicEA();
        for (int i = 0; i <= 999; i++) {
            retorno += this.dicionario1.findElements(i) + " \n";
        }
        return retorno;
    }

    public static void main(String[] args) throws IOException {
        InteiroString intStr = new InteiroString();
        for (int i = 0; i <= 999; i++) {
            intStr.inserindoDicEA(i);
        }
        System.out.println(InteiroString.moedaPorExtenso("A$"));
        System.out.println(intStr);
    }

}
