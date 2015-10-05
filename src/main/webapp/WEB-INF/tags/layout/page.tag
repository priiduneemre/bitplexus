<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<%@ attribute name="activeMenuItem" required="true" rtexprvalue="true" %>
<%@ attribute name="title" required="true" rtexprvalue="true" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<meta name="application-name" content="Bitplexus"/>
		<meta name="description" content="A free, open-source multi-currency web wallet with support for almost every major cryptocurrency in existence"/>
		<meta name="keywords" content="bitcoin, blockchain, cryptocurrency, litecoin, multicurrency, multi-currency, online wallet, wallet, wallet service, web wallet"/>
		<meta name="author" content="Priidu Neemre"/>

		<title>Bitplexus - ${title} | Cryptocurrency Wallet</title>

		<base href="/bitplexus-webapp/"/>

		<link rel="shortcut icon" href="static/img/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="static/css/vendor/bootstrap/3.3.5/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/css/vendor/bootstrap/3.3.5/bootstrap-theme.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/css/vendor/bootstrap-languages/1.0.0/bootstrap-languages.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/css/vendor/bootstrap-social/4.10.1/bootstrap-social.css"/>
		<link rel="stylesheet" type="text/css" href="static/css/vendor/font-awesome/4.4.0/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="static/css/bootstrap-override.css"/>
		<link rel="stylesheet" type="text/css" href="static/css/custom.css"/>
		<link rel="stylesheet" type="text/css" href="static/css/template.css"/>
		<script type="text/javascript" src="static/js/vendor/jquery/2.1.4/jquery.min.js"></script>
		<script type="text/javascript" src="static/js/vendor/bootstrap/3.3.5/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/bootstrap-override.js"></script>
		<script type="text/javascript" src="static/js/exchange.js"></script>
		<script type="text/javascript" src="static/js/widget.js"></script>
	</head>
	<body>
		<bpl:header activeMenuItem="${activeMenuItem}"/>
		<jsp:doBody/>
		<bpl:footer/>
	</body>
</html>