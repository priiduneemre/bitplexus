<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<c:url value="login" var="pageBaseUrl"/>

<bpl:page activeMenuItem="null" title="Log in">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-base panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-10 col-md-offset-1">
									<div class="row">
										<div class="col-md-6 col-md-offset-3">
											<h2 class="page-header panel-base-heading">
												<img alt="Bitplexus" id="login-icon" src="static/img/icon_32x32.png"/>
												Log in to Bitplexus
											</h2>
											<div class="alert alert-danger alert-dismissible <c:if test="${empty danger}">element-hidden</c:if>" id="alert-danger" role="alert">
												<button class="close" data-dismiss="alert" type="button" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
												<strong id="alert-danger-heading"></strong> 
												<span id="alert-danger-body"></span>
											</div>
											<form id="login" method="post" role="form">
												<div class="form-group">
													<label for="username">Username</label>
													<input class="form-control" id="username" maxlength="20" placeholder="Username" tabindex="1" type="text" autocomplete="off" autofocus="autofocus" required="required"/>
												</div>
												<div class="form-group">
													<label for="password">Password</label>
													<input class="form-control" id="password" maxlength="255" placeholder="Password" tabindex="2" type="password" autocomplete="off" required="required"/>
												</div>
												<button class="btn btn-block btn-primary body-btn-margin-tall" tabindex="3" type="submit">Log in</button>
											</form>
											<div class="pull-left">No account yet? <a href="${pageBaseUrl}/../signup">Sign up</a></div>
											<div class="pull-right"><a href="${pageBaseUrl}/forgot">Forgot your password?</a></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</bpl:page>