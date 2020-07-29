<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
	            <a class="navbar-brand pull-right" href="logout"> Logout </a>
	        </div>
	        
	
   	 	</header>
   	 	
       	<section id="main">
	    	<div class="container">			
	            <h1 id="homeTitle">
	                ${ nbComputerDatabase } Computers found
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
						<th onclick="window.location.href='?orderBy=${orderBy=='cnASC'?'cnDSC': 'cnASC'}&search=${ search }&nbByPage=${page.getNbElementByPage()}'">Computer name 
						<c:if test="${orderBy == 'cnASC'}"><span class="pull-right">⬆</span></c:if>
						<c:if test="${orderBy == 'cnDSC'}"><span class="pull-right">⬇</span></c:if>
						<c:if test="${orderBy != 'cnASC'&& orderBy != 'cnDSC'}"><span class="pull-right">⬆⬇</span></c:if>
						</th>
						<th onclick="window.location.href='?orderBy=${orderBy=='diASC'?'diDSC': 'diASC'}&search=${ search }&nbByPage=${page.getNbElementByPage()}'">Introduced date 
						<c:if test="${orderBy == 'diASC'}"><span class="pull-right">⬆</span></c:if>
						<c:if test="${orderBy == 'diDSC'}"><span class="pull-right">⬇</span></c:if>
						<c:if test="${orderBy != 'diASC'&& orderBy != 'diDSC'}"><span class="pull-right">⬆⬇</span></c:if>
						</th>
						<!-- Table header for Discontinued Date -->
						<th  onclick="window.location.href='?orderBy=${orderBy=='ddASC'?'ddDSC': 'ddASC'}&search=${ search }&nbByPage=${page.getNbElementByPage()}'">Discontinued date 
						<c:if test="${orderBy == 'ddASC'}"><span class="pull-right">⬆</span></c:if>
						<c:if test="${orderBy == 'ddDSC'}"><span class="pull-right">⬇</span></c:if>
						<c:if test="${orderBy != 'ddASC'&& orderBy != 'ddDSC'}"><span class="pull-right">⬆⬇</span></c:if>
						</th>
						<!-- Table header for Company -->
						<th  onclick="window.location.href='?orderBy=${orderBy=='ciASC'?'ciDSC': 'ciASC'}&search=${ search }&nbByPage=${page.getNbElementByPage()}'">Company
						<c:if test="${orderBy == 'ciASC'}"><span class="pull-right">⬆</span></c:if>
						<c:if test="${orderBy == 'ciDSC'}"><span class="pull-right">⬇</span></c:if>
						<c:if test="${orderBy != 'ciASC'&& orderBy != 'ciDSC'}"><span class="pull-right">⬆⬇</span></c:if>
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
								<td><c:out value="${computerDtoCollection.nameCompany}"/></td>
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
                    	<a href="/webapp/dashboard?page=${page.getNumPage() - 1}&search=${ search }nbByPage=${page.getNbElementByPage()}&orderBy=${ orderBy }" aria-label="Previous">
                      		<span aria-hidden="true">&laquo;</span>
                  		</a>
                	</c:if>
              	</li>
              	<c:forEach var="i" begin="1" end="2">
              		<c:if test="${page.getNumPage() - (3 - i) >= 1}">
              			<li><a href="/webapp/dashboard?page=${page.getNumPage() - (3 - i)}&search=${ search }&nbByPage=${page.getNbElementByPage()}&orderBy=${ orderBy }"><c:out value="${page.getNumPage() - (3 - i)}"/></a></li>
              		</c:if>
              	</c:forEach>
              	<c:forEach var="i" begin="0" end="2">
              		<c:if test="${page.getNumPage() + i <= nbPagesMax}">
              			<c:set var="active" value=""/>
                        <c:if test = "${page.getNumPage() + i ==  page.getNumPage()}">
                          <c:set var="active" value="active"/>
                         </c:if>
              			<li class="${active}"><a href="/webapp/dashboard?page=${page.getNumPage() + i}&search=${ search }&nbByPage=${page.getNbElementByPage()}&orderBy=${ orderBy }"><c:out value="${page.getNumPage() + i}"/></a></li>
              		</c:if>
              	</c:forEach>
              	<li>
              		<c:if test="${page.getNumPage() < nbPagesMax}">
                		<a href="/webapp/dashboard?page=${page.getNumPage() + 1}&search=${ search }&nbByPage=${page.getNbElementByPage()}&orderBy=${ orderBy }" aria-label="Next">
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
	            <a href="/webapp/dashboard?nbByPage=10&search=${ search }&orderBy=${ orderBy }"><button type="button" class="btn btn-default ${active}">10</button></a>
	            <c:set var="active" value=""/>
	            <c:if test = "${page.getNbElementByPage() == 50}">
	               <c:set var="active" value="active"/>
	            </c:if>
	            <a href="/webapp/dashboard?nbByPage=50&search=${ search }&orderBy=${ orderBy }"><button type="button" class="btn btn-default ${active}">50</button></a>
	            <c:set var="active" value=""/>
	            <c:if test = "${page.getNbElementByPage() == 100}">
	               <c:set var="active" value="active"/>
	            </c:if>
	            <a href="/webapp/dashboard?nbByPage=100&search=${ search }&orderBy=${ orderBy }"><button type="button" class="btn btn-default ${active}">100</button></a>
        	</div>
		</div>
    	</footer>
    
		
		<script src="static/js/jquery.min.js"></script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/dashboard.js"></script>
	</body>
</html>
