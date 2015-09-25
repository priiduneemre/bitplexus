<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
											<%--<c:if test="${not empty param['error']}">--%>
											<div class="alert alert-danger alert-dismissible" role="alert">
												<button class="close" data-dismiss="alert" type="button" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
												<strong>Whoops!</strong> Wrong username and/or password.
											</div>
											<%--</c:if>--%>
											<form method="post" role="form">
												<div class="form-group">
													<label for="username">Username</label>
													<input class="form-control" id="username" placeholder="Username" type="text" autocomplete="off" autofocus required/>
												</div>
												<div class="form-group">
													<label for="password">Password</label>
													<input class="form-control" id="password" placeholder="Password" type="password" autocomplete="off" required/>
												</div>
												<button class="btn btn-block btn-primary body-btn-margin-tall" type="submit">Log in</button>
											</form>
											<div class="pull-left">No account yet? <a href="signup/">Sign up</a></div>
											<div class="pull-right"><a href="login/forgot/">Forgot your password?</a></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>