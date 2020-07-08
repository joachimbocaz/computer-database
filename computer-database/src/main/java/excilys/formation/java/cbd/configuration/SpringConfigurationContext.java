package excilys.formation.java.cbd.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan(basePackages="excilys.formation.java.cbd")
public class SpringConfigurationContext extends AbstractContextLoaderInitializer{

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfigurationContext.class);
		return rootContext;
	}

}
