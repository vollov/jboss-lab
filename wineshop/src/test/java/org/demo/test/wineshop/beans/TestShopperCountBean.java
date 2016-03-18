package org.demo.test.wineshop.beans;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.demo.wineshop.beans.SearchFacadeBean;
import org.demo.wineshop.beans.ShopperCountBean;
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
public class TestShopperCountBean {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
            .addClasses(ShopperCountBean.class, ShoppingCartBean.class,SearchFacadeBean.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    ShopperCountBean counter;

    @Test
    public void increment_counter() {
    	counter.incrementShopperCount();
    	System.out.println("counter=" + counter.getShopperCount());
    	
    	int expected_count = 1;
        Assert.assertEquals("counter should be " + expected_count,
        		counter.getShopperCount(), expected_count);
    }
}
