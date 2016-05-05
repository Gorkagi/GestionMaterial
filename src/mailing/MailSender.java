package mailing;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sendpulse.restapi.Sendpulse;


public class MailSender {

	private Sendpulse sendpulse;
	private Map<String, Object> from;
	@SuppressWarnings("rawtypes")
	private ArrayList<Map> to; 
	private Map<String, Object> elementto;
	private Map<String, Object> emaildata;
	private Map<String, Object> result;

	@SuppressWarnings("rawtypes")
	public void sendMailValoracion(String nombre, String mail, String producto){

		/*String mensajehtml = mensaje.replaceAll("\n", "</br>");
		mensajehtml = mensajehtml.replaceAll("ó", "&#243;");
		mensajehtml = mensajehtml.replaceAll("á", "&#225;");
		mensajehtml = mensajehtml.replaceAll("é", "&#233;");
		mensajehtml = mensajehtml.replaceAll("í", "&#237;");
		mensajehtml = mensajehtml.replaceAll("ú", "&#250;");
		mensajehtml = mensajehtml.replaceAll("ñ", "&#241;");*/

		sendpulse = new Sendpulse("edb1ec9661ed3d0580f986e7ffd6be1d", "67a7ea18994ba181a343aecf432ebfc0");
		from = new HashMap<String, Object>();
		from.put("name", "Gestion Material");
		from.put("email", "gorka.gonzalo@alumni.mondragon.edu");
		to = new ArrayList<Map>();
		elementto = new HashMap<String, Object>();
		elementto.put("name", nombre);
		elementto.put("email",mail);
		to.add(elementto);
		emaildata = new HashMap<String, Object>();
		//emaildata.put("html","YA PUEDE RECOGER SU RECURSO");
		emaildata.put("text","La reseva de "+producto+" se ha realizado correctamente.");
		emaildata.put("subject","Confimación de Reserva");
		emaildata.put("from",from);
		emaildata.put("to",to);
		result = (Map<String, Object>) sendpulse.smtpSendMail(emaildata);
		System.out.println("Result: " + result);
	}
}

