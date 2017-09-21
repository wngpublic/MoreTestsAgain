package org.wayne.testing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.wayne.misc.Strings;

public class Testcases {
	
    Utils util = new Utils();
    List<Method> methods = new ArrayList<>();
	
    public Testcases() {
    	getMethods();
    }

    void start(String [] args) {
        long timebegin = System.nanoTime();
        try {
            if(args.length > 0) {
                test(args);
            }
            else {
                printMethods();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        long timeend = System.nanoTime();
        long timediff = timeend - timebegin;
        long timediffmilli = timediff / 1000000;
        p("\n");
        p("time elapse millis:%d\n", timediffmilli);
        
    }
    
    private void test(String [] args) throws 
        IllegalAccessException, 
        IllegalArgumentException, 
        InvocationTargetException 
    {
    	Integer i = Integer.parseInt(args[0]);
    	if(i >= methods.size()) {
    		printMethods();
    	} else {
    		Method m = methods.get(i);
    		m.invoke(this);
    	}
    }

    private static void p(String f, Object ...o) {
        System.out.printf(f, o);
    }
    
    private void getMethods() {
        Method [] tmpM = this.getClass().getMethods();
        for(int i = 0; i < tmpM.length; i++) {
            if(tmpM[i].getName().matches("^t\\d+")) {
                if(tmpM[i].getModifiers() == Modifier.PUBLIC) {
                    methods.add(tmpM[i]);
                }
            }
        }
    }

    private void printMethods() {
        for(int i = 0; i < methods.size(); i++) {
            p("%3d: %s\n", i, methods.get(i).getName());
        }
    }
    
    public void t1() {
    	Strings strings = new Strings();
    }

    public void t2() {
    }

    public void t3() {
    }

    public void t4() {
    }

    public void t5() {
    }

    public void t6() {
    }

    public void t7() {
    }

    public void t8() {
    }

    public void t9() {
    }

    public void t10() {
    }

    public void t11() {
    }

    public void t12() {
    }

    public void t13() {
    }

    public void t14() {
    }

    public void t15() {
    }

    public void t16() {
    }

    public void t17() {
    }

    public void t18() {
    }

    public void t19() {
    }

    public void t20() {
    }

}
