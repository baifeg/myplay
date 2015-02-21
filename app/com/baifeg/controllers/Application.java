package com.baifeg.controllers;

import play.mvc.Controller;
import play.mvc.Result;

import com.baifeg.play.FreemarkerPage;

public class Application extends Controller {

    public static Result index() {
		return ok(FreemarkerPage.render("index.ftl"));
    }

}
