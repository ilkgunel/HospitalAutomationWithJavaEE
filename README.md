# HospitalAutomationWithJavaEE

Software Engineering Lesson Project With Java EE Technologies

This project is being developed with Maven,JSF,PrimeFaces,JPA and Spring Security. For some features Servlets is being used.

İlkay Günel and Batuhan Apaydın are writing the code.

İlknur Özgen and Kübranur Öztürk are analyst.

Arif Cemal Özcan is tester.

Tolga Durak is designer.

We use some images that taken from the internet for taking appointment page.
The source of the images like this:
http://www.uottawa.ca/sante/sites/www.uottawa.ca.sante/files/icon-appointment_0.png

http://www.geelus.com/wp-content/uploads/2014/01/InfoIcon.png

http://iconizer.net/files/Crystal_Clear/orig/exit.png

After 6 January 2016, to run this project you need a server that has to have ejb container. You can use GlassFish, Tom EE, WildFly, Weblogic. For every server you have to prepare some configurations. I advise to use GlassFish Java EE Server.

In January 2016, GlassFish 4.1.1 has a bug that is about JDBC Connection Pool and JDBC Resource on domain admin console. The error is about getOutputStream method.

To run this application on GlassFish Server open domain.xml file in glassfish-4.1.1 (or glassfish4)/glassfish/domains/domain1/config and add this lines to between  <resources></resources> tags.

<jdbc-connection-pool datasource-classname="com.mysql.jdbc.jdbc2.optional.MysqlDataSource" name="mysql_HospitalAutomation_ilkayPool" wrap-jdbc-objects="false" connection-validation-method="auto-commit" res-type="javax.sql.DataSource">
      <property name="URL" value="jdbc:mysql://localhost:3306/HospitalAutomation"></property>
      <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
      <property name="portNumber" value="3306"></property>
      <property name="databaseName" value="HospitalAutomation"></property>
      <property name="User" value="root"></property>
      <property name="serverName" value="localhost"></property>
    </jdbc-connection-pool>
    <jdbc-resource pool-name="mysql_HospitalAutomation_ilkayPool" jndi-name="HospitalAutomation"></jdbc-resource>

After this, to between <servers></servers> tags add this.

<resource-ref ref="HospitalAutomation"></resource-ref>

Finally, in <applications></applications> tags add this:

<application context-root="/HospitalAutomationWithJavaEE" object-type="user" name="HastaneOtomasyonu" directory-deployed="true" location="file:/Users/ilkaygunel/Desktop/HospitalAutomationWithJavaEE/target/HastaneOtomasyonu-1.0-SNAPSHOT/">
      <property name="archiveType" value="war"></property>
      <property name="appLocation" value="file:/Users/ilkaygunel/Desktop/HospitalAutomationWithJavaEE/target/HastaneOtomasyonu-1.0-SNAPSHOT/"></property>
      <property name="org.glassfish.ejb.container.application_unique_id" value="95216682819584000"></property>
      <property name="defaultAppName" value="HastaneOtomasyonu-1.0-SNAPSHOT"></property>
      <module name="HastaneOtomasyonu">
        <engine sniffer="ejb"></engine>
        <engine sniffer="security"></engine>
        <engine sniffer="weld"></engine>
        <engine sniffer="jpa"></engine>
        <engine sniffer="web"></engine>
      </module>
    </application>

Now you can run the project on the GlassFish Server.