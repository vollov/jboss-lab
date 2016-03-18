package org.demo.test.wineshop.beans;

import java.util.List;

import javax.ejb.EJB;

import org.demo.wineshop.beans.SearchFacadeBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestSearchFacadeBean {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
            .addClass(SearchFacadeBean.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    SearchFacadeBean facade;
    
    @Test
    public void add_item() {
    	List<String> wineList = facade.wineSearch("Red");
    	
    	for(String item: wineList){
    		System.out.println("wine=" + item);
    	}
    }
}
