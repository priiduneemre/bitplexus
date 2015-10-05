<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<c:url value="403" var="pageBaseUrl"/>

<bpl:page activeMenuItem="null" title="Access denied">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-base panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-4 col-md-4 col-xs-offset-1 col-md-offset-1 text-right">
									<img alt="Gas mask" id="error-image" src="static/img/err_gasmask_sketch.png"/>
								</div>
								<div class="col-xs-5 col-md-5 text-left">
									<h2 class="page-header panel-base-heading">403 - Access denied</h2>
									<p>
										Nothing to see here folks, move along. You don't have permission to view this page --
										at least not with those credentials :(.
									</p>
									<a class="btn btn-success body-btn-margin" href="${pageBaseUrl}/../">Back to homepage</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</bpl:page>