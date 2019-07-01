package servlet;

import beans.Device;
import factory.ParserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.AbstractDeviceParser;
import validator.XMLValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/parse")
@MultipartConfig
public class ParserServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(ParserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String parserName = req.getParameter("parser");
        InputStream fileToValidate = filePart.getInputStream();
        boolean isValid = XMLValidator.validate(fileToValidate);
        InputStream fileToParse = filePart.getInputStream();

        if (isValid) {
            AbstractDeviceParser parser = ParserFactory.INSTANCE.createParser(parserName);
            try {
                parser.buildDeviceList(fileToParse);
            } catch (ParserConfigurationException e) {
                LOGGER.warn("ParserConfigurationException in servlet");
            }
            List<Device> devices = parser.getDeviceList();
            System.out.println(devices);
            req.setAttribute("devices", devices);
            req.getRequestDispatcher("WEB-INF/result.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
        }
    }
}
