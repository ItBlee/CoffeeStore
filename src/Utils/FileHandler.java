package Utils;

import java.io.*;
import java.util.Properties;

import static Utils.SystemConstant.*;

public class FileHandler {
    public static void saveConfig() throws IOException {
        OutputStream output = new FileOutputStream(CONFIG_FILE_URL);
        Properties prop = new Properties();

        if (General.USER_IS_REMEMBER) {
            prop.setProperty(CONFIG_PROP_USER_USERNAME, General.USER_USERNAME);
            prop.setProperty(CONFIG_PROP_USER_PASSWORD, General.USER_PASSWORD);
            prop.setProperty(CONFIG_PROP_USER_REMEMBER, String.valueOf(General.USER_IS_REMEMBER));
        }
        prop.setProperty(CONFIG_PROP_DB_HOST, General.DB_HOST);
        prop.setProperty(CONFIG_PROP_DB_NAME, General.DB_NAME);
        prop.setProperty(CONFIG_PROP_DB_USERNAME, General.DB_USERNAME);
        if (!General.DB_PASSWORD.isBlank())
            prop.setProperty(CONFIG_PROP_DB_PASSWORD, General.DB_PASSWORD);

        prop.store(output, "Coffee Store App Config File");
        output.close();
    }

    public static void loadConfig() throws IOException {
        InputStream input = new FileInputStream(CONFIG_FILE_URL);
        Properties prop = new Properties();
        prop.load(input);
        input.close();

        General.USER_USERNAME = prop.getProperty(CONFIG_PROP_USER_USERNAME);
        General.USER_PASSWORD = prop.getProperty(CONFIG_PROP_USER_PASSWORD);
        General.USER_IS_REMEMBER = Boolean.parseBoolean(prop.getProperty(CONFIG_PROP_USER_REMEMBER));

        String dbHost = prop.getProperty(CONFIG_PROP_DB_HOST);
        String dbName = prop.getProperty(CONFIG_PROP_DB_NAME);
        String dbUsername = prop.getProperty(CONFIG_PROP_DB_USERNAME);
        String dbPassword = prop.getProperty(CONFIG_PROP_DB_PASSWORD);
        if (!dbHost.isBlank())
            General.DB_HOST = dbHost;
        if (!dbName.isBlank())
            General.DB_NAME = dbName;
        if (!dbUsername.isBlank())
            General.DB_USERNAME = dbUsername;
        if (!dbPassword.isBlank())
            General.DB_PASSWORD = dbPassword;
    }
}
