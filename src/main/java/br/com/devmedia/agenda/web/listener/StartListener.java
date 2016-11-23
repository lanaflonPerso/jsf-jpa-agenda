package br.com.devmedia.agenda.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class StartListener
 *
 */
@WebListener
public class StartListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public StartListener() {

	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {

		print();
	}

	private void print() {

		StringBuilder mensagem = new StringBuilder();
		mensagem.append("\n  _   _ _ _                           _             _ ____   _              ____             __  __          _ _       ");
		mensagem.append("\n | | | (_) |__   ___ _ __ _ __   __ _| |_ ___      | |  _ \\ / \\            |  _ \\  _____   _|  \\/  | ___  __| (_) __ _ ");
		mensagem.append("\n | |_| | | '_ \\ / _ \\ '__| '_ \\ / _` | __/ _ \\  _  | | |_) / _ \\    _____  | | | |/ _ \\ \\ / / |\\/| |/ _ \\/ _` | |/ _` |");
		mensagem.append("\n |  _  | | |_) |  __/ |  | | | | (_| | ||  __/ | |_| |  __/ ___ \\  |_____| | |_| |  __/\\ V /| |  | |  __/ (_| | | (_| |");
		mensagem.append("\n |_| |_|_|_.__/ \\___|_|  |_| |_|\\__,_|\\__\\___|  \\___/|_| /_/   \\_\\         |____/ \\___| \\_/ |_|  |_|\\___|\\__,_|_|\\__,_|");
		System.out.println(mensagem);
	}

}
