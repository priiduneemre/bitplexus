package com.neemre.bitplexus.frontend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neemre.bitplexus.common.util.ServletUtils;
import com.neemre.bitplexus.frontend.controller.misc.Views;

import static com.neemre.bitplexus.frontend.controller.misc.RequestMappings.*;

@Controller
@RequestMapping(ROOT_MAPPING)
public class GeneralController {

	@RequestMapping(value = {ROOT_MAPPING, VIEW_HOME_MAPPING}, method = RequestMethod.GET)
	public String viewHome() {
		return Views.HOME_VIEW.getPath();
	}

	@RequestMapping(value = {VIEW_LOGIN_MAPPING}, method = RequestMethod.GET)
	public String viewLogin() {
		return Views.LOGIN_VIEW.getPath();
	}

	@RequestMapping(value = {REDIRECT_ERROR_403_MAPPING}, method = RequestMethod.GET)
	public String redirectError403(HttpServletRequest request) {
		return ServletUtils.getRedirectUrl(request, VIEW_ERROR_403_MAPPING);
	}

	@RequestMapping(value = {VIEW_ERROR_403_MAPPING}, method = RequestMethod.GET)
	public String viewError403() {
		return Views.ERROR_403_VIEW.getPath();
	}

	@RequestMapping(value = {REDIRECT_ERROR_404_MAPPING}, method = RequestMethod.GET)
	public String redirectError404(HttpServletRequest request) {
		return ServletUtils.getRedirectUrl(request, VIEW_ERROR_404_MAPPING);
	}

	@RequestMapping(value = {VIEW_ERROR_404_MAPPING}, method = RequestMethod.GET)
	public String viewError404() {
		return Views.ERROR_404_VIEW.getPath();
	}

	@RequestMapping(value = {REDIRECT_ERROR_500_MAPPING}, method = RequestMethod.GET)
	public String redirectError500(HttpServletRequest request) {
		return ServletUtils.getRedirectUrl(request, VIEW_ERROR_500_MAPPING);
	}

	@RequestMapping(value = {VIEW_ERROR_500_MAPPING}, method = RequestMethod.GET)
	public String viewError500() {
		return Views.ERROR_500_VIEW.getPath();
	}
}