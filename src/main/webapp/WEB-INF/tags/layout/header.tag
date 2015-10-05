<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<%@ attribute name="activeMenuItem" required="true" rtexprvalue="true" %>

		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="" title="Bitplexus">
						<img alt="Bitplexus" id="brand-image" src="static/img/logo_560x187.png"/>
					</a>
				</div>
				<bpl:menu activeMenuItem="${activeMenuItem}"/>
			</div>
		</nav>