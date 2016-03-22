package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Reservation;
import model.Dispatcher;
import model.User;
import exception.UserAlreadyExistException;


public class UserController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private Dispatcher dispatcher = Dispatcher.getInstance();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
        
		String action = request.getParameter("action");
		action = (action == null) ? "index" : action;
		
		if(action.equals("index"))
			this.indexAction(request, response);
		else if(action.equals("signUp"))
			this.signUpAction(request, response);
		else if(action.equals("listReservations"))
			this.listReservationsAction(request, response);
		else if(action.equals("signOut"))
			this.signOutAction(request, response);
		else if(action.equals("promoteUser"))
			this.promoteUserAction(request, response);
		else
			request.getRequestDispatcher("/views/error/404.jsp").forward(request, response);
	}

	/**
	 * Afficher la page d'accueil de l'espace user
	 * 
	 * @param request requête HTTP courante
	 * @param response réponse HTTP pour la requête courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void indexAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(!this.isConnected(request)) {
			response.sendRedirect("login");
			return;
		}
		request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
	}

	/**
	 * Inscrire un nouvel user
	 * 
	 * @param request requête HTTP courante
	 * @param response réponse HTTP pour la requête courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void signUpAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String lastname = request.getParameter("Lastname");
		String firstname = request.getParameter("Firstname");
		String pseudo = request.getParameter("Pseudo");
		String password = request.getParameter("Password");
		String right = request.getParameter("Right");
		int reservations  = Integer.parseInt(request.getParameter("Reservations"));
		
		
		if("POST".equals(request.getMethod())) {
			try {
				HttpSession session = request.getSession();
				session.setAttribute("user", dispatcher.addUser(lastname, firstname, pseudo, password, right, reservations));		
				if(session.getAttribute("reservationAttribute") != null) {
					response.sendRedirect("reservation?action=reserve&choice=" + session.getAttribute("reservationAttribut"));
					return;
				}
				response.sendRedirect("index");
				return;
			}
			catch (UserAlreadyExistException e) {
				e.printStackTrace();
				request.setAttribute("error", true);
			}
		}
		
		request.getRequestDispatcher("/views/user/signUp.jsp").forward(request, response);
	}
	
	/**
	 * Récupérer les reservation de l'user en session
	 * 
	 * @param request requête HTTP courante
	 * @param response réponse HTTP pour la requête courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void listReservationsAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(!this.isConnected(request)) {
			response.sendRedirect("login");
			return;
		}
		
		User user = (User) request.getSession().getAttribute("user");
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) this.dispatcher.getReservations(user);
		
//		String nbPlaces = "1";
//		try {
//			request.getSession().getAttribute("nbPlaces").toString();
//		} catch (NullPointerException npe) {
//			
//		}
//		request.setAttribute("nbPlaces", nbPlaces);
		
		request.setAttribute("reservations", reservations);
		request.getRequestDispatcher("/views/user/listRooms.jsp").forward(request, response);
	}

	/**
	 * Déconnecter l'user en session
	 * 
	 * @param request requête HTTP courante
	 * @param response réponse HTTP pour la requête courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void signOutAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getSession().removeAttribute("user");
		response.sendRedirect("index");
	}
	
	/**
	 * Promote un User
	 * 
	 * @param request requête HTTP courante
	 * @param response réponse HTTP pour la requête courante
	 * @throws IOException
	 * @throws ServletException
	 */
	private void promoteUserAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String pseudo = request.getParameter("Pseudo");
			
		if("POST".equals(request.getMethod())) {
			
			HttpSession session = request.getSession();
			session.setAttribute("userPromoted", dispatcher.promoteUser(pseudo));		
			if(session.getAttribute("userPromoted") != null) {
				response.sendRedirect("user?action=promoteUser");
				return;
			}
			response.sendRedirect("index");
			return;
		}
		
		request.getRequestDispatcher("/views/user/promoteUser.jsp").forward(request, response);
	}
	
	/**
	 * Vérifie si un user est actuellement connecté
	 * 
	 * @param request requête HTTP courante
	 * @return true si l'utilisateur est connecté, false sinon
	 */
	private boolean isConnected(HttpServletRequest request) {
		return(request.getSession().getAttribute("user") != null);
	}
}