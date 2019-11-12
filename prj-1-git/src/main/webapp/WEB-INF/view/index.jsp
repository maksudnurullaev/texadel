<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Index.JSP</title>
      <link rel="stylesheet" href="/css/bootstrap.min.css"/>
      <script src="/js/bootstrap.min.js"></script>       
   </head>
   <body>
      <div class="container">
         <h2>Simple Test Application (STA), Employees</h2>
         <!--Search Form -->
         <form action="/" method="get" id="seachEmployeeForm" role="form">
            <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
            <div class="form-group col-xs-5">
               <input value="${param['employeeName']}" type="text" name="employeeName" id="employeeName" class="form-control" required="true" placeholder="Type the Name or Last Name of the employee"/>                    
            </div>
            <button type="submit" class="btn btn-info">Search</button>
            <a class="btn btn-link" href="/" role="button">Cancel</a>
            <a class="btn btn-primary float-right" href="/employee/new" role="button">New employee</a>            
            <br />
            <br />
         </form>
         <!-- message if any! -->
         <c:if test="${not empty message}">                
             <div class="alert alert-success">
                 ${message}
             </div>
         </c:if> 
         <!--Employees List-->
         <form action="/employee" method="post" id="employeeForm" role="form" >
            <input type="hidden" id="idEmployee" name="idEmployee">
            <input type="hidden" id="action" name="action">
            <c:choose>
               <c:when test="${not empty employeeList}">
                  <table  class="table table-striped">
                     <thead>
                        <tr>
                           <td>#</td>
                           <td>Name</td>
                           <td>Last name</td>
                           <td>Birth date</td>
                           <td>Role</td>
                           <td>Department</td>
                           <td>E-mail</td>
                           <td>&nbsp;</td>
                        </tr>
                     </thead>
                     <c:forEach var="employee" items="${employeeList}">
                        <c:set var="cleassSuccess" value=""/>
                        <c:if test ="${idEmployee == employee.id}">
                           <c:set var="cleassSuccess" value="info"/>
                        </c:if>
                        <tr class="${cleassSuccess}">
                           <td><a href="/employee/edit/${employee.id}">${employee.id}</a></td>
                           <td>${employee.name}</td>
                           <td>${employee.lastName}</td>
                           <td>${employee.birthDate}</td>
                           <td>${employee.role}</td>
                           <td>${employee.department}</td>
                           <td>${employee.email}</td>
                           <td>
                            <!-- Implement 'Are you sure' message! -->
                            <a href="/employee/del/${employee.id}" class="confirmation">X</a>
                        </tr>
                     </c:forEach>
                  </table>
               </c:when>
               <c:otherwise>
                  <br>           
                  <div class="alert alert-info">
                     No people found matching your search criteria
                  </div>
               </c:otherwise>
            </c:choose>
         </form>
      </div>      
<jsp:include page="modalYesNoConfirmation.jsp"/>
   </body>
</html>