package my.nmk.sb1.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Described {
	String athor() default "Maksud Nurullaev";
	int currentVersion() default 1;	
}
