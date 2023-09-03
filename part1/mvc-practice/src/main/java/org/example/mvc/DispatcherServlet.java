package org.example.mvc;

import org.example.mvc.annotation.RequestMethod;
import org.example.mvc.controller.Controller;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.ModelAndView;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    private List<ViewResolver> viewResolvers;
    private List<HandlerAdapter> handlerAdapters;

    @Override
    public void init() throws ServletException {
        requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.init();

        handlerAdapters = List.of(
                new SimpleControllerHandlerAdapter()
        );
        viewResolvers = List.of(
                new JspViewResolver()
        );
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started.");
        try {
            Controller handler = requestMappingHandlerMapping.findHandler(new HandlerKey(RequestMethod.valueOf(request.getMethod()), request.getRequestURI()));

            ModelAndView modelAndView = handlerAdapters
                    .stream()
                    .filter(handlerAdapter -> handlerAdapter.supports(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"))
                    .handle(request, response, handler);

            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolve(modelAndView.getView());
                view.render(modelAndView.getModel(), request, response);
            }
        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
        }
    }

}
