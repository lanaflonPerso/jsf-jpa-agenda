package br.com.devmedia.agenda.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.devmedia.agenda.model.entidade.Grupo;

/**
 * <p>
 * <b>Title:</b> GrupoConverter.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> Converter JSF para Classe Grupo
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
@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && !value.isEmpty()) {

			return (Grupo) uic.getAttributes().get(value);
		}

		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {

		if (object instanceof Grupo) {

			Grupo entity = (Grupo) object;

			if (entity != null && entity instanceof Grupo && entity.getId() != null) {

				uic.getAttributes().put(entity.getId().toString(), entity);

				return entity.getId().toString();
			}

		}

		return "";
	}
}
