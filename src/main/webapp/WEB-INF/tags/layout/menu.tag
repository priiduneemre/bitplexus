<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<%@ attribute name="activeMenuItem" required="true" rtexprvalue="true" %>

				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-left">
						<li<c:if test="${activeMenuItem eq 'Wallets'}"> class="active"</c:if>><a class="menu-link" href="customer/wallets">Wallets</a></li>
						<li<c:if test="${activeMenuItem eq 'Address book'}"> class="active"</c:if>><a class="menu-link" href="customer/addressbook">Address book</a></li>
						<li<c:if test="${activeMenuItem eq 'Payment requests'}"> class="active"</c:if>><a class="menu-link" href="customer/requests">Payment requests</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a class="dropdown-toggle menu-link" data-toggle="dropdown" href="" role="button" aria-expanded="false" aria-haspopup="true">
								<span class="lang-xs lang-lbl-en" lang="en">&nbsp;</span>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="en"><span class="lang-xs lang-lbl-en" lang="en">&nbsp;</span></a></li>
								<li><a href="et"><span class="lang-xs lang-lbl-en" lang="et">&nbsp;</span></a></li>
							</ul>
						</li>
						<li class="<c:if test="${activeMenuItem eq 'Account'}">active </c:if>dropdown">
							<a class="dropdown-toggle menu-link" data-toggle="dropdown" href="" role="button" aria-expanded="false" aria-haspopup="true">
								<span class="glyphicon glyphicon-user navbar-glyph-margin-right" aria-hidden="true"></span>
								<span>rebel_sloth</span>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li>
									<a href="member/activity">
										<span>Activity</span>
										<span class="dropdown-activity-glyph glyphicon glyphicon-transfer" aria-hidden="true"></span>
									</a>
								</li>
								<li>
									<a href="member/settings">
										<span>Settings</span>
										<span class="dropdown-settings-glyph glyphicon glyphicon-wrench" aria-hidden="true"></span>
									</a>
								</li>
								<li class="divider" role="separator"></li>
								<li>
									<a href="logout">
										<span>Log out</span>
										<span class="dropdown-logout-glyph glyphicon glyphicon-off" aria-hidden="true"></span>
									</a>
								</li>
							</ul>
						</li>
						<li>
							<p class="navbar-btn navbar-btn-margin-left">
								<a class="btn btn-danger" href="signup">Sign up</a>
							</p>
						</li>
						<li>
							<p class="navbar-btn navbar-btn-margin-left navbar-content-margin-right">
								<a class="btn btn-warning" href="login">Log in</a>
							</p>
						</li>
					</ul>
				</div>