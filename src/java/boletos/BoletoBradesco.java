/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boletos;

import br.com.nordestefomento.jrimum.bopepo.BancoSuportado;
import br.com.nordestefomento.jrimum.bopepo.Boleto;
import br.com.nordestefomento.jrimum.bopepo.view.BoletoViewer;
import br.com.nordestefomento.jrimum.domkee.comum.pessoa.endereco.Endereco;
import br.com.nordestefomento.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Agencia;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Carteira;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Cedente;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Sacado;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Titulo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class BoletoBradesco {

    public byte[] geraBoletoBradesco() {
        
        /**
         * Dados do cedente
         */
        Cedente cedente = new Cedente("Ecommerce Devmedia", "00.000.208/0001-00");

        /**
         * * Dados do sacado
         */
        Sacado sacado = new Sacado("Fabrício Mendes Ribeiro", "070.088.626-52");

        /**
         * Endereço do sacado
         */
        Endereco endereco = new Endereco();
        endereco.setUF(UnidadeFederativa.SP);
        endereco.setLocalidade("São Paulo");
        endereco.setCep("08032-500");
        endereco.setComplemento("bl C ap 21");
        endereco.setLogradouro("Rua Cotinga");
        endereco.setNumero("236");
        endereco.setPais("Brasil");
        endereco.setBairro("Vila Nova Curuçá");
        ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);
        sacado.setEnderecos(enderecos);
        
        /**
         * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
         */
        SacadorAvalista sacadorAvalista = new SacadorAvalista("Lojas Merifa", "00.000.000/0001-91");

        // Informando o endereço do sacador avalista.
        Endereco enderecoSacAval = new Endereco();
        enderecoSacAval.setBairro("Consolação");
        enderecoSacAval.setCep("08032-500");
        enderecoSacAval.setComplemento("Bl D Cj 72");
        enderecoSacAval.setLocalidade("São Paulo");
        enderecoSacAval.setLogradouro("Avenida Paulista");
        enderecoSacAval.setNumero("2208");
        enderecoSacAval.setPais("Brasil");
        enderecoSacAval.setUF(UnidadeFederativa.SP);

        /**
         * INFORMANDO OS DADOS SOBRE O TÍTULO.
         */
        // Informando dados sobre a conta bancária do título.
        ContaBancaria contaBancaria = new ContaBancaria(BancoSuportado.BANCO_BRADESCO.create());
        contaBancaria.setAgencia(new Agencia(8766, "1"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setNumeroDaConta(new NumeroDaConta(8755, "3"));

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento("123456");
        titulo.setNossoNumero("99345678912");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(BigDecimal.valueOf(0.23));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(new Date());
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setDesconto(new BigDecimal(0.05));

        /**
         * INFORMANDO OS DADOS SOBRE O BOLETO.
         */
        Boleto boleto = new Boleto(titulo);

        boleto.setLocalPagamento("Pagável preferencialmente na Rede X ou em "
                + "qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor "
                + "cobrado não é o esperado, aproveite o DESCONTÃO!");
        boleto.setInstrucao1("PARA PAGAMENTO 1 até Hoje não cobrar nada!");
        boleto.setInstrucao2("PARA PAGAMENTO 2 até Amanhã Não cobre!");
        boleto.setInstrucao3("PARA PAGAMENTO 3 até Depois de amanhã, OK, não cobre.");
        boleto.setInstrucao4("PARA PAGAMENTO 4 até 04/xx/xxxx de 4 dias atrás COBRAR O VALOR DE: R$ 01,00");
        boleto.setInstrucao5("PARA PAGAMENTO 5 até 05/xx/xxxx COBRAR O VALOR DE: R$ 02,00");
        boleto.setInstrucao6("PARA PAGAMENTO 6 até 06/xx/xxxx COBRAR O VALOR DE: R$ 03,00");
        boleto.setInstrucao7("PARA PAGAMENTO 7 até xx/xx/xxxx COBRAR O VALOR QUE VOCÊ QUISER!");
        boleto.setInstrucao8("APÓS o Vencimento, Pagável Somente na Rede X.");
        boleto.setLocalPagamento("Todos os bancos");
        /*
         * GERANDO O BOLETO BANCÁRIO.
         */
        // Instanciando um objeto "BoletoViewer", classe responsável pela
        // geração do boleto bancário.
        BoletoViewer boletoViewer = new BoletoViewer(boleto);

        // Gerando o arquivo. No caso o arquivo mencionado será salvo na mesma
        // pasta do projeto. Outros exemplos:
        // WINDOWS: boletoViewer.getAsPDF("C:/Temp/MeuBoleto.pdf");
        // LINUX: boletoViewer.getAsPDF("/home/temp/MeuBoleto.pdf");
        
        //File arquivoPdf = boletoViewer.getPdfAsFile("MeuPrimeiroBoleto.pdf");       
        byte[] arquivo = boletoViewer.getPdfAsByteArray();
        return arquivo;
    }
}
