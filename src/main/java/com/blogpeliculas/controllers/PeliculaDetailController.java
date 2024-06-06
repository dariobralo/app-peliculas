package com.blogpeliculas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/detail")
public class PeliculaDetailController {

    /* formatto para la edicion de comentario

    @PreAuthorize("hasPermission(#article, 'isEditor')")
    public void acceptArticle(Article article) {…}

    Only the authorized user can call this method, and they need to have
    isEditor permission in the service.
    We also need to remember to explicitly configure a PermissionEvaluator
    in our application context, where customInterfaceImplementation will be the
    class that implements PermissionEvaluator:

    @Override
    protected MethodSecurityExpressionHandler expressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler =
          new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new CustomInterfaceImplementation());
        return expressionHandler;
    }

    */


}
