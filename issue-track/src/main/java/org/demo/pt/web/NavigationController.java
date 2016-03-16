package org.demo.pt.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {

	private static final long serialVersionUID = 1L;

	// this managed property will read value from request parameter pageId
	@ManagedProperty(value = "#{param.pageId}")
	private String pageId;

	// condional navigation based on pageId
	// if pageId is 1 show page1.xhtml,
	// if pageId is 2 show page2.xhtml
	// else show home.xhtml
	public String showPage() {
		System.out.println("showPage()");
		if (pageId == null) {
			return "home";
		}
		if (pageId.equals("1")) {
			System.out.println("showPage().success");
			return "success";
		} else if (pageId.equals("2")) {
			System.out.println("showPage().failure");
			return "failure";
		} else {
			System.out.println("showPage().home");
			return "home";
		}
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
}
