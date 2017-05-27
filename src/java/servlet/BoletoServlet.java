/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import boletos.BoletoBradesco;
import entity.Sell;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.SellSession;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "BoletoServlet", urlPatterns = {"/BoletoServlet"})
public class BoletoServlet extends HttpServlet {

    @Inject
    private SellSession sellSession;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer idOfSell = Integer.valueOf(request.getParameter("id"));
        Sell sell = sellSession.getSell(idOfSell);
        if (sell != null) {
            response.setContentType("application/pdf");            
            response.setHeader("Content-Disposition", "attachment; filename=boleto.pdf");
            /*
             JBoletoBean jBoletoBean = new JBoletoBean();
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
             jBoletoBean.setDataDocumento(simpleDateFormat.format(sell.getDateOfSell()));
             jBoletoBean.setDataProcessamento(simpleDateFormat.format(Calendar.getInstance().getTime()));
             jBoletoBean.setCedente("Ecommerce Devmedia");
             jBoletoBean.setCarteira("17");
            
             jBoletoBean.setNomeSacado(sell.getUserOf().getName());
             jBoletoBean.setEnderecoSacado(sell.getAddressToSend().getAddress());
             jBoletoBean.setBairroSacado("Verificar");
             jBoletoBean.setCidadeSacado(sell.getAddressToSend().getStateOfAddress());
             jBoletoBean.setUfSacado(sell.getAddressToSend().getStateOfAddress());
             jBoletoBean.setCepSacado(sell.getAddressToSend().getPostalCode());
             jBoletoBean.setCpfSacado("xxxxxxxxx-xx");
            
             jBoletoBean.setLocalPagamento("Até o vencimento preferencialmente no Banco Itaú.");
             jBoletoBean.setLocalPagamento2("Após o vencimento somente no Banco Itaú.");
            
             Vector descricoes = new Vector();
            
             for (SellItem item : sellSession.getSellItens(idOfSell)) {
             descricoes.add(item.getProduct().getName() + " - R$ " + item.getProduct().getCost().floatValue());
             }
             descricoes.add("Itens do pedido nro: "+sell.getId());
             jBoletoBean.setDescricoes(descricoes);
            
             jBoletoBean.setDataVencimento(simpleDateFormat.format(Calendar.getInstance().getTime()));
             jBoletoBean.setDataDocumento(simpleDateFormat.format(Calendar.getInstance().getTime()));
             jBoletoBean.setInstrucao1("Após vencimento cobrar multa de 2%");
             jBoletoBean.setInstrucao2("Após o vencimento cobrar R$ 0.50 por dia de atraso");
             jBoletoBean.setInstrucao3("");
             jBoletoBean.setInstrucao4("");

             jBoletoBean.setAgencia("341");
             jBoletoBean.setContaCorrente("090878");
             jBoletoBean.setNumConvenio("xxxxxx");
             jBoletoBean.setNossoNumero("xxxxxxxx", 10);
             jBoletoBean.setValorBoleto(sell.getTotal().toPlainString());
             jBoletoBean.setMoeda("R$");

             JBoleto jBoleto = new JBoleto();
             jBoleto.addBoleto(jBoletoBean, JBoleto.ITAU);
             ByteArrayOutputStream byteArrayOutputStream = jBoleto.writeToByteArray();
             response.getOutputStream().write(byteArrayOutputStream.toByteArray());
             jBoleto.writeToFile("banco_itau.pdf");*/
            BoletoBradesco boletoBradesco = new BoletoBradesco();
            byte[] boleto = boletoBradesco.geraBoletoBradesco();
            response.getOutputStream().write(boleto);
            
            response.flushBuffer();
            response.getOutputStream().flush();
            response.getOutputStream().close();
            
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
