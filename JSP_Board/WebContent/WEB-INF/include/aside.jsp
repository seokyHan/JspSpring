<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="#" class="brand-link">
      <i class="fas fa-bars"></i>
      <span class="brand-text font-weight-light" style="margin-left:20px;">Category</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="<%=request.getContextPath() %>/resources/bootstrap/dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
             <div class="row">
		          <a href="javascript:OpenWindow('<%=request.getContextPath() %>
		          			/member/detail.do?id=${loginUser.id }','내정보','800','700');"
		          class="d-block" >${loginUser.name }</a>&nbsp;&nbsp;
		          <button onclick="location.href='<%=request.getContextPath() %>/common/logout.do';" 
		           	class="btn btn-xs btn-primary col-xs-3 " type="button" >LOGOUT</button>
	         </div>
	         <a href="tel:${loginUser.phone }">tel : ${loginUser.phone }</a><br/>
           	<a href="mailto:${loginUser.email }">email : ${loginUser.email }</a>
        </div>
      </div>

      
      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column subMenuList" data-widget="treeview" role="menu" data-accordion="false">
        
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>