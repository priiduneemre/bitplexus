<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<c:url value="404" var="pageBaseUrl"/>

<bpl:page activeMenuItem="null" title="Page not found">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-base panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-4 col-md-4 col-xs-offset-1 col-md-offset-1 text-right">
									<img alt="Human skull" id="error-image" src="static/img/err_skull_sketch.png"/>
								</div>
								<div class="col-xs-5 col-md-5 text-left">
									<h2 class="page-header panel-base-heading">404 - Page not found</h2>
									<p>
										You've reached a dead end... this page has either been moved, deleted, or it never 
										existed in the first place :(.
									</p>
									<a class="btn btn-success body-btn-margin" href="${pageBaseUrl}/../">Back to homepage</a>
									<p class="small">
										P.S. If this link ruined your day, please consider <a href="mailto:tips@bitplexus.com">reporting it</a>.
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</bpl:page>