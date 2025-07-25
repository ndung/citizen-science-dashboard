/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.go.lipi.informatika.alboom.dashboard.filter;

import id.go.lipi.informatika.alboom.dashboard.function.ConstantaUtil;
import net.sourceforge.stripes.config.RuntimeConfiguration;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class StartupActionBean extends RuntimeConfiguration {

    @Override
    public void init() {
        ConstantaUtil.ctx          = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        ConstantaUtil.rootPath     = getServletContext().getContextPath();
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
