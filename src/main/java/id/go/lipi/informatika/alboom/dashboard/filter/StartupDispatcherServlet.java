package id.go.lipi.informatika.alboom.dashboard.filter;

import id.go.lipi.informatika.alboom.dashboard.function.ConstantaUtil;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import net.sourceforge.stripes.controller.DispatcherServlet;

public class StartupDispatcherServlet extends DispatcherServlet {

    private static final long serialVersionUID = 8196753317373597590L;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ConstantaUtil.WEB_CONTENT_LOCATION = config.getServletContext().getRealPath("/WEB-PAGES/") + "/";
        ConstantaUtil.WEB_INF_LOCATION = config.getServletContext().getRealPath("/WEB-INF/").replaceAll("\\\\", "/") + "/";

//      XmlParser xmlParser = new XmlParser(config.getServletContext().getRealPath("/WEB-INF/") + "/");
//      xmlParser.run();
    }

}
