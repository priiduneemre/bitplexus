<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<c:url value="home" var="pageBaseUrl"/>

<bpl:page activeMenuItem="null" title="Take control of your money">
		<div class="jumbotron">
			<div class="container">
				<h1>Welcome to Money 3.0</h1>
				<p>
					Bitplexus allows you to transact with anyone, anywhere, anytime.<br/>
					Instant &amp; free.<br/>
					It really is that simple.
				</p>
				<p><a class="btn btn-success btn-lg" href="${pageBaseUrl}/../signup" role="button">Get started &raquo;</a></p>
			</div>
		</div>
</bpl:page>