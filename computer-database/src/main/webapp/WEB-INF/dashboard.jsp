<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Computer Database</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="utf-8">
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
	            <h1 id="homeTitle">
	                ${ nbComputer } Computers found
	            </h1>
	            <div id="actions" class="form-horizontal">
	                <div class="pull-left">
	                    <form id="searchForm" action="#" method="GET" class="form-inline">
	
	                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
	                        <input type="submit" id="searchsubmit" value="Filter by name"
	                        class="btn btn-primary" />
	                    </form>
	                </div>
	                <div class="pull-right">
	                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
	                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
	                </div>
	            </div>
	        </div>
	
	        <form id="deleteForm" action="#" method="POST">
	            <input type="hidden" name="selection" value="">
	        </form>
	
	        <div class="container" style="margin-top: 10px;">
	            <table class="table table-striped table-bordered">
	                <thead>
	                    <tr>
	                        <!-- Variable declarations for passing labels as parameters -->
	                        <!-- Table header for Computer Name -->
	
	                        <th class="editMode" style="width: 60px; height: 22px;">
	                            <input type="checkbox" id="selectall" /> 
	                            <span style="vertical-align: top;">
	                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
	                                        <i class="fa fa-trash-o fa-lg"></i>
	                                    </a>
	                            </span>
	                        </th>
	                        <th>
	                            Computer name
	                        </th>
	                        <th>
	                            Introduced date
	                        </th>
	                        <!-- Table header for Discontinued Date -->
	                        <th>
	                            Discontinued date
	                        </th>
	                        <!-- Table header for Company -->
	                        <th>
	                            Company
	                        </th>
	
	                    </tr>
	                </thead>
	                <!-- Browse attribute computers -->
	                <tbody id="results">
	                    <c:forEach items="${computerDtoCollection}" var="computerDtoCollection" >
							<tr>
								<td class="editMode"><input type="checkbox" name="cb"
									class="cb" value=${ computerDtoCollection.id }></td>
								<td><a href="editComputer?idComputer=${ computerDtoCollection.id }" onclick="">${ computerDtoCollection.name }</a></td>
								<td><c:out value="${computerDtoCollection.introduced}"/></td>
								<td><c:out value="${computerDtoCollection.discontinued}"/></td>
								<td><c:out value="${computerDtoCollection.idCompanie}"/></td>
							</tr>
						</c:forEach>
					
	                </tbody>
	            </table>
	            
	        </div>
		</section>
    
		<footer class="navbar-fixed-bottom">
	     <div class="container text-center">
            <ul class="pagination">
                <li>
                	<c:if test="${page.getNumPage() > 1}">
                    	<a href="/computer-database/dashboard?page=${page.getNumPage() - 1}" aria-label="Previous">
                      		<span aria-hidden="true">&laquo;</span>
                  		</a>
                	</c:if>
              	</li>
              	<c:forEach var="i" begin="1" end="2">
              		<c:if test="${page.getNumPage() - (3 - i) >= 1}">
              			<li><a href="/computer-database/dashboard?page=${page.getNumPage() - (3 - i)}"><c:out value="${page.getNumPage() - (3 - i)}"/></a></li>
              		</c:if>
              	</c:forEach>
              	<c:forEach var="i" begin="0" end="2">
              		<c:if test="${page.getNumPage() + i <= nbPagesMax}">
              			<c:set var="active" value=""/>
                        <c:if test = "${page.getNumPage() + i ==  page.getNumPage()}">
                          <c:set var="active" value="active"/>
                         </c:if>
              			<li class="${active}"><a href="/computer-database/dashboard?page=${page.getNumPage() + i}"><c:out value="${page.getNumPage() + i}"/></a></li>
              		</c:if>
              	</c:forEach>
              	<li>
              		<c:if test="${page.getNumPage() < nbPagesMax}">
                		<a href="/computer-database/dashboard?page=${page.getNumPage() + 1}" aria-label="Next">
                    		<span aria-hidden="true">&raquo;</span>
                		</a>
                	</c:if>
            	</li>
        	</ul>
   			<div class="btn-group btn-group-sm pull-right" role="group" >
	        	<c:set var="active" value=""/>
	            <c:if test = "${page.getNbElementByPage() == 10}">
	               <c:set var="active" value="active"/>
	            </c:if>
	            <a href="/computer-database/dashboard?nbByPage=10"><button type="button" class="btn btn-default ${active}">10</button></a>
	            <c:set var="active" value=""/>
	            <c:if test = "${page.getNbElementByPage() == 50}">
	               <c:set var="active" value="active"/>
	            </c:if>
	            <a href="/computer-database/dashboard?nbByPage=50"><button type="button" class="btn btn-default ${active}">50</button></a>
	            <c:set var="active" value=""/>
	            <c:if test = "${page.getNbElementByPage() == 100}">
	               <c:set var="active" value="active"/>
	            </c:if>
	            <a href="/computer-database/dashboard?nbByPage=100"><button type="button" class="btn btn-default ${active}">100</button></a>
        	</div>
		</div>
    	</footer>
    
		
		<script src="static/js/jquery.min.js"></script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/dashboard.js"></script>
	</body>
</html>
