package br.com.devmedia.agenda.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * <p>
 * <b>Title:</b> MensagemUtil.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> Classe utilitária para alertas do JSF
 * </p>
 * 
 * <p>
 * <b>Company: </b> DEVMEDIA
 * </p>
 * 
 * @author DevMedia - http://www.devmedia.com.br/
 * 
 * @version 1.0.0
 */
public class MensagemUtil {

	public static void show(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem));
	}

	public static void showAtencao(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
	}

	public static void showErro(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", mensagem));
	}

	public static void showInfo(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}

	public static void showFatal(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", mensagem));
	}
}
