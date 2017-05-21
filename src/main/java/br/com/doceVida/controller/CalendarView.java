package br.com.doceVida.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
public class CalendarView {
	private String strDataAtual;
	private Date dataDaEntrega;
	private Date dataAtual = new Date();
	public CalendarView(){
		this.strDataAtual = dataSistema();
		
	}
	public void ajustaData(){
		this.dataDaEntrega = this.dataAtual;
	}
	//Método que captura a dataAtual do sistema     
	public String dataSistema(){
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		
		return dataFormatada.format(dataAtual);
	}
	public String getStrDataAtual() {
		return strDataAtual;
	}
	public void setStrDataAtual(String strDataAtual) {
		this.strDataAtual = strDataAtual;
	}
	public Date getDataDaEntrega() {
		return dataDaEntrega;
	}
	public void setDataDaEntrega(Date dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}
	public Date getDataAtual() {
		return dataAtual;
	}
	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}
	
	 
	   


	

}
