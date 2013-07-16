package guestbook;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nigini
 */
public class SignGuestbookServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(SignGuestbookServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        String content = req.getParameter("content");
        if(content == null){
            content = "(NO GREETINGS!!! THAT'S SAD!)";
        }
        if(user != null){
            log.info("Greeting posted by user " + user.getNickname() + ": " + content);
        } else {
            log.info("Greeting posted anonymously: " + content);
        }
        resp.sendRedirect("/guestbook.jsp");
    }
    
    
}
