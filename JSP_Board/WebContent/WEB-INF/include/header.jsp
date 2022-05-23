<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
<!--       <li class="nav-item"> -->
<!--         <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a> -->
<!--       </li> -->
        <c:forEach items="${menuList }" var="menu">
   	  	<li class="nav-item d-none d-sm-inline-block">
        	<a href="javascript:goPage('<%=request.getContextPath() %>${menu.murl }','${menu.mcode }');subMenu_go('${menu.mcode}');" class="nav-link"><i class="${menu.micon}"></i>&nbsp;${menu.mname }</a>
   	   	</li>
      </c:forEach>      
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">

      <li class="nav-item">
        <a class="nav-link" data-widget="fullscreen" href="#" role="button">
          <i class="fas fa-expand-arrows-alt"></i>
        </a>
      </li>
      
    </ul>
  </nav>

</body>