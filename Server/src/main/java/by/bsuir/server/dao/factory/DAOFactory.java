package by.bsuir.server.dao.factory;

import by.bsuir.server.dao.AdminDAO;
import by.bsuir.server.dao.BugalterDAO;
import by.bsuir.server.dao.MinusOrPlusDAO;
import by.bsuir.server.dao.UserDAO;
import by.bsuir.server.dao.impl.MySQLAdminDAO;
import by.bsuir.server.dao.impl.MySQLBugalterDAO;
import by.bsuir.server.dao.impl.MySQLMinusOrPlusDAO;
import by.bsuir.server.dao.impl.MySQLUserDAO;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new MySQLUserDAO();

    private final AdminDAO adminDAO = new MySQLAdminDAO();

    private final BugalterDAO bugalterDAO = new MySQLBugalterDAO();

    private final MinusOrPlusDAO minusOrPlusDAO = new MySQLMinusOrPlusDAO();

    private DAOFactory() {
    }

    public MinusOrPlusDAO getMinusOrPlusDAO() {
        return minusOrPlusDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public BugalterDAO getBugalterDAO() {
        return bugalterDAO;
    }


}
