<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Employee-new</title>
      <link rel="stylesheet" href="/css/bootstrap.min.css"/>
      <script src="/js/bootstrap.min.js"></script>       
   </head>
   <body>
        <c:if test ="${empty action}">                        	
            <c:set var="action" value="new"/>
        </c:if>
        <div class="container">
            <form action="/employee/${action}/${employee.id}" method="post"  role="form" data-toggle="validator" >
                <input type="hidden" id="action" name="action" value="${action}">
                <input type="hidden" id="id" name="id" value="${employee.id}">
                <h2>Employee (${action})</h2>
                <div class="form-group col-xs-4">
                    <label for="name" class="control-label col-xs-4">Name:</label>
                    <input type="text" name="name" id="name" class="form-control" value="${employee.name}" required="true" />                                   

                    <label for="lastName" class="control-label col-xs-4">Last name:</label>                   
                    <input type="text" name="lastName" id="lastName" class="form-control" value="${employee.lastName}" required="true"/> 

                    <label for="birthdate" class="control-label col-xs-4">Birth date</label>                 
                    <input type="text"  pattern="^\d{2}-\d{2}-\d{4}$" name="birthDate" id="birthdate" class="form-control" value="${employee.birthDate}" maxlength="10" placeholder="dd-MM-yyy" required="true"/>

                    <label for="role" class="control-label col-xs-4">Role:</label>                    
                    <input type="text" name="role" id="role" class="form-control" value="${employee.role}" required="true"/> 

                    <label for="department" class="control-label col-xs-4">Department:</label>
                    <input type="text" name="department" id="department" class="form-control" value="${employee.department}" required="true"/>

                    <label for="department" class="control-label col-xs-4">E-mail:</label>                   
                    <input type="text" name="email" id="email" class="form-control" value="${employee.email}" placeholder="smith@aol.com" required="true"/>

<c:choose>
 <c:when test="${not empty skills}">
 <hr />
 <h3>Skills:</h3>
 <fieldset>
    <select multiple class="form-control" name="skills">
     <c:forEach var="skill" items="${skills}">
      <option value="${skill.id}" ${employee.isContainSkill(skill.id)?"selected":""}>${skill.name} - (${skill.description})</option>
     </c:forEach>
    </select>
  </fieldset>
 </c:when>
</c:choose>

                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">Accept</button> 
                    <a class="btn btn-link" href="/" role="button">Cancel</a>                    
                </div>                                                      
            </form>
        </div>   
    </body>
</html>