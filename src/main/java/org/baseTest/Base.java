package org.baseTest;

import java.io.FileInputStream;
import java.util.Properties;
public class Base {
    public final int STATUS_CODE_200 = 200;
    public final int STATUS_CODE_201 = 201;
    public Properties prop;

    public Base() {
        try {
            prop = new Properties();
            FileInputStream fip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/org/Config/Config.properties");
            prop.load(fip);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

}
