package my.nmk.sb1.components;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import my.nmk.sb1.annotations.Described;

@Component
@EnableAspectJAutoProxy
public class DumBean implements Serializable {	
	private final static Logger logger = LoggerFactory.getLogger(DumBean.class);
	@Bean
	@Qualifier("main")
	@Scope("prototype")
	public DumBean getDumBean(InjectionPoint injectionPoint) {
        logger.debug("qualifier-main executed! Injection point: " + injectionPoint.getMember());
		return new DumBean();
	}
	
	@Bean
	@Primary
	@Qualifier("second")
	@Described
	public DumBean getSecondBean() {
		logger.debug("qualifier-second executed!");
		return new DumBean();
	}
	
	@Bean
	@Qualifier("second")
	public DumBean getSecond2Bean() {
		logger.debug("qualifier-second2 executed!");
		return new DumBean();		
	}
	
/**/

	/**
	 * 
	 */
	private static final long serialVersionUID = 613990155723976931L;
	
	private String name = "I'm dumb!";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
