package org.demo.test.wineshop.beans;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.demo.wineshop.beans.User;
import org.demo.wineshop.beans.UserServiceBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class TestUserServiceBean {
    private static final Logger LOGGER = Logger.getLogger(TestUserServiceBean.class.getName());

    @Inject
    private UserServiceBean service;

    @Deployment
    public static JavaArchive createTestableDeployment() {
        final JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "example.jar")
                .addClasses(User.class, UserServiceBean.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                // Enable CDI
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

        LOGGER.info(jar.toString(Formatters.VERBOSE));

        return jar;
    }

    @Test
    public void callServiceToAddNewUserToDB() {
        final User user = new User("Ike");
        service.addUser(user);
        
        assertNotNull("User id should not be null!",user.getId());
    }
}
