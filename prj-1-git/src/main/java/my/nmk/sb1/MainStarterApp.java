package my.nmk.sb1;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import my.nmk.sb1.components.DumBean;
import my.nmk.sb1.objects.EmployeeService;

@SpringBootApplication
//@RestController
//@EnableAutoConfiguration
//@ComponentScan
//@Configuration
@EnableAspectJAutoProxy
public class MainStarterApp extends SpringBootServletInitializer { //implements ApplicationContextAware
	private final static Logger logger = LoggerFactory.getLogger(MainStarterApp.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		//return super.configure(builder);
		return builder.sources(MainStarterApp.class);
	}

	@Autowired
	private ApplicationContext appContext = null;
	@Autowired
	@Qualifier("main")
	private DumBean dumb = null;
	@Autowired
	@Qualifier("second")
	private DumBean dumb2 = null;
	@Autowired
	@Qualifier("second")
	private List<DumBean> listOfBeans = null;
	
	@Autowired
	private EmployeeService empService = null;

	
//	@RequestMapping("/")
//	//@GetMapping("/")
//	@ResponseBody
//	String home() {
//		logger.debug("Home action executed!");
//		//return "Hello World - second stage, v11!";
//		return "index";
//	}
   
    @RequestMapping("/quit")
    String MyQuit() {
    	this.myPreDestroy();
    	( (ConfigurableApplicationContext) appContext).close();
    	return "Bye!!!";
    }
    

    public static void main(String[] args) {
        logger.debug("Main action executed!");
        SpringApplication.run(MainStarterApp.class, args);
    }
    
/*
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appContext = applicationContext;		
        Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Application context is setted up!");
	}
*/
	@Autowired
	void simpleAutoWired(@Qualifier("main") DumBean dumb) {
		Assert.notNull(dumb, "dumb var could not be NULL!");
		this.dumb = dumb;
        logger.debug("Just test for @Autowired for: " + dumb.getClass().getName());		
	}

	@PostConstruct
	public void myPostConstruct() {
		logger.debug("Post costruct method executed!");		
		Assert.notNull(appContext, "appContext should not be null");
		Assert.notNull(dumb, "dumb var could not be NULL!");
		Assert.notNull(listOfBeans,"listOfBeans should not be a NULL!");
		Assert.notNull(empService, "EmployeeService should not be a NULL!");
		logger.debug("listOfBeans size: " + listOfBeans.size());
		logger.info("SET UP DATABASE and setup initial records!");
		empService.setInitialData();		
	}

	@PreDestroy
	public void myPreDestroy() {
		logger.debug( "Pre destroy method executed!");				
	}
}