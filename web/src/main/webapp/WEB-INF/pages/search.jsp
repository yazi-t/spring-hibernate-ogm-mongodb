<%--
  Created by IntelliJ IDEA.
  User: yasitha
  Date: 4/28/17
  Time: 9:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:twrapper>

  <jsp:attribute name="page_heading">
    My Blog
  </jsp:attribute>

  <jsp:attribute name="page_body">
    <div class="row">

      <c:forEach var="post" items="${posts}" varStatus="i">

        <div class="col-md-12 well">
          <div class="row">
            <div class="col-md-2">
              <img src="/uploads/hib-ogm/${post.imgUrl}" class="img-responsive" width="100%"/>
            </div>
            <div class="col-md-10">
              <h3>${post.title}</h3>
              <c:set var="shortDesc" value="${fn:substring(post.description, 0, 200)}" />
              <p class="text-muted">${shortDesc}</p>
            </div>
          </div>
        </div>

      </c:forEach>

    </div>
  </jsp:attribute>
</t:twrapper>

