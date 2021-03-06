<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
		<link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
		<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${ idComputer }
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="editComputer?idComputer=${ idComputer }" method="POST" onsubmit="return verifFormEdit(this)">
                        <input type="hidden" value="${ idComputer }" id="id" name="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name" value="${ computer.name }" onblur="verifName(this)">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date" value="${ computer.introduced }" onblur="verifDates(this.introduced, this.discontinued)">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date" value="${ computer.discontinued }" onblur="verifDates(this.introduced, this.discontinued)">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId">
                                    
                                    <option value="0">--</option>
                                    
									<c:forEach items="${companieDtoCollection}" var="companieDtoCollection" >
										<c:if test="${ computer.getIdCompanie() == companieDtoCollection.getId() }">
											<option value="${companieDtoCollection.id}" selected ><c:out value="${companieDtoCollection.name}"/></option>
										</c:if>
										
										<c:if test="${ computer.discontinued != companieDtoCollection.id }">
	                                    	<option value="${companieDtoCollection.id}" ><c:out value="${companieDtoCollection.name}"/></option>
	                                    </c:if>
                                    </c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script src="static/js/validator.js"></script>
</body>
</html>