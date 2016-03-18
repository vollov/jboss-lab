package org.demo.test.wineshop.beans;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.demo.wineshop.beans.ShoppingCartBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestShopperCartBean {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
            .addClass(ShoppingCartBean.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    ShoppingCartBean cart;

    @Test
    public void add_item() {
    	cart.addWineItem("Bordeaux");
    	
    	int expected_count = 1;
        Assert.assertEquals("cart size should be " + expected_count,
        		cart.getCartItems().size(), expected_count);
    }
    
}
